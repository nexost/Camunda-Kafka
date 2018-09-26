package com.nexost.experiments.kafka;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexost.experiments.kafka.handler.MessageHandler;
import com.nexost.experiments.kafka.message.Message;

//@Component
public class Communicator {
	
	@Autowired
	private Sender sender;

	public Communicator() {
	}

	public void run() {
		System.out.println("COMMUNICATOR ON TARGET.");
		DefaultKafkaListener.register(new MessageHandler() {
			@Override
			public void handle(Message<String> message) {
				System.out.println("Message received and handled by handler 1: " + message.toString());
			}
		});
		DefaultKafkaListener.register(new MessageHandler() {
			@Override
			public void handle(Message<String> message) {
				System.out.println("Message received and handled by handler 2: " + message.toString());
			}
		});
		//sender.send(new Message<String>("Test", "Hello world"));
	}

}
