package demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
@SpringBootApplication
public class RabbitCloudApplication {
	static ApplicationContext ctx = null;

	public final static String queueName = "myQueue";
	public final static String exchangeName = "myExchange";

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	
<<<<<<< HEAD
=======
	@Configuration
	@Profile("dev")
	public static class DevContext {
		// dev beans
		
		@Bean
		public ConnectionFactory connectionFactory() {
			// Read the host, port, username and password from a config file
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
					"localhost");
			connectionFactory.setUsername("guest");
			connectionFactory.setPassword("guest");
			return connectionFactory;
		}
	}

	@Configuration
	@Profile("cloud")
	public static class CloudContext {
		// cloud beans

		@Profile("cloud")
		@Bean
		public ConnectionFactory connectionFactory() {
			
			CloudFactory cloudFactory = new CloudFactory();
			// Return a cloud suitable for the current environment
			Cloud cloud = cloudFactory.getCloud();
			AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud
					.getServiceInfo("my-rabbit");
			String serviceID = serviceInfo.getId();
			return cloud.getServiceConnector(serviceID,
					ConnectionFactory.class, null);
		}
	}
	
>>>>>>> 8681f6587361e02a3855e089afd8d027825d48d9
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
	
	public static ApplicationContext getApplicationContext() {
		if (ctx == null) {
			ctx = SpringApplication.run(RabbitCloudApplication.class);
		}
		return ctx;
	}

	public static void main(String[] args) {
		ctx = SpringApplication.run(RabbitCloudApplication.class, args);
	}
}
