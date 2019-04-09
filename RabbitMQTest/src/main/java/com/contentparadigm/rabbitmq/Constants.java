package com.contentparadigm.rabbitmq;

public final class Constants {
	public final static String APP_PROP = "application";
	public final static String RABB_CLUS_HOST= "rabbitmq.cluster.host";
	public final static String RABB_CLUS_PORT = "rabbitmq.cluster.port";
	public final static String RABB_CLUS_USER= "rabbitmq.cluster.user";
	public final static String RABB_CLUS_PASS= "rabbitmq.cluster.pass";
	public final static String RABB_CLUS_VHOST= "rabbitmq.cluster.vhost";
	public final static String RABB_CLUS_EXCH_NOTIF_NAME= "rabbitmq.cluster.exchange.notifications.name";
	public final static String RABB_CLUS_QUEUE_NAME= "rabbitmq.cluster.queue.name";
	
	private Constants() {
		throw new AssertionError();
	}

}
