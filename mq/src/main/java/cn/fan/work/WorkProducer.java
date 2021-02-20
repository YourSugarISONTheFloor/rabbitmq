package cn.fan.work;

import cn.fan.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;


/**
 * 发送消息端
 */

public class WorkProducer {
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
		//发送消息
		/**
		 * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
		 * 参数：
		 * 	1.exchange：交换机的名称。简单模式下，交换机会使用默认的
		 * 	2.routingKey：路由key
		 * 	3.props：配置信息
		 * 	4.body：真实的消息发送数据
		 */
		for (int i = 0; i < 10; i++) {
			String msg = "hello rabbitmq----->第" + i + "条消息";
			channel.basicPublish("", "queue_work", null, msg.getBytes());
		}
		//关闭连接
		RabbitmqUtil.closeConnectionAndChanel(channel, connection);
	}
}
