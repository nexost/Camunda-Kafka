package com.nexost.experiments.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.nexost.experiments.kafka.handler.MessageHandler;
import com.nexost.experiments.kafka.message.Message;

@Component
@EnableBinding(Sink.class)
public class DefaultKafkaListener extends KafkaListener {

	@Override
	@StreamListener(target = Sink.INPUT)
	public void messageReceived(String message) {
		Message<String> kafkaMessage = new Message<String>("DefaultKafkaMessage", message);
		for (MessageHandler listenerHandler : handlers) {
			listenerHandler.handle(kafkaMessage);
		}
	}
	
}
