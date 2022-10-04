package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



// This class handles the cnx between the RabbitMQ broker and the listener
@Configuration
public class RabbitMQConfig 
{
	// Defining out queue
	private static final String MY_QUEUE = "MyQueue";
	
	// Creating a queue when this app start, using the @Bean annot.
	// Creating a queue this way is an alternative to creating one
	// using the RabbitMQ dashboard.
	@Bean
	Queue myQueue()
	{	
		// true is for the Durability of the queue.
		return new Queue(MY_QUEUE, true);
	}
	
	// Creating a topic Exchange.
	@Bean
	Exchange myExchange()
	{
		return ExchangeBuilder.topicExchange("MyTopicExchange")
					   		  .durable(true)
					   		  .build();
	}
	
	// Creating a binding.
	@Bean
	Binding binding()
	{
		// This is a way of creating a binding.
		/*return new Binding(MY_QUEUE, 
						   Binding.DestinationType.QUEUE,
						   "MyTopicExchange",
						   "topic",
						   null);*/
		
		// This is a second way of creating a binding.
		return BindingBuilder.bind(myQueue())
							 .to(myExchange())
							 .with("topic") // routing key.
							 .noargs();
	}
	
	// Connection to the RabbitMQ broker.
	@Bean
	ConnectionFactory connectionFactory()
	{
		// We want our cnx to be stable, and to not keep opening and closing
		// cnx with the broker so we use CachingConnectionFactory.
		CachingConnectionFactory cachingConnectionFactory = 
				new CachingConnectionFactory("localhost");
		
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		return cachingConnectionFactory;
	}
	
	// MessageListenerContainer is used to bind MessageListener class and 
	// the queue and the connection.
	@Bean
	MessageListenerContainer messageListenerContainer()
	{
		SimpleMessageListenerContainer simpleMessageListenerContainer =
				new SimpleMessageListenerContainer();
		
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(myQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
		
		return simpleMessageListenerContainer;
	}
}
