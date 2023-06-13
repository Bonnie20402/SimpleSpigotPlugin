package bonnie20402.simplespigotplugin.commands.gui;

import bonnie20402.simplespigotplugin.controllers.gui.SimpleGuiController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SimpleGuiCommand implements CommandExecutor {

    private final SimpleGuiController simpleGuiController;

    public SimpleGuiCommand(SimpleGuiController simpleGuiController) {
        this.simpleGuiController = simpleGuiController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player))return true;
        Player player = (Player) commandSender;
        simpleGuiController.open(player);
        return true;
    }
}
