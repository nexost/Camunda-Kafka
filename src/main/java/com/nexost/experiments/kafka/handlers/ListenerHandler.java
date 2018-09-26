package com.nexost.experiments.kafka.handlers;

import org.springframework.cloud.stream.annotation.StreamListener;

public abstract class ListenerHandler {

	@StreamListener
	public abstract void handle(String message);
	
}
