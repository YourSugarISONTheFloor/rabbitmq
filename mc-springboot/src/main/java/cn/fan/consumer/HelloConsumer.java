package cn.fan.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//代表这是一个RabbitMQ中的消费者，queuesToDeclare表示没有队列自动创建队列，@Queue("hello")用来创建队列，里面可以设置队列名字等
//@Queue默认创建的队列是持久化，非独占
//@RabbitListener可以加在类上，也可以加在方法上。
//加在方法上，可以省去@RabbitHandler。表示该方法就是队列的回调方法
@RabbitListener(queuesToDeclare = {@Queue(value = "Hello",autoDelete = "true",durable = "false")})
public class HelloConsumer {

	//代表取出消息的回调方法是通过receive来执行的
	@RabbitHandler
	public void receive(String body) {
		System.out.println("Hello类型消费者收到的消息内容为：" + body);
	}
}
