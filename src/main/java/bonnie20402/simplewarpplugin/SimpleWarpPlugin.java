package bonnie20402.simplewarpplugin;

import bonnie20402.simplewarpplugin.commands.spawn.SetSpawnCommand;
import bonnie20402.simplewarpplugin.commands.spawn.SpawnCommand;
import bonnie20402.simplewarpplugin.commands.warp.CreateWarpCommand;
import bonnie20402.simplewarpplugin.commands.warp.DeleteSpawnCommand;
import bonnie20402.simplewarpplugin.commands.warp.DeleteWarpCommand;
import bonnie20402.simplewarpplugin.commands.warp.ListWarpCommand;
import bonnie20402.simplewarpplugin.controllers.spawn.SpawnController;
import bonnie20402.simplewarpplugin.controllers.warp.WarpController;
import bonnie20402.simplewarpplugin.listeners.scoreboard.MainScoreboardListener;
import bonnie20402.simplewarpplugin.listeners.spawn.SpawnListener;
import bonnie20402.simplewarpplugin.models.SpawnModel;
import bonnie20402.simplewarpplugin.models.WarpModel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SimpleWarpPlugin extends JavaPlugin {
    WarpController warpController;
    SpawnController spawnController;
    @Override
    public void onEnable() {
        warpController = new WarpController(new ArrayList<WarpModel>(),this);
        spawnController = new SpawnController(this, new SpawnModel());
        if(!getDataFolder().exists())getDataFolder().mkdirs();
        Bukkit.getServer().getLogger().info("Simple warp plugin loaded!");

        //Warp commands registration
        Bukkit.getServer().getPluginCommand("createwarp").setExecutor(new CreateWarpCommand(warpController));
        Bukkit.getServer().getPluginCommand("warp").setExecutor(new ListWarpCommand(warpController));
        Bukkit.getServer().getPluginCommand("deletewarp").setExecutor(new DeleteWarpCommand(warpController));

        //Spawn
        Bukkit.getServer().getPluginCommand("spawn").setExecutor(new SpawnCommand(spawnController));
        Bukkit.getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCommand(spawnController));
        Bukkit.getServer().getPluginCommand("deletespawn").setExecutor(new DeleteSpawnCommand(spawnController));
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnListener(spawnController,this),this);

        //Sb
        Bukkit.getServer().getPluginManager().registerEvents(new MainScoreboardListener(this),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
