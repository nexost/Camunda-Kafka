package com.nexost.experiments.kafka.handler;

import org.springframework.cloud.stream.annotation.StreamListener;

import com.nexost.experiments.kafka.message.Message;

public abstract class MessageHandler {
	
	@StreamListener
	public abstract void handle(Message<String> message);
	
}
