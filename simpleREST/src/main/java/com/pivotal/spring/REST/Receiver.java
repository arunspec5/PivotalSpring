package com.pivotal.spring.REST;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class Receiver implements MessageListener {

	//******Code to retrive the Java Object**************///
/*		private CountDownLatch latch = new CountDownLatch(1);
		@Autowired
		RabbitTemplate rabbitTemplate;
		public void receiveMessage(User user) {
			System.out.println("simpleREST caught it-->"+user.getUserName());
			latch.countDown();
		}

		public CountDownLatch getLatch() {
			return latch;
		}
*/
	
	@Override
	public void onMessage(Message message) {
			JsonMessageConverter jmc = new JsonMessageConverter();
        	User user = (User)jmc.fromMessage(message);
			System.out.println("Received <" + user.getUserId() + "    "+ user.getUserName() + ">");
		
	}
}