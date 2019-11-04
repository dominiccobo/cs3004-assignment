package com.dominiccobo.cs3004.assignment.multiplayer.messaging;

import java.io.Serializable;

/**
 * Classic approach to creating a messaging bus message. Messages must be destined to a given topic,
 * and can have a variable payload type.
 *
 * There is nothing particularly wrong with this as the message consumer / subscriber knows or should know
 * the agreed protocol which the message is sent using.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public abstract class Message<PayloadType extends Serializable> {
    public abstract String getTopic();
    public abstract PayloadType getMessage();
}
