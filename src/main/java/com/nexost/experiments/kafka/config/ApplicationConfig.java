package com.nexost.experiments.kafka.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ApplicationConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void createFlow() {
		/*
		 * Currently the order.bpmn in src/main/resources is used You can either define
		 * flows as BPMN 2.0 XML or by Model API:
		 * 
		 * engine.getRepositoryService().createDeployment() //
		 * .addModelInstance("order.bpmn", Bpmn.createProcess("order").executable() //
		 * .startEvent().message("OrderPlacedEvent")
		 * .sendTask().name("Fetch goods").camundaDelegateExpression(exp(
		 * FetchGoodsAdapter.class))
		 * .receiveTask().name("Goods fetched").message("GoodsFetchedEvent")
		 * .camundaOutputParameter("pickId",
		 * "#{PAYLOAD_GoodsFetchedEvent.jsonPath('$.pickId').stringValue()}")
		 * .sendTask("payment").name("Retrieve payment").camundaDelegateExpression(exp(
		 * RetrievePaymentAdapter.class))
		 * .receiveTask().name("Payment received").message("PaymentReceivedEvent")
		 * .sendTask().name("Ship goods").camundaDelegateExpression(exp(ShipGoodsAdapter
		 * .class)) .receiveTask().name("Goods shipped").message("GoodsShippedEvent")
		 * .endEvent().camundaExecutionListenerDelegateExpression("end",
		 * exp(OrderCompletedAdapter.class)) .done() ).deploy();
		 */
	}

	@SuppressWarnings("rawtypes")
	public String exp(Class delegateClass) {
		String[] beanNames = applicationContext.getBeanNamesForType(delegateClass);
		if (beanNames.length > 1) {
			throw new RuntimeException("More than one Spring bean found for type " + delegateClass);
		}
		return "#{" + beanNames[0] + "}";
	}
	
	@Bean
    public MessageChannel output() {
        return new PublishSubscribeChannel();
    }

	/*
	 * .boundaryEvent().timerWithDuration("PT5S").endEvent().
	 * compensateEventDefinition().compensateEventDefinitionDone()
	 * .moveToNode("payment")
	 */
}
