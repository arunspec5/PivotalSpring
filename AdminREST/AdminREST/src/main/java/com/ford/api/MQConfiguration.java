package com.ford.api;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.pivotal.spring.REST.User;

@Configuration
public class MQConfiguration {

final static String queueName = "spring-boot";

	

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	   @Bean
	    MessageListener receiver() {
	       
				MessageListener ml= (message) -> {
					JsonMessageConverter jmc = new JsonMessageConverter();
			    	User user = (User)jmc.fromMessage(message);
					System.out.println("Received <" + user.getUserId() + "    "+ user.getUserName() + ">");
			};
			return ml;
	    }

		@Bean
		MessageListenerAdapter listenerAdapter(MessageListener receiver) {
			return new MessageListenerAdapter(receiver, "receiveMessage");
		}
		@Bean
		@Primary
		public ConnectionFactory rabbitConnectionFactory() {
			CloudFactory cloudFactory = new CloudFactory();
			Cloud cloud = cloudFactory.getCloud();
			AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud.getServiceInfo("rabbit-mq");
			String serviceID = serviceInfo.getId();
			return cloud.getServiceConnector(serviceID, ConnectionFactory.class, null);
		}
		@Bean
		public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
			return new RabbitTemplate(connectionFactory);
		}
}
