package bonnie20402.simplespigotplugin.commands.cuboid;

import bonnie20402.simplespigotplugin.controllers.cuboid.CuboidController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CuboidCommand implements CommandExecutor {
    private final CuboidController cuboidController;

    public CuboidCommand(CuboidController cuboidController) {
        this.cuboidController = cuboidController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player))return true;
        Player player = (Player) commandSender;
        if(args.length == 0) {
            commandSender.sendMessage("Usage: /setloc A | B");
        }
        if(args[0].equalsIgnoreCase("a")) {
            cuboidController.setLocationA(player.getLocation());
            commandSender.sendMessage("Defined location A!");
        }
        if(args[0].equalsIgnoreCase("b")) {
            cuboidController.setLocationB(player.getLocation());
            commandSender.sendMessage("Defined Location B!");
        }
        if(args[0].equalsIgnoreCase("toggle")) {
            boolean isActive = cuboidController.isActive();
            cuboidController.setActive(!isActive);
            commandSender.sendMessage("Toggled it to "+!isActive);
        }
        return true;
    }
}
