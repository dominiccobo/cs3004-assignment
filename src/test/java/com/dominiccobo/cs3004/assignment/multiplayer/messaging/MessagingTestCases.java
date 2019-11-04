package com.dominiccobo.cs3004.assignment.multiplayer.messaging;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class MessagingTestCases {

    public static final String SOME_SILLY_MESSAGE = "the wheel has been reinvented";
    public static final String SOME_SILLY_TOPIC = "bigNews";
    private MessageBroker fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new MessageBroker();
    }

    @Test
    public void test_whenMessageSent_TwoSubscribersReceive() {

        new MessageSubscriber<String>(SOME_SILLY_TOPIC, fixture) {
            @Override
            void sendMessage(Message<String> message) {
                assertThat(message.getMessage()).isEqualTo(SOME_SILLY_MESSAGE);
            }
        };

        new MessageSubscriber<String>(SOME_SILLY_TOPIC, fixture) {
            @Override
            void sendMessage(Message<String> message) {
                assertThat(message.getMessage()).isEqualTo(SOME_SILLY_MESSAGE);
            }
        };

        MessagePublisher publisher = new MessagePublisher(SOME_SILLY_TOPIC, fixture);

        Message message = new Message<String>() {
            @Override
            public String getTopic() {
                return SOME_SILLY_TOPIC;
            }

            @Override
            public String getMessage() {
                return SOME_SILLY_MESSAGE;
            }
        };
        publisher.publish(message);
    }
}
