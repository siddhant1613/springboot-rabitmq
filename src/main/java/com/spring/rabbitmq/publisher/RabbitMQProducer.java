package com.spring.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	@Value(value = "${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value(value = "${rabbitmq.routingkey.name}") 
	private String routingKey;
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent -> %s",message));
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}

}
