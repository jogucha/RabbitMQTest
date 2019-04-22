package com.contentparadigm.rabbitmq;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

public class Sender {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ResourceBundle res = ResourceBundle.getBundle(Constants.APP_PROP);
		ConnectionFactory rabbmqFact = new ConnectionFactory();
		rabbmqFact.setHost(res.getString(Constants.RABB_CLUS_HOST));
		rabbmqFact.setPort(Integer.parseInt(res.getString(Constants.RABB_CLUS_PORT)));
		rabbmqFact.setVirtualHost(res.getString(Constants.RABB_CLUS_VHOST_INTERNET_NAME));
		rabbmqFact.setUsername(res.getString(Constants.RABB_CLUS_USER_PRODUCER_OLBACTIVITY_NAME));
		rabbmqFact.setPassword(res.getString(Constants.RABB_CLUS_USER_PRODUCER_OLBACTIVITY_PWD));
		Connection rabbmqCon = null;
		Channel rabbmqChann = null;
		String theRoutingKey = "WEB_OLB.LOGIN.EXECUTED.200";
		String message = "{\"message\":\"The fist message in the Queue\"}";
		try {
			rabbmqCon = rabbmqFact.newConnection();
			rabbmqChann = rabbmqCon.createChannel();
			rabbmqChann.exchangeDeclare(res.getString(Constants.RABB_CLUS_EXCH_OLBACTIVITY_NAME), "topic", true);
			rabbmqChann.queueDeclare(res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_NAME),
					false, false, false, null);
			rabbmqChann.queueBind(res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_NAME), 
					res.getString(Constants.RABB_CLUS_EXCH_OLBACTIVITY_NAME), 
					res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_BIND_ROUTKEY));

			rabbmqChann.basicPublish(res.getString(Constants.RABB_CLUS_EXCH_OLBACTIVITY_NAME),
					theRoutingKey, new AMQP.BasicProperties.Builder()
					.timestamp(new Date(new Timestamp(System.currentTimeMillis()).getTime()))
					.contentType("application/json")
					.build()
					, message.getBytes());
			System.out.println("Sent: " + message);
		}
		catch (Exception e) {			
				System.out.println("Error connecting to RabbitMQ: " + e.getMessage() + 
				"\n" + e.getCause()); 
		}
		finally {
			if (rabbmqChann != null) if (rabbmqChann.isOpen()) rabbmqChann.close();
			if (rabbmqCon != null) if (rabbmqCon.isOpen()) rabbmqCon.close();
		}

	}

}
