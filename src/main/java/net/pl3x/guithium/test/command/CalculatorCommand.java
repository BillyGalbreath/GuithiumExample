package net.pl3x.guithium.test.command;

import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.player.WrappedPlayer;
import net.pl3x.guithium.test.gui.Calculator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class CalculatorCommand implements TabExecutor {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof org.bukkit.entity.Player bukkitPlayer)) {
            sender.sendMessage("Player only command.");
            return true;
        }

        WrappedPlayer player = Guithium.api().getPlayerManager().get(bukkitPlayer.getUniqueId());

        Calculator calculator = new Calculator();
        calculator.open(player);
        return true;
    }
}
