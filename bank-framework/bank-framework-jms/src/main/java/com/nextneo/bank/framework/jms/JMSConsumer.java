package com.nextneo.bank.framework.jms;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class JMSConsumer {

	public void send(String lookup, String textMessage) throws Exception {
		// Direct configuration option in java
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.setProperty("java.naming.provider.url", "tcp://localhost:61616");
		properties.setProperty("queue.email", "queue.email");
		InitialContext context = new InitialContext(properties);

		//InitialContext context = new InitialContext();

		// import package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = cf.createConnection();
		// Connection connection = cf.createConnection("user", "senha");

		connection.start();

		// create context, factory, connection
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination queue = (Destination) context.lookup(lookup);

		MessageProducer producer = session.createProducer(queue);
		
		System.out.println("producer... message");

		for (int i = 0; i < 100; i++) {
			Message message = session.createTextMessage(textMessage);
			producer.send(message);
		}

		session.close();
		connection.close();
		context.close();

	}

}
