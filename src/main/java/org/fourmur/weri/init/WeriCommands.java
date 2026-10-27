package org.fourmur.weri.init;

import net.minecraft.server.command.ServerCommandSource;

import org.fourmur.weri.command.ForceWendigoModelCommand;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class WeriCommands {
    public static void initialize() {
        register(ForceWendigoModelCommand.COMMAND);
    }

    private static void register(LiteralArgumentBuilder<ServerCommandSource> command) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(command);
        });
    }
}
