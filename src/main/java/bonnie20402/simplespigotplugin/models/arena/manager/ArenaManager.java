package bonnie20402.simplespigotplugin.models.arena.manager;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerJoinArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerQuitArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.setup.ArenaSetup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ArenaManager {
    private static final String ARENAS_FOLDER = "arenas";
    private final HashMap<String, ArenaModel> arenas;
    private final HashMap<UUID, ArenaSetup> arenaSetups;
    private final Plugin plugin;
    private final Gson gson;


    private String getArenasFolder() {
        return plugin.getDataFolder() + File.separator + ARENAS_FOLDER;
    }
    public ArenaManager(HashMap<String, ArenaModel> arenas, Plugin plugin,HashMap<UUID,ArenaSetup> arenaSetups) {
        this.arenas = arenas;
        this.plugin = plugin;
        this.arenaSetups = arenaSetups;
        gson =  new GsonBuilder()
                .setPrettyPrinting()
                .create();
        load();
    }
    public HashMap<UUID, ArenaSetup> getArenaSetups() {
        return arenaSetups;
    }

    public void unloadArenas() {
        plugin.getLogger().info("Unloading arenas...");
        for(ArenaModel arenaModel : arenas.values()) {
            Bukkit.getServer().unloadWorld(arenaModel.getArenaWorld(),false);
            arenas.remove(arenaModel.getArenaName());
        }
        plugin.getLogger().info("Unloaded!");
    }
    public ArenaModel getArena(String arenaName) {
        return arenas.get(arenaName);
    }

    public void unloadArena(ArenaModel arenaModel) {
        Bukkit.getServer().unloadWorld(arenaModel.getArenaWorld(),false);
        arenas.remove(arenaModel.getArenaName());
    }
    public void reloadArena(ArenaModel arenaModel) {
        this.unloadArena(arenaModel);
        this.addArena(arenaModel);
    }
    public void addArena(ArenaModel arenaModel) {
        arenaModel.initTransientFields(plugin);
        arenas.put(arenaModel.getArenaName(),arenaModel);
        saveArena(arenaModel);
    }

    private void saveArena(ArenaModel arenaModel) {
        File arenaFile = new File(this.getArenasFolder() + File.separator + arenaModel.getArenaName() + ".json");
        if( arenaFile.delete() ) {
            plugin.getLogger().info("Overwrited arena named " + arenaModel.getArenaName());
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arenaFile)) ) {
            String arenaModelSerialized = gson.toJson(arenaModel);
            bufferedWriter.write(arenaModelSerialized);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }



    public boolean isPlayerOnArena(Player player) {
        ArenaModel arenaModel;
        arenaModel = this.getPlayerArena(player);
        return arenaModel != null;
    }
    public ArenaModel getPlayerArena(Player player) {
        return (ArenaModel) player.getMetadata("currentArena").get(0).value();
    }

    public boolean arenaExists(String arenaName) {
        return arenas.containsKey(arenaName);
    }

    public void teleportToArena(Player joiningPlayer,String arenaName ) {
        if( this.arenaExists(arenaName) ) {
            ArenaModel arenaModel = this.getArena(arenaName);
            PlayerJoinArenaEvent playerJoinArenaEvent = new PlayerJoinArenaEvent(arenaModel,joiningPlayer);
            playerJoinArenaEvent.callEvent();
            arenaModel.annunceMessage(playerJoinArenaEvent.getJoinMessage());
        }
    }

    public void teleportFromArena(Player quittingPlayer) {
        ArenaModel arenaModel = this.getPlayerArena(quittingPlayer);
        PlayerQuitArenaEvent playerQuitArenaEvent = new PlayerQuitArenaEvent(arenaModel,quittingPlayer);
        playerQuitArenaEvent.callEvent();
        arenaModel.annunceMessage(playerQuitArenaEvent.getQuitMessage());
    }

    private void load() {
        int quantity = 0;
        File folder = new File(this.getArenasFolder());
        if( folder.mkdirs() ) {
            plugin.getLogger().info("Created arenas folder!");
            return;
        }
        for( File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.getName().endsWith(".json") ) {
                try(JsonReader jsonReader = new JsonReader(new FileReader(file)) ) {
                    ArenaModel arenaModel = gson.fromJson(jsonReader,ArenaModel.class);
                    arenaModel.initTransientFields(plugin);
                    arenas.put(arenaModel.getArenaName(),arenaModel);
                    quantity++;
                }catch ( IOException exception ) {
                    exception.printStackTrace();
                }
            }
        }
        plugin.getLogger().info(quantity + " arenas have been loaded.");
    }
}
