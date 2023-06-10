package bonnie20402.simplewarpplugin.commands;

import bonnie20402.simplewarpplugin.controllers.SpawnController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    private final SpawnController spawnController;

    public SpawnCommand(SpawnController spawnController) {
        this.spawnController = spawnController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are NOT a player!");
            return true;
        }
        Player player = (Player) commandSender;
        if(spawnController.getSpawn() == null) {
            commandSender.sendMessage("The spawn is not defined yet.");
            return true;
        }
        spawnController.teleportToSpawn(player);
        commandSender.sendMessage("You've been telepoted to the server spawn.");
        return true;
    }
}
