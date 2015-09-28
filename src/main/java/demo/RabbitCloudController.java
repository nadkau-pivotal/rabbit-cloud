package demo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.ApplicationContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


@Controller
public class RabbitCloudController {
	
	static Logger logger = Logger.getLogger(RabbitCloudController.class);
    
	@RequestMapping(value="/publish", method=RequestMethod.GET)
	public @ResponseBody String publish(String message){
		logger.info("In publish");
		ApplicationContext ctx = RabbitCloudApplication.getApplicationContext();
		RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
		rabbitTemplate.convertAndSend(message);
		logger.info("Publishing..." + message);
		return "Published " + message;
	}
    
	@RequestMapping(value="/receive", method=RequestMethod.GET)
	public @ResponseBody String receive(){
		logger.info("In receive");
		ApplicationContext ctx = RabbitCloudApplication.getApplicationContext();
		RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean(RabbitTemplate.class);
		String message = (String)rabbitTemplate.receiveAndConvert(RabbitCloudApplication.queueName);
		if(message == null) {
			message = "No messages in queue " + RabbitCloudApplication.queueName;
		}
		else {
			logger.info("Received: " + message);		
		}
		return message;
	}
}