package cn.fan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingDirectConfig {
	//交换机名称
	public static final String EXCHANGE_NAME = "directs";

	//队列的名称
	public static final String QUEUE_NAME_1 = "direct_queue_1";
	public static final String QUEUE_NAME_2 = "direct_queue_2";

	//交换机
	@Bean("directs")
	public Exchange logsExchange() {
		//topicExchange中定义交换机的名称
		//durable中定义交换机是否持久化
		//build构建交换机
		return ExchangeBuilder.directExchange(EXCHANGE_NAME).autoDelete().build();
	}

	//队列1
	@Bean("directqueue1")
	public Queue directQueue1() {
		//定义一个不持久化的队列
		return  QueueBuilder.nonDurable(QUEUE_NAME_1).autoDelete().build();
	}

	//队列1
	@Bean("directqueue2")
	public Queue directQueue2() {
		//定义一个不持久化的队列
		return QueueBuilder.nonDurable(QUEUE_NAME_2).autoDelete().build();
	}
	//队列与交换机绑定的关系

	/**
	 * 1.要知道是哪个队列
	 * 2.要与那个交换机绑定
	 * 3.设置routingKey
	 * <p>
	 * 通过Bean的名字来进行注入
	 */
	@Bean
	public Binding bind1(@Qualifier("directqueue1") Queue queue, @Qualifier("directs") Exchange exchange) {
		//with设置队列与交换机通过什么路由Key来绑定
		//noargs不设置参数
		return BindingBuilder.bind(queue).to(exchange).with("info").noargs();
	}

	@Bean
	public Binding bind12(@Qualifier("directqueue1") Queue queue, @Qualifier("directs") Exchange exchange) {
		//with设置队列与交换机通过什么路由Key来绑定
		//noargs不设置参数
		return BindingBuilder.bind(queue).to(exchange).with("warm").noargs();
	}

	@Bean
	public Binding bind2(@Qualifier("directqueue2") Queue queue, @Qualifier("directs") Exchange exchange) {
		//with设置队列与交换机通过什么路由Key来绑定
		//noargs不设置参数
		return BindingBuilder.bind(queue).to(exchange).with("debug").noargs();
	}

	@Bean
	public Binding bind22(@Qualifier("directqueue2") Queue queue, @Qualifier("directs") Exchange exchange) {
		//with设置队列与交换机通过什么路由Key来绑定
		//noargs不设置参数
		return BindingBuilder.bind(queue).to(exchange).with("error").noargs();
	}
}
