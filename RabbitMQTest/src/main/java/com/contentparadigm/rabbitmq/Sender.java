package com.contentparadigm.rabbitmq;

import java.util.ResourceBundle;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ResourceBundle res = ResourceBundle.getBundle(Constants.APP_PROP);
		ConnectionFactory rabbmqFact = new ConnectionFactory();
		rabbmqFact.setHost(res.getString(Constants.RABB_CLUS_HOST));
		rabbmqFact.setPort(Integer.parseInt(res.getString(Constants.RABB_CLUS_PORT)));
		rabbmqFact.setUsername(res.getString(Constants.RABB_CLUS_USER));
		rabbmqFact.setPassword(res.getString(Constants.RABB_CLUS_PASS));
		rabbmqFact.setVirtualHost(res.getString(Constants.RABB_CLUS_VHOST));
		Connection rabbmqCon = null;
		Channel rabbmqChann = null;
		String message = "The fist message in the Queue";
		try {
			rabbmqCon = rabbmqFact.newConnection();
			rabbmqChann = rabbmqCon.createChannel();
			//rabbmqChann.exchangeDeclare(RABB_CLUS_EXCH_NOTIF_NAME, "direct", true);
			rabbmqChann.queueDeclare(res.getString(Constants.RABB_CLUS_QUEUE_NAME), false, false, false, null);
			rabbmqChann.basicPublish("", res.getString(Constants.RABB_CLUS_QUEUE_NAME), null, message.getBytes());
			System.out.println("Sent: " + message);
			}
		catch (Exception e) {
			System.out.println("Error connecting to RabbitMQ: " + e.getMessage() + 
					"\n" + e.getCause()); 
		}
		finally {
			if (rabbmqChann != null) rabbmqChann.close();
			if (rabbmqCon != null) rabbmqCon.close();
		}

	}

}
