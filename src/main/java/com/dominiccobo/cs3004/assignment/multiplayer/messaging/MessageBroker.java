package com.dominiccobo.cs3004.assignment.multiplayer.messaging;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Generic simple message bus for decoupling all components from each other.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class MessageBroker {

    private ConcurrentHashMap<String, TopicSubscribers> subscribers = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, TopicPublishers> publishers = new ConcurrentHashMap<>();

    void register(MessageSubscriber subscriber) {
        final String topic = subscriber.getTopic();
        final TopicSubscribers subs = subscribers.get(topic);
        if (subs == null) {
            final TopicSubscribers emptyTopicSubs = new TopicSubscribers();
            emptyTopicSubs.addSubscriber(subscriber);
            subscribers.put(topic, emptyTopicSubs);
        }
        else {
            subs.addSubscriber(subscriber);
        }
    }
    void register(MessagePublisher publisher) {
        final String topic = publisher.getTopic();
        final TopicPublishers subs = publishers.get(topic);
        if (subs == null) {
            final TopicPublishers topicPublishers = new TopicPublishers();
            topicPublishers.addPublisher(publisher);
            publishers.put(topic, topicPublishers);
        }
        else {
            subs.addPublisher(publisher);
        }
    }

    synchronized void send(MessagePublisher publisher, Message message) {
        final TopicSubscribers messageSubscribers = subscribers.get(publisher.getTopic());
        messageSubscribers.sendMessage(message);
    }

    static class TopicPublishers {

        private final HashSet<MessagePublisher> publishers;

        public TopicPublishers() {
            publishers = new HashSet<>();
        }

        synchronized void addPublisher(MessagePublisher pub) {
            publishers.add(pub);
        }
    }

    static class TopicSubscribers {

        private final HashSet<MessageSubscriber> subscribers;

        public TopicSubscribers() {
            subscribers = new HashSet<>();
        }

        synchronized void addSubscriber(MessageSubscriber pub) {
            subscribers.add(pub);
        }

        void sendMessage(Message message) {
            subscribers.forEach(subscriber -> {
                subscriber.sendMessage(message);
            });
        }
    }
}
