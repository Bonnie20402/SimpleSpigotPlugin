package bonnie20402.simplespigotplugin.commands;

import com.infernalsuite.aswm.api.SlimePlugin;
import com.infernalsuite.aswm.api.exceptions.InvalidWorldException;
import com.infernalsuite.aswm.api.exceptions.WorldAlreadyExistsException;
import com.infernalsuite.aswm.api.exceptions.WorldLoadedException;
import com.infernalsuite.aswm.api.exceptions.WorldTooBigException;
import com.infernalsuite.aswm.api.loaders.SlimeLoader;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import com.infernalsuite.aswm.api.world.properties.SlimeProperties;
import com.infernalsuite.aswm.api.world.properties.SlimeProperty;
import com.infernalsuite.aswm.api.world.properties.SlimePropertyMap;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;


public class TestCommand implements CommandExecutor {

    private final SlimePlugin slimePlugin;
    private final Plugin plugin;
    public TestCommand(SlimePlugin slimePlugin,Plugin plugin) {
        this.slimePlugin = slimePlugin;
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        File worldDirectory = new File("voidworld");
        SlimeLoader slimeLoader = slimePlugin.getLoader("file");
        Player player = (Player) commandSender;
        commandSender.sendMessage("Now loading world...");
        SlimePropertyMap slimePropertyMap = new SlimePropertyMap();
        try {
            SlimeWorld slimeWorld = slimePlugin.createEmptyWorld(slimeLoader, "testworld",false,slimePropertyMap);
            slimePlugin.loadWorld(slimeWorld);
            World world = plugin.getServer().getWorld("testworld");
            if( world == null) {
                commandSender.sendMessage("Null world");
            }
            else player.teleport(world.getSpawnLocation());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
            commandSender.sendMessage("World created and loaded");
            return true;

    }

}
