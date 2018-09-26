package com.nexost.experiments.kafka;

import java.util.ArrayList;
import java.util.List;

import com.nexost.experiments.kafka.handler.MessageHandler;
import com.nexost.experiments.kafka.message.Message;

public abstract class KafkaListener {

	protected static List<MessageHandler> handlers = new ArrayList<MessageHandler>();
	
	public abstract void messageReceived(String message);
	
	public static void register(MessageHandler handler) {
		handlers.add(handler);
	};
	
}
