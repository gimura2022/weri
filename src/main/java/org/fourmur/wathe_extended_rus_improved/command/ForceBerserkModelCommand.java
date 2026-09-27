package org.fourmur.wathe_extended_rus_improved.command;

import org.fourmur.wathe_extended_rus_improved.index.Attachments;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public final class ForceBerserkModelCommand {
    public static final LiteralArgumentBuilder<ServerCommandSource> COMMAND =
        CommandManager.literal("forceBerserkModel")
            .requires(sender -> sender.hasPermissionLevel(2))
            .then(CommandManager.argument("player", EntityArgumentType.player())
                .executes(ForceBerserkModelCommand::execute)
            );

    private static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
        boolean forceBerserkModel = player.getAttachedOrCreate(Attachments.FORCE_BERSERK_MODEL);

        context.getSource().sendFeedback(() -> Text.literal("Set berserk model flag to " + !forceBerserkModel), false);

        player.setAttached(Attachments.FORCE_BERSERK_MODEL, !forceBerserkModel);

        return Command.SINGLE_SUCCESS;
    }
}
