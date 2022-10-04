package com.rabbitmq.rabbitmqdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// To run the code when the app lunches we implement CommandLineRunner
@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner
{
	
	// To make use of RabbitTemplate we will autowire it.
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) 
	{
		SpringApplication.run(RabbitmqdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		// This meth is gonna publish message to the AMQP default
		// Exchange and it will get lost, which not a good thing, 
		// we need to specify the Exchange and Biding and Queue ..
		//rabbitTemplate.convertAndSend("Hello from out first message!");
		
		// In the rabbitMQ UI, we created an Exchanged by the name
		// "TestExchange", and a Queue by the name "RoutedQueue", and biding
		// them using a routing key with the name "testRouting". When we run this app
		// a message "Hi from code" is published to the queue "RoutedQueue"
		//rabbitTemplate.convertAndSend("TestExchange", "testRouting", "Hi from code");
		
		SimpleMessage simpleMessage = new SimpleMessage();
		simpleMessage.setName("FirstMessage");
		simpleMessage.setDescription("simpleDescription");
		
		// Here we publish a Java object
		rabbitTemplate.convertAndSend("MyTopicExchange", "topic", simpleMessage);
		
	}

}
