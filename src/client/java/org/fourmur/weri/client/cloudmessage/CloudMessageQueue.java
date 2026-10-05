package org.fourmur.weri.client.cloudmessage;

import java.util.ArrayList;
import java.util.List;

public final class CloudMessageQueue {
    public static final int MAX_ELEMENTS = 3;
    public static final double MAX_MESSAGE_LIFETIME = 80;

    private final List<Message> messages = new ArrayList<>();

    public void queueMessage(String message) {
        messages.add(0, new Message(message, 0));

        if (messages.size() == MAX_ELEMENTS + 1) messages.remove(MAX_ELEMENTS);
    }

    public void update(float tickDelta) {
        for (Message message : messages) {
            message.age += tickDelta;
        }

        if (messages.size() != 0 && messages.getLast().age > MAX_MESSAGE_LIFETIME) messages.removeLast();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public final class Message {
        public final String message;
        public double age;

        public Message(String message, double age) {
            this.message = message;
            this.age = age;
        }
    }
}
