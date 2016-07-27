package com.pivotal.spring.REST;

import javax.sql.DataSource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;



@Configuration
@Profile("cloud")
public class DataSourceConfig extends AbstractCloudConfig {
	@Bean(name = "cf-spring-db")
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
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
		container.setMessageListener(new Receiver());
		container.setMessageConverter(jsonMessageConverter());
		return container;
	}
	   @Bean
	    Receiver receiver() {
	        return new Receiver();
	    }
	   @Bean
	    public MessageConverter jsonMessageConverter(){
	        return new JsonMessageConverter();
	    }

		@Bean
		MessageListenerAdapter listenerAdapter(Receiver receiver) {
			return new MessageListenerAdapter(receiver, "receiveMessage");
		}
		@Bean
		public ConnectionFactory rabbitConnectionFactory() {
			CloudFactory cloudFactory = new CloudFactory();
			Cloud cloud = cloudFactory.getCloud();
			AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud.getServiceInfo("rabbit-mq");
			String serviceID = serviceInfo.getId();
			return cloud.getServiceConnector(serviceID, ConnectionFactory.class, null);
		}
		@Bean
		public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
			RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(jsonMessageConverter());
			return rabbitTemplate;
		}
}
