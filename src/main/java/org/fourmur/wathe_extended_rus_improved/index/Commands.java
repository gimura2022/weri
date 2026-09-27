package org.fourmur.wathe_extended_rus_improved.index;

import org.fourmur.wathe_extended_rus_improved.command.ForceBerserkModelCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class Commands {
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(ForceBerserkModelCommand.COMMAND);
        });
    }
}
