package com.contentparadigm.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import java.util.ResourceBundle;


public class Receiver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ResourceBundle res = ResourceBundle.getBundle(Constants.APP_PROP);
		ConnectionFactory rabbmqFact = new ConnectionFactory();
		rabbmqFact.setHost(res.getString(Constants.RABB_CLUS_HOST));
		rabbmqFact.setPort(Integer.parseInt(res.getString(Constants.RABB_CLUS_PORT)));
		rabbmqFact.setVirtualHost(res.getString(Constants.RABB_CLUS_VHOST_INTERNET_NAME));
		rabbmqFact.setUsername(res.getString(Constants.RABB_CLUS_USER_CONSUMER_OLBACTIVITY_NAME));
		rabbmqFact.setPassword(res.getString(Constants.RABB_CLUS_USER_CONSUMER_OLBACTIVITY_PWD));
		Connection rabbmqCon = null;
		Channel rabbmqChann = null;
		try {
			rabbmqCon = rabbmqFact.newConnection();
			rabbmqChann = rabbmqCon.createChannel();
			rabbmqChann.exchangeDeclare(res.getString(Constants.RABB_CLUS_EXCH_OLBACTIVITY_NAME), "topic", true);
			rabbmqChann.queueDeclare(res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_NAME),
					false, false, false, null);
			rabbmqChann.queueBind(res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_NAME), 
					res.getString(Constants.RABB_CLUS_EXCH_OLBACTIVITY_NAME), 
					res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_BIND_ROUTKEY));
	
			System.out.println("[*] Waiting for messages. To exit press CTRL+C");
			DeliverCallback rabbmqDelCallBack = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println("[x] Received '" + message + "'");
			};
			rabbmqChann.basicConsume(res.getString(Constants.RABB_CLUS_QUEUE_OLBACTIVITY_NAME), 
					true, rabbmqDelCallBack, consumerTag -> {});
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
