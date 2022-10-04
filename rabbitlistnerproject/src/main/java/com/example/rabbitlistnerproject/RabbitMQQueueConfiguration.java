package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// See diff ways of creating queues with spring.
@Configuration
public class RabbitMQQueueConfiguration 
{
	@Bean
	Queue exampleHowToCreateQueue()
	{
		return new Queue("ExampleQueue", false);
	}
	
	@Bean
	Queue secondExampleHowToCreateQueue()
	{
		// "ExampleSecondQueue" is the name of the queue.
		return QueueBuilder.durable("ExampleSecondQueue")
						   .autoDelete()
						   .exclusive()
						   .build();
	}
}
