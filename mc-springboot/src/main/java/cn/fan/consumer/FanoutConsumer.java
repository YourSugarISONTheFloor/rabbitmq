package cn.fan.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {
	//@QueueBinding将交换机和队列绑定
	//@QueueBinding()中的@Queue不指定名字的话，就是一个临时队列。
	//@QueueBinding()中的exchange表示绑定交换机
	//@Exchange设置交换机的属性
	@RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "fanout_queue_1", autoDelete = "true", durable = "false"), exchange = @Exchange(value = "logs", type = "fanout", autoDelete = "true"))})
	public void receive1(String message) {
		System.out.println("message1：" + message);
	}

	@RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "fanout_queue_2", autoDelete = "true", durable = "false"), exchange = @Exchange(value = "logs", type = "fanout", autoDelete = "true"))})
	public void receive2(String message) {
		System.out.println("message2：" + message);
	}
}
