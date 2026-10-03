package org.fourmur.weri.init;

import org.fourmur.weri.command.ForceWendigoModelCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class WeriCommands {
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(ForceWendigoModelCommand.COMMAND);
        });
    }
}
