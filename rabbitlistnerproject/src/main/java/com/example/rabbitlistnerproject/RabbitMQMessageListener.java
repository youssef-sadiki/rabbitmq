package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

// To Listen to rabbitms msgs we need to implement MessageListener
public class RabbitMQMessageListener implements MessageListener
{

	// The param message will be injected by Spring and RabbitMq
	// and thats where we will recieve the msg.
	@Override
	public void onMessage(Message message) 
	{
		System.out.println("message = " + new String(message.getBody()));
	}

}
