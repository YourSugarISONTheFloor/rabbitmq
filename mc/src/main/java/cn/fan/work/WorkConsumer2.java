package cn.fan.work;

import cn.fan.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


import java.io.IOException;

public class WorkConsumer2 {
	public static void main(String[] args) throws IOException {
		//创建链接 Connection
		Connection connection = RabbitmqUtil.getConnection();
		//创建 Channel
		Channel channel = connection.createChannel();
		//创建队列
		/**
		 * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
		 *	参数：
		 *		1.	queue：队列名称
		 *		2.	durable：是否持久化，当rabbitmq重启之后，是否还在
		 *		3.	exclusive：
		 *				是否独占。只能有一个消费者监听这个队列
		 *				当Connection关闭时，是否删除队列
		 *		4.	autoDelete：是否自动删除，当没有Consumer时自动删除
		 *		5.	arguments：参数
		 */
		//如果没有一个名叫hello_word的队列时，会自动创建一个队列
		channel.queueDeclare("queue_work", false, false, true, null);
		//接收消息
		/**
		 * basicConsume(String queue, boolean autoAck, Consumer callback)
		 * 参数：
		 * 	1.queue：队列的名称
		 * 	2.autoAck：是否自动确认
		 * 	3.callback：回调函数
		 */
		Consumer consumer = new DefaultConsumer(channel) {
			/**
			 * 回调方法，当消息接收到会自动执行该方法
			 * @param consumerTag   标签
			 * @param envelope      消息传递过程中的整个信息
			 * @param properties    消息传递的一些属性
			 * @param body          消息队列中取出的消息
			 * @throws IOException
			 */
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("WorkConsumer2:body = " + new String(body));
			}
		};
		channel.basicConsume("queue_work", true, consumer);
		//消费者不用关闭资源。后面还要它接着监听资源
	}

}
