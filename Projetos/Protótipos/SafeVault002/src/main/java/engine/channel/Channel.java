package engine.channel;

import java.util.ArrayList;

import engine.channel.subscribable.Subscriber;

public class Channel {
	
	ArrayList<Subscriber> subscribers;
	
	public Channel() {
		subscribers = new ArrayList<Subscriber>();
	}
	
	public void subscribe(Subscriber subscriber) {
		subscribers.add(subscriber);
	}
	
	public void notify(String message) {
		subscribers.forEach((subscriber) -> subscriber.notify(message));
	}

}
