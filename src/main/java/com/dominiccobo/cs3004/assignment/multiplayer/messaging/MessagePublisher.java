package com.dominiccobo.cs3004.assignment.multiplayer.messaging;

/**
 * A publisher is someone who's sole interest it is to send a message of a given topic to a bus or broker
 * and forget about it.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class MessagePublisher {

    private final String topic;
    private final MessageBroker bus;

    public MessagePublisher(String topic, MessageBroker bus) {
        this.topic = topic;
        this.bus = bus;
        this.bus.register(this);
    }

    void publish(Message message) {
        bus.send(this, message);
    }

    public final String getTopic() {
        return topic;
    }
}
