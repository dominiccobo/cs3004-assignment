package com.dominiccobo.cs3004.assignment.multiplayer.messaging;

import java.io.Serializable;

/**
 * A subscriber is someone who cares about a given topic and the messages that are published about it.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public abstract class MessageSubscriber<PayloadType extends Serializable> {

    private final String topic;
    private final MessageBroker messageBroker;

    public MessageSubscriber(String topic, MessageBroker messageBroker) {
        this.topic = topic;
        this.messageBroker = messageBroker;
        messageBroker.register(this);
    }

    public final String getTopic() {
        return topic;
    }

    abstract void sendMessage(Message<PayloadType> message);
}
