package com.hackathon.webservice.notify;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.hackathon.webservice.model.NotifyModel;
import com.hackathon.webservice.util.Config;
import com.hackathon.webservice.util.LoggerUtil;

public class EmailNotifier implements MessageListener {

	@Autowired
	JmsTemplate jmstemplate;
	
	ActiveMQQueue  destination;
	
	@Autowired
	CustomMailSender mailSender;
	
	public EmailNotifier(){
		destination = new ActiveMQQueue("Notify");
	}
	
	@Override
	public void onMessage(Message message) {
		LoggerUtil.debug("Here we will Notify the Email Object");
		if (message instanceof ObjectMessage){
			ObjectMessage objMessage = (ObjectMessage)message;
			try {
				NotifyModel object = (NotifyModel)objMessage.getObject();
				Map map = new HashMap<>();
				for (Object obj: object.getObj()){
					map.put(obj.getClass().getSimpleName().toLowerCase(), obj);
				}
				String subject = Config.getString("hackathon.email.template."+object.getStatus()+".subject");
				LoggerUtil.debug("Object: "+object.getObj()+" result Code:"+object.getStatus());
				mailSender.sendEmail(""+object.getStatus(), object.getTo(), subject ,map);
			} catch (JMSException e) {
				LoggerUtil.error("Exception caused while getting the object", e);
				e.printStackTrace();
			}
		}
	}
	
	
	public void sendNotifier(final int status, final String to, final Serializable... seriableObject) {
		String[] recipient={to};
		sendNotifier(status, recipient, seriableObject);
	}
	
	public void sendNotifier(final int status, final String[] to, final Serializable... seriableObject) {
		MessageCreator message = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage();
				NotifyModel model = new NotifyModel(status,seriableObject);
				model.setTo(to);
				message.setObject(model);
				return message;
			}
		};
		jmstemplate.send(destination, message);
	}
	
}
