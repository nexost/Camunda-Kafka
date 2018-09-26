package com.nexost.experiments.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
//@EnableBinding(SinkTwo.class)
public class Listener {

	private String channel = SinkTwo.INPUT;
	
	//@StreamListener(target = "#{listener.channel}")
	//@Transactional
	public void messageReceived(Message<String> message) throws JsonParseException, JsonMappingException, IOException {
		// Message<Order> message = new ObjectMapper().readValue(messageJson, new
		// TypeReference<Message<Order>>(){});
		//String order = message.toString();

		System.out.println("Message received: " + message.toString());
	}
}
