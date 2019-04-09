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
		rabbmqFact.setUsername(res.getString(Constants.RABB_CLUS_USER));
		rabbmqFact.setPassword(res.getString(Constants.RABB_CLUS_PASS));
		rabbmqFact.setVirtualHost(res.getString(Constants.RABB_CLUS_VHOST));
		Connection rabbmqCon = null;
		Channel rabbmqChann = null;
		rabbmqCon = rabbmqFact.newConnection();
		rabbmqChann = rabbmqCon.createChannel();
		rabbmqChann.queueDeclare(res.getString(Constants.RABB_CLUS_QUEUE_NAME), false, false, false, null);
		System.out.println("[*] Waiting for messages. To exit press CTRL+C");
		DeliverCallback rabbmqDelCallBack = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println("[x] Received '" + message + "'");
		};
		rabbmqChann.basicConsume(res.getString(Constants.RABB_CLUS_QUEUE_NAME), true, rabbmqDelCallBack, consumerTag -> {});
	}

}
