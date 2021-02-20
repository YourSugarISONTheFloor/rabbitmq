package cn.fan.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {
	//队列的名称
	public static final String QUEUE_NAME = "Work";

	//创建一个队列
	@Bean
	public Queue work() {
		//定义一个不持久化的队列
		return QueueBuilder.nonDurable(QUEUE_NAME).autoDelete().build();
	}
}
