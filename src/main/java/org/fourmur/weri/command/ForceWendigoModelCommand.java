package org.fourmur.weri.command;

import org.fourmur.weri.init.WeriAttachments;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public final class ForceWendigoModelCommand {
    public static final LiteralArgumentBuilder<ServerCommandSource> COMMAND =
        CommandManager.literal("forceWendigoModel")
            .requires(sender -> sender.hasPermissionLevel(2))
            .then(CommandManager.argument("player", EntityArgumentType.player())
                .executes(ForceWendigoModelCommand::execute)
            );

    private static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
        boolean forceWendigoModel = player.getAttachedOrCreate(WeriAttachments.FORCE_WENDIGO_MODEL);

        context.getSource().sendFeedback(() -> Text.literal("Set wendigo model flag to " + !forceWendigoModel), false);

        player.setAttached(WeriAttachments.FORCE_WENDIGO_MODEL, !forceWendigoModel);

        return Command.SINGLE_SUCCESS;
    }
}
