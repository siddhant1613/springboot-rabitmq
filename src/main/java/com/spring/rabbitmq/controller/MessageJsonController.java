package com.spring.rabbitmq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rabbitmq.dto.User;
import com.spring.rabbitmq.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
	
	private RabbitMQJsonProducer jsonProducer;

	
	
	public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
		super();
		this.jsonProducer = jsonProducer;
	}



	@PostMapping("/publish")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		jsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok("Json message sent to RabbitMq.....");
	}
	
	

}
