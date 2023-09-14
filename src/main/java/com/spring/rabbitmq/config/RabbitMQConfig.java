package com.spring.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {
	
	@Value(value = "${rabbitmq.queue.name}")
	private String queue;
	
	@Value(value = "${rabbitmq.jsonqueue.name}")
	private String jsonQueue;
	
	@Value(value = "${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value(value = "${rabbitmq.routingkey.name}") 
	private String routingKey;
	
	@Value(value = "${rabbitmq.jsonroutingkey.name}")
	private String jsonRoutingKey;
	
	
	//spring bean for rabbit mq queue
	@Bean
	public Queue queue() {
		return new Queue(queue);		
	}
	
	//spring bean for queue ( store json object)
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	//spring bean for rabbit mq exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	//binding between queue and exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingKey);
	}
	// connectionFactory
	// RabbitTemplate -  use for sending messages
	// RabbitAdmin
		
		//spring boot auto configuration automatically configure these above there beans we don't need to configure it separately.
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
		
	}

}
