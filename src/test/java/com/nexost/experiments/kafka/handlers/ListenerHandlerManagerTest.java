package com.nexost.experiments.kafka.handlers;

import org.junit.Test;

public class ListenerHandlerManagerTest {
	@Test
    public void test() {
		
		ListenerHandler creditInsuranceAgreement = new ListenerHandler() {
			@Override
			public void handle(String message) {
				if(message.split(" ")[0].equals("event1"))
					System.out.println("Hander1 (creditInsuranceAgreement): " + message);
			}
		};
		
		ListenerHandlerManager.register(creditInsuranceAgreement);
		
		ListenerHandlerManager.register(new ListenerHandler() {
			@Override
			public void handle(String message) {
				if(message.split(" ")[0].equals("event2"))
					System.out.println("Hander2: " + message);
			}
		});
		
		ListenerHandlerManager.register(new ListenerHandler() {
			
			@Override
			public void handle(String message) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ListenerHandlerManager.messageReceived("event1 message1");
		ListenerHandlerManager.messageReceived("event1 message2");
		ListenerHandlerManager.messageReceived("event2 message3");
		ListenerHandlerManager.messageReceived("event1 message4");
    }
}
