package cn.fan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class TestProducer {
	//注入 RabbitTemplate
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void testHello() {
		//发送消息
		rabbitTemplate.convertAndSend("spring_queue", "hello word to spring....");
	}

	/**
	 * 发fanout消息
	 */
	@Test
	public void testFanout() {
		//发送消息
		rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "spring fanout.....");
	}

	/**
	 * 发送topics消息
	 */
	@Test
	public void testTopics() {
		//发送消息
		rabbitTemplate.convertAndSend("spring_topic_exchange", "user.name", "spring topics.....");
	}
}
