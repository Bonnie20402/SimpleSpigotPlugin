package bonnie20402.simplewarpplugin;

import bonnie20402.simplewarpplugin.commands.CreateWarpCommand;
import bonnie20402.simplewarpplugin.commands.ListWarpCommand;
import bonnie20402.simplewarpplugin.controllers.WarpController;
import bonnie20402.simplewarpplugin.models.WarpModel;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SimpleWarpPlugin extends JavaPlugin {
    WarpController warpController;

    /*
        Should I init it on constructor?
     */
    public SimpleWarpPlugin() {
        warpController = new WarpController(new ArrayList<WarpModel>(),this);

    }
    @Override
    public void onEnable() {
        if(!getDataFolder().exists())getDataFolder().mkdirs();
        Bukkit.getServer().getLogger().info("Simple warp plugin loaded!");
        Bukkit.getServer().getPluginCommand("createwarp").setExecutor(new CreateWarpCommand(warpController));
        Bukkit.getServer().getPluginCommand("warp").setExecutor(new ListWarpCommand(warpController));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
