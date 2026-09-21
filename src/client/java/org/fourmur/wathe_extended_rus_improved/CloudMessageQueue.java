package org.fourmur.wathe_extended_rus_improved;

import java.util.ArrayList;
import java.util.List;

public final class CloudMessageQueue {
    public static final int MAX_ELEMENTS = 3;
    public static final int MESSAGE_LIFETIME = 200;

    private final List<Message> messages = new ArrayList<>();

    public void queueMessage(String message) {
        messages.add(0, new Message(message, 0));
        
        if (messages.size() == MAX_ELEMENTS + 1)
            messages.remove(MAX_ELEMENTS);
    }

	public List<Message> getMessages() {
		return new ArrayList<>(messages);
	}

    public void tick() {
        for (Message message : messages) {
            message.age++;
        }

        if (messages.size() != 0 && messages.getLast().age > MESSAGE_LIFETIME)
            messages.removeLast();
    }

    public final class Message {
        public final String message;
        public int age;

        public Message(String message, int age) {
            this.message = message;
            this.age = age;
        }
    }
}
