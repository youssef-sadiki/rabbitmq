package com.rabbitmq.rabbitmqdemo;

import java.io.Serializable;

// The msg have to implements Serializable to be sent over rabbitMQ
public class SimpleMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5781542639867140273L;
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public SimpleMessage() {
		super();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	// We impl the toString metho so that the listner can read
	// the content of the message.
	@Override
	public String toString() {
		return "SimpleMessage [name=" + name + ", description=" + description + "]";
	}
	
	
}
