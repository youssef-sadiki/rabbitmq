package com.example.rabbitlistnerproject;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfiguration 
{
	@Bean
	Exchange exampleHowToCreateExchange()
	{	// Since Exchange is an interface we need to return one of its
		// implementations.
		return new TopicExchange("ExampleExchange");
	}
	
	@Bean
	Exchange secondExampleHowToCreateExchange()
	{
		return ExchangeBuilder.directExchange("ExampleSecondExchange")
				              .autoDelete()
				              .internal()
				              .build();
	}
	
	@Bean
	Exchange thirdExample()
	{
		return ExchangeBuilder.topicExchange("TopicTestExchange")
							  .autoDelete()
							  .durable(true)
							  .internal()
							  .build();
	}
	
	@Bean
	Exchange fanoutExchange()
	{
		return ExchangeBuilder.fanoutExchange("FanoutTestExchange")
							  .autoDelete()
							  .durable(false)
							  .internal()
							  .build();
	}
	
	@Bean
	Exchange headersExchange()
	{
		return ExchangeBuilder.headersExchange("HeadersTestExchange")
							  .internal()
							  .durable(true)
							  .ignoreDeclarationExceptions()
							  .build();
	}
	
}
