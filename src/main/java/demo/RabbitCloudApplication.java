package demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class RabbitCloudApplication {

	public final static String queueName = "myQueue";
	public final static String exchangeName = "myExchange";

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	// default beans
	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setRoutingKey(queueName);
		template.setQueue(queueName);
		template.setExchange(exchangeName);
		return template;
	}
	
	@Bean
	// Every queue is bound to the default direct exchange
	public Queue queue() {
		return new Queue(queueName);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitCloudApplication.class, args);
	}
}
