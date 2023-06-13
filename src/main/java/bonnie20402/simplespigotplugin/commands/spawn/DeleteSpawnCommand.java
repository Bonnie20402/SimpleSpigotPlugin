package bonnie20402.simplespigotplugin.commands.spawn;

import bonnie20402.simplespigotplugin.controllers.spawn.SpawnController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class DeleteSpawnCommand implements CommandExecutor {
    private final SpawnController spawnController;

    public DeleteSpawnCommand(SpawnController spawnController) {
        this.spawnController = spawnController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(spawnController.getSpawn().isPresent()) {
            spawnController.deleteSpawn();
            commandSender.sendMessage("My boy congrats, you just deleted the server spawn. Be proud of yourself for that accomplishment!");
        }else commandSender.sendMessage("There is no spawn in the server. Why don't you set it? Pleeease :3");
        return true;
    }
}
