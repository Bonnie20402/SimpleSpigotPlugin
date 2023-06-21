package bonnie20402.simplespigotplugin.commands;

import com.infernalsuite.aswm.api.SlimePlugin;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


public class TestCommand implements CommandExecutor {
    private final SlimePlugin slimePlugin;
    private final Plugin plugin;
    public TestCommand(SlimePlugin slimePlugin,Plugin plugin) {
        this.slimePlugin = slimePlugin;
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        SlimeWorld slimeWorld;
        commandSender.sendMessage("Now cloning world...");
        slimeWorld = slimePlugin.getWorld("ArenaTemplate").clone("arena1");
        slimePlugin.loadWorld(slimeWorld);
        if( slimeWorld != null ) {
            Location spawn = plugin.getServer().getWorld(slimeWorld.getName()).getSpawnLocation();
            player.teleport(spawn);
            commandSender.sendMessage("welcome there dude");
        }
        else {
            commandSender.sendMessage("ERROR");
        }

            return true;

    }

}
