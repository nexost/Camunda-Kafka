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

    // The sender places the message type under the 'messageType' header.
    // Using 'type' here prevents the listener from receiving any messages
    // because the condition never matches. Adjust the header name so that
    // incoming messages are correctly routed to the registered handlers.
    @StreamListener(target = Sink.INPUT, condition = "headers['messageType']=='bogey'")
	public static void messageReceived(String message) {
		
		for (ListenerHandler listenerHandler : handlers) {
			listenerHandler.handle(message);
		}
	}
	
	public static void register(ListenerHandler h) {
		handlers.add(h);
	}

}
