package org.fourmur.weri.index;

import org.fourmur.weri.command.ForceBerserkModelCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class Commands {
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(ForceBerserkModelCommand.COMMAND);
        });
    }
}
