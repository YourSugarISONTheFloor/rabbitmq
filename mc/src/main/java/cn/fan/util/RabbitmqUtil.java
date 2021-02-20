package cn.fan.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtil {
	private static ConnectionFactory connectionFactory;

	//静态代码块在类加载的时候执行，只执行一次
	static {
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");    //ip地址默认是localhost
		connectionFactory.setPort(5672);//端口 默认值5672
		connectionFactory.setUsername("fan");//设置用户名
		connectionFactory.setPassword("123456");//设置密码
		connectionFactory.setVirtualHost("/fan");//设置虚拟机
	}

	//定义提供链接对象的方法
	public static Connection getConnection() {
		try {
			//创建一个连接对象
			//Connection connection = connectionFactory.newConnection();
			return connectionFactory.newConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//关闭通道和关闭连接的工具方法
	public static void closeConnectionAndChanel(Channel channel, Connection connection) {
		try {
			if (channel != null) channel.close();
			if (connection != null) connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}