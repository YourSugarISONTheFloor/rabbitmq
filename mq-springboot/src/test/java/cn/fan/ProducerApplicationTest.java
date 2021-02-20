package cn.fan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerApplicationTest {
	//注入RabbitTemplate
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
		System.out.println(rabbitTemplate);
	}

	//第一种模式发送消息
	//hello word
	@Test
	public void hello() {
		rabbitTemplate.convertAndSend("Hello", "hello word");
		System.out.println("消息发送成功");
	}

	//第二种模式发送消息
	//work
	@Test
	public void work() {
		for (int i = 0; i < 10; i++) {
			rabbitTemplate.convertAndSend("Work", "Work模型" + i);
		}
		System.out.println("消息全部发送成功");
	}

	//第三种模式发送消息
	//Publish/Subscribe
	@Test
	public void publish() {
		rabbitTemplate.convertAndSend("logs", "", "Publish/Subscribe模型");
		System.out.println("消息发送成功");
	}

	//第四种模式发送消息
	//Routing-Direct
	@Test
	public void direct() {
		rabbitTemplate.convertAndSend("directs", "info", "Routing-Direct模型路由key为：info");
		rabbitTemplate.convertAndSend("directs", "warm", "Routing-Direct模型路由key为：warm");
		rabbitTemplate.convertAndSend("directs", "debug", "Routing-Direct模型路由key为：debug");
		rabbitTemplate.convertAndSend("directs", "error", "Routing-Direct模型路由key为：error");
		System.out.println("消息发送成功");
	}

	//第五种模式发送消息
	//Routing-Topic
	@Test
	public void topic() {
		rabbitTemplate.convertAndSend("topics", "user.name.age", "Routing-Topic模型user.name.age的路由key");
		rabbitTemplate.convertAndSend("topics", "user.name", "Routing-Topic模型user.name的路由key");
		rabbitTemplate.convertAndSend("topics", "user", "Routing-Topic模型user的路由key");
		System.out.println("消息发送成功");
	}
}
