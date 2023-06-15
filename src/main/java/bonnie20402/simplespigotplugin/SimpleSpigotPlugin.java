package bonnie20402.simplespigotplugin;

import bonnie20402.simplespigotplugin.commands.TestCommand;
import bonnie20402.simplespigotplugin.commands.gui.SimpleGuiCommand;
import bonnie20402.simplespigotplugin.commands.gui.SimplePlayerGuiCommand;
import bonnie20402.simplespigotplugin.commands.home.HomeCommand;
import bonnie20402.simplespigotplugin.commands.home.SetHomeCommand;
import bonnie20402.simplespigotplugin.commands.spawn.DeleteSpawnCommand;
import bonnie20402.simplespigotplugin.commands.spawn.SetSpawnCommand;
import bonnie20402.simplespigotplugin.commands.spawn.SpawnCommand;
import bonnie20402.simplespigotplugin.commands.warp.CreateWarpCommand;
import bonnie20402.simplespigotplugin.commands.warp.DeleteWarpCommand;
import bonnie20402.simplespigotplugin.commands.warp.ListWarpCommand;
import bonnie20402.simplespigotplugin.commands.cuboid.CuboidCommand;
import bonnie20402.simplespigotplugin.controllers.cuboid.CuboidController;
import bonnie20402.simplespigotplugin.listeners.cuboid.CuboidListener;
import bonnie20402.simplespigotplugin.controllers.gui.SimpleGuiController;
import bonnie20402.simplespigotplugin.controllers.gui.SimplePlayerGuiController;
import bonnie20402.simplespigotplugin.controllers.home.HomeController;
import bonnie20402.simplespigotplugin.controllers.scoreboard.CoolScoreBoardController;
import bonnie20402.simplespigotplugin.controllers.spawn.SpawnController;
import bonnie20402.simplespigotplugin.controllers.warp.WarpController;
import bonnie20402.simplespigotplugin.guiviews.SimpleGuiView;
import bonnie20402.simplespigotplugin.guiviews.SimplePlayerGuiView;
import bonnie20402.simplespigotplugin.listeners.scoreboard.CoolScoreboardListener;
import bonnie20402.simplespigotplugin.listeners.spawn.SpawnListener;
import bonnie20402.simplespigotplugin.models.spawn.SpawnModel;
import com.google.gson.GsonBuilder;
import com.infernalsuite.aswm.api.SlimePlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class SimpleSpigotPlugin extends JavaPlugin {

    private FileConfiguration fileConfiguration;
    private WarpController warpController;
    private SpawnController spawnController;
    private CoolScoreBoardController coolScoreBoardController;
    private SimpleGuiController simpleGuiController;
    private SimplePlayerGuiController simplePlayerGuiController;

    private CuboidController cuboidController;

    private HomeController homeController;

    private SlimePlugin slimePlugin;



    public Plugin getSlimeWorldManager() {
        return Bukkit.getPluginManager().getPlugin("SlimeWorldManager");
    }

    @Override
    public void onEnable() {
        setupConfigDir();
        createObjects();
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void createObjects() {
        warpController = new WarpController(new HashMap<>(),this);
        spawnController = new SpawnController(this, new SpawnModel());
        homeController = new HomeController(new HashMap<>(),this,new GsonBuilder());
        coolScoreBoardController = new CoolScoreBoardController(this);
        simpleGuiController = new SimpleGuiController(this,new SimpleGuiView());
        simplePlayerGuiController = new SimplePlayerGuiController(this,new SimplePlayerGuiView());
        cuboidController = new CuboidController(this);
        slimePlugin = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");
    }
    private void setupConfigDir() {
        this.saveDefaultConfig();
    }
    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new SpawnListener(spawnController,this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new CoolScoreboardListener(coolScoreBoardController),this);
        Bukkit.getServer().getPluginManager().registerEvents(new CuboidListener(cuboidController),this);
    }

    private void registerCommands() {
        //Warp commands registration
        Bukkit.getServer().getPluginCommand("createwarp").setExecutor(new CreateWarpCommand(warpController));
        Bukkit.getServer().getPluginCommand("warp").setExecutor(new ListWarpCommand(warpController));
        Bukkit.getServer().getPluginCommand("deletewarp").setExecutor(new DeleteWarpCommand(warpController));
        //Spawn
        Bukkit.getServer().getPluginCommand("spawn").setExecutor(new SpawnCommand(spawnController));
        Bukkit.getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCommand(spawnController));
        Bukkit.getServer().getPluginCommand("deletespawn").setExecutor(new DeleteSpawnCommand(spawnController));
        //GUI
        Bukkit.getServer().getPluginCommand("gui").setExecutor(new SimpleGuiCommand(simpleGuiController));
        Bukkit.getServer().getPluginCommand("guip").setExecutor(new SimplePlayerGuiCommand(simplePlayerGuiController));

        //home
        Bukkit.getServer().getPluginCommand("home").setExecutor(new HomeCommand(homeController));
        Bukkit.getServer().getPluginCommand("sethome").setExecutor(new SetHomeCommand(homeController));
        //area playing
        Bukkit.getServer().getPluginCommand("setloc").setExecutor(new CuboidCommand(cuboidController));
        Bukkit.getServer().getPluginCommand("test").setExecutor(new TestCommand(slimePlugin,this));
    }
}
