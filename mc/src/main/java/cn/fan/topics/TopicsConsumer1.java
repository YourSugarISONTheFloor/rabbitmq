package cn.fan.topics;

import cn.fan.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class TopicsConsumer1 {
	public static void main(String[] args) throws IOException {
		//创建链接 Connection
		Connection connection = RabbitmqUtil.getConnection();
		//创建 Channel
		Channel channel = connection.createChannel();
		//接收消息
		/**
		 * basicConsume(String queue, boolean autoAck, Consumer callback)
		 * 参数：
		 * 	1.queue：队列的名称
		 * 	2.autoAck：是否自动确认
		 * 	3.callback：回调函数
		 */
		String queueName1="test_topics_queue1";
		Consumer consumer=new DefaultConsumer(channel){
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
				System.out.println("TopicsConsumer1:接收到的消息：" + new String(body));
			}
		};
		channel.basicConsume(queueName1, true, consumer);
		//消费者不用关闭资源。后面还要它接着监听资源
	}
}
