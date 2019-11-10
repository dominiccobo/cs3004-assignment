package com.dominiccobo.cs3004.assignment.core;

import java.util.UUID;

/**
 * Identifier for a sessionInstance.
 */
public class SessionIdentifier {

    private final String identifier;

    private SessionIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public static SessionIdentifier newInstance() {
        return new SessionIdentifier(UUID.randomUUID().toString());
    }

    String get() {
        return identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
