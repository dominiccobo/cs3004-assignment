package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.api.PlayerReadyEvent;
import com.google.common.eventbus.Subscribe;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public interface PlayerLifecycleEvents {

    @Subscribe
    void on(PlayerReadyEvent event);
}
