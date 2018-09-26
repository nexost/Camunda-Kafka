package com.nexost.experiments.kafka.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ListenerHandlerManager {
	
	private static List<ListenerHandler> handlers = new ArrayList<ListenerHandler>();

	@StreamListener(target = Sink.INPUT, condition = "headers['type']=='bogey'")
	public static void messageReceived(String message) {
		
		for (ListenerHandler listenerHandler : handlers) {
			listenerHandler.handle(message);
		}
	}
	
	public static void register(ListenerHandler h) {
		handlers.add(h);
	}

}
