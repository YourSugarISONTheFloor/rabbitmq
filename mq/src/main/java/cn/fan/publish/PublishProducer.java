package cn.fan.publish;

import cn.fan.util.RabbitmqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class PublishProducer {
	public static void main(String[] args) throws IOException {
		//创建链接 Connection
		Connection connection = RabbitmqUtil.getConnection();
		//创建 Channel
		Channel channel = connection.createChannel();
		//创建交换机
		/**
		 * exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
		 * 参数：
		 * 	1.exchange：	交换机名称
		 * 	2.type：			交换机的类型
		 * 		DIRECT("direct"),	//定向
		 * 		FANOUT("fanout"),	//扇形：发送消息到每一个与之绑定的队列
		 * 		TOPIC("topic"),		//通配符的方式
		 * 		HEADERS("headers");	//参数匹配
		 * 	3.durable：		是否持久化
		 * 	4.autoDelete：	是否自动删除
		 * 	5.internal：	内部使用
		 * 	6.arguments：	参数
		 */
		String exchangeName="work_exchange";
		channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,false,true,false,null);
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
		String queueName1="test_work_queue1";
		String queueName2="test_work_queue2";
		channel.queueDeclare(queueName1,true,false,true,null);
		channel.queueDeclare(queueName2,true,false,true,null);
		//绑定队列和交换机
		/**
		 * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
		 * 参数：
		 * 	1.queue：		队列名称
		 * 	2.exchange：	交换机名称
		 * 	3.routingKey：	路由key
		 * 		如果交换机类型为：BuiltinExchangeType.FANOUT，那么它的routingKey设置为""。
		 * 	4.arguments：	参数列表(非必须)
		 */
		channel.queueBind(queueName1,exchangeName,"");
		channel.queueBind(queueName2,exchangeName,"");
		//发送消息
		/**
		 * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
		 * 参数：
		 * 	1.exchange：交换机的名称。简单模式下，交换机会使用默认的
		 * 	2.routingKey：路由key
		 * 	3.props：配置信息
		 * 	4.body：真实的消息发送数据
		 */
		String message="fanout类型的交换机发布的信息";
		channel.basicPublish(exchangeName,"",null,message.getBytes());
		//释放资源
		RabbitmqUtil.closeConnectionAndChanel(channel,connection);
	}
}

