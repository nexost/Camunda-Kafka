package com.nexost.experiments.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SinkTwo {
	  String INPUT = "input-two";

	  @Input(SinkTwo.INPUT)
	  SubscribableChannel input();
}
