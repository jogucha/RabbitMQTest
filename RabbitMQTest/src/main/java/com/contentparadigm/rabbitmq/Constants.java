package com.contentparadigm.rabbitmq;

public final class Constants {
	public final static String APP_PROP = "application";
	public final static String RABB_CLUS_HOST= "rabbitmq.cluster.host";
	public final static String RABB_CLUS_PORT = "rabbitmq.cluster.port";
	public final static String RABB_CLUS_VHOST_INTERNET_NAME= "rabbitmq.cluster.vhost.internet.name";
	public final static String RABB_CLUS_USER_PRODUCER_OLBACTIVITY_NAME= "rabbitmq.cluster.users.producer.olbActivity.name";
	public final static String RABB_CLUS_USER_PRODUCER_OLBACTIVITY_PWD= "rabbitmq.cluster.users.producer.olbActivity.pwd";
	public final static String RABB_CLUS_USER_CONSUMER_OLBACTIVITY_NAME= "rabbitmq.cluster.users.consumer.olbActivity.name";
	public final static String RABB_CLUS_USER_CONSUMER_OLBACTIVITY_PWD= "rabbitmq.cluster.users.consumer.olbActivity.pwd";
	public final static String RABB_CLUS_EXCH_OLBACTIVITY_NAME= "rabbitmq.cluster.exchange.olbActivity.name";
	public final static String RABB_CLUS_QUEUE_OLBACTIVITY_NAME= "rabbitmq.cluster.queue.olbActivity.name";
	public final static String RABB_CLUS_QUEUE_OLBACTIVITY_BIND_ROUTKEY= "rabbitmq.cluster.queue.olbActivity.bind.routingKey";
	
	private Constants() {
		throw new AssertionError();
	}

}
