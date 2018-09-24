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

@Component
@EnableBinding(Source.class)
public class Communicator {
	
	@Autowired
	private MessageChannel output;

	public Communicator() {
	}

	public void run() {
		System.out.println("COMMUNICATOR ON TARGET.");
		System.out.println(output);
		output = new PublishSubscribeChannel();
		System.out.println(output);
		send(new Message<String>("Test", "Hello world"));
	}
	
	public void send(Message<?> m) {
		try {
			// avoid too much magic and transform ourselves
			ObjectMapper mapper = new ObjectMapper();
			String jsonMessage = mapper.writeValueAsString(m);
			// wrap into a proper message for the transport (Kafka/Rabbit) and send it
			output.send(MessageBuilder.withPayload(jsonMessage).setHeader("messageType", m.getMessageType()).build());
		} catch (Exception e) {
			throw new RuntimeException("Could not tranform and send message due to: " + e.getMessage(), e);
		}
	}

}
