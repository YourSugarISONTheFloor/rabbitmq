package cn.fan.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {
	//队列的名称
	public static final String QUEUE_NAME = "Hello";

	/**
	 * 创建一个队列
	 */
	@Bean
	public Queue hello() {
		/**
		 * Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments)
		 *	参数：
		 *		1.	name：队列名称
		 *		2.	durable：是否持久化，当rabbitmq重启之后，是否还在
		 *		3.	exclusive：
		 *				是否独占。只能有一个消费者监听这个队列
		 *				当Connection关闭时，是否删除队列
		 *		4.	autoDelete：是否自动删除，当没有Consumer时自动删除
		 *		5.	arguments：参数
		 *	默认值
		 *	Queue(name, true, false, false);
		 */
		//return new Queue("Hello", false, false, true, null);

		//定义一个不持久化的队列
		return QueueBuilder.nonDurable(QUEUE_NAME).autoDelete().build();
	}
}
