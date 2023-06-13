package bonnie20402.simplespigotplugin.commands.gui;

import bonnie20402.simplespigotplugin.controllers.gui.SimplePlayerGuiController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SimplePlayerGuiCommand implements CommandExecutor {

    private final SimplePlayerGuiController simplePlayerGuiController;

    public SimplePlayerGuiCommand(SimplePlayerGuiController simplePlayerGuiController) {
        this.simplePlayerGuiController = simplePlayerGuiController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player))return true;
        Player player = (Player) commandSender;
        simplePlayerGuiController.setPlayer(player);
        simplePlayerGuiController.open(player);
        return true;
    }
}
