package bonnie20402.simplewarpplugin.commands;

import bonnie20402.simplewarpplugin.controllers.SpawnController;
import bonnie20402.simplewarpplugin.models.SpawnModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {
    private final SpawnController spawnController;

    public SetSpawnCommand(SpawnController spawnController) {
        this.spawnController = spawnController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player");
            return true;
        }
        Player player = (Player) commandSender;
        SpawnModel spawn = new SpawnModel(player.getLocation());
        spawnController.updateSpawn(spawn);
        commandSender.sendMessage("You have updated the server spawn point!");
        return true;
    }
}
