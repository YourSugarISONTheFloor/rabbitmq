package cn.fan.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumer {
	@RabbitListener(queuesToDeclare = {@Queue(value = "Work", autoDelete = "true",durable = "false")})
	public void receive1(String body) {
		System.out.println("Work类型消费者[1]收到的消息内容为：" + body);
	}

	@RabbitListener(queuesToDeclare = @Queue(value = "Work", autoDelete = "true",durable = "false"))
	public void receive2(String body) {
		System.out.println("Work类型消费者[2]收到的消息内容为：" + body);
	}
}
