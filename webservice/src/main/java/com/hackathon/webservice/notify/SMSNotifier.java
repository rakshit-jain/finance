package com.hackathon.webservice.notify;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.hackathon.webservice.util.Config;
import com.hackathon.webservice.util.LoggerUtil;
import com.hackathon.webservice.util.ObjectRepository;

public class SMSNotifier implements MessageListener {

	@Autowired
	JmsTemplate jmstemplate;

	ActiveMQQueue destination;

	@Autowired
	SMSGateway smsGateway;

	public SMSNotifier() {
		destination = new ActiveMQQueue("SMSNotify");
	}

	@Override
	public void onMessage(Message message) {
		LoggerUtil.debug("Trying to Send SMS as per message:" + message);
		if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			try {
				SMSNotifyModel object = (SMSNotifyModel) objMessage.getObject();
				smsGateway.send(object.getMessage(), object.getTo());
			} catch (JMSException e) {
				LoggerUtil.error("Exception caused while getting the object", e);
				e.printStackTrace();
			}
		}
	}

	public void sendNotifier(final int status, final String to, final Serializable... seriableObject) {
		SMSMessageCreator message = new SMSMessageCreator(status, to, seriableObject);
		if (message.getFinalTextMessage() != null)
			jmstemplate.send(destination, message);
	}

}

class SMSMessageCreator implements MessageCreator {

	private int status;
	private String to;
	private Serializable[] objects;
	private String finalTextMessage;

	public String getFinalTextMessage() {
		return finalTextMessage;
	}

	public SMSMessageCreator(int status, String to, Serializable... objects) {
		this.status = status;
		this.to = to;
		this.objects = objects;
		this.finalTextMessage= Config.getString("hackathon.sms." + status + ".template", null);
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		ObjectMessage message = session.createObjectMessage();
		SMSNotifyModel model = new SMSNotifyModel();// (status,seriableObject);
		model.setTo(to);
		Context context = new Context();
		if (objects != null)
			for (Serializable object : objects) {
				context.setVariable(object.getClass().getSimpleName().toLowerCase(), object);
			}

		final TemplateEngine templateEngine = ObjectRepository.getBean("stringtemplateEngine", TemplateEngine.class);
		String finalMessage = templateEngine.process(finalTextMessage, context);

		model.setMessage(finalMessage);
		message.setObject(model);
		return message;
	}

}
