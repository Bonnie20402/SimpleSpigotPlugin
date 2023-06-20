package bonnie20402.simplespigotplugin.models.arena;

import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import bonnie20402.simplespigotplugin.utils.SimpleLocation;
import com.infernalsuite.aswm.api.SlimePlugin;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class ArenaModel {
    private String arenaTemplate;
    private SimpleLocation lobbyLoc, p1Loc, p2Loc;
    private String arenaName;

    private transient List<UUID> currentPlayers;
    private transient ArenaState arenaState = ArenaState.ARENA_STATE_LOADING;
    private transient World arenaWorld;

    private transient int timer;
    private transient BukkitRunnable timerTask;
    private transient Plugin plugin;
    private transient Player fighterOne,fighterTwo;
    private transient Location lobbySpawn, p1Spawn, p2Spawn;




    private void resetTimer() { this.timer = 30; }

    private void createWorld() {
        SlimePlugin slimePlugin = (SlimePlugin) getServer().getPluginManager().getPlugin("SlimeWorldManager");
        assert slimePlugin != null;
        SlimeWorld slimeWorld = slimePlugin.getWorld(arenaTemplate).clone(arenaName);
        try {
            slimePlugin.loadWorld(slimeWorld);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("Created world named " + arenaName + " from template " + arenaTemplate);
    }
    public void initTransientFields(Plugin plugin) {
        this.plugin = plugin;
        createWorld();
        this.currentPlayers = new ArrayList<>();
        this.arenaWorld = getServer().getWorld(this.arenaName);
        this.setLobbySpawn( new Location(arenaWorld,lobbyLoc.getX(),lobbyLoc.getY(),lobbyLoc.getZ()) );
        this.setP1Spawn( new Location(arenaWorld,p1Loc.getX(),p1Loc.getY(),p1Loc.getZ()) );
        this.setP2Spawn( new Location(arenaWorld,p2Loc.getX(),p2Loc.getY(),p2Loc.getZ()) );
        this.arenaState = ArenaState.ARENA_STATE_WAITING;
    }
    public String getArenaTemplate() {
        return arenaTemplate;
    }

    public void setArenaTemplate(String arenaTemplate) {
        this.arenaTemplate = arenaTemplate;
    }

    public void annunceMessage(String message) {
        for(Player player : getServer().getOnlinePlayers()) {
            if( currentPlayers.contains(player.getUniqueId()) ) {
                player.sendMessage(message);
            }
        }
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public SimpleLocation getLobbyLoc() {
        return lobbyLoc;
    }

    public void setLobbyLoc(SimpleLocation lobbyLoc) {
        this.lobbyLoc = lobbyLoc;
    }

    public SimpleLocation getP1Loc() {
        return p1Loc;
    }

    public void setP1Loc(SimpleLocation p1Loc) {
        this.p1Loc = p1Loc;
    }

    public SimpleLocation getP2Loc() {
        return p2Loc;
    }

    public void setP2Loc(SimpleLocation p2Loc) {
        this.p2Loc = p2Loc;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public List<UUID> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(List<UUID> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public ArenaState getArenaState() {
        return arenaState;
    }

    public void setArenaState(ArenaState arenaState) {
        this.arenaState = arenaState;
    }

    public World getArenaWorld() {
        return arenaWorld;
    }

    public void setArenaWorld(World arenaWorld) {
        this.arenaWorld = arenaWorld;
    }

    public Location getLobbySpawn() {
        return lobbySpawn;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public int getPlayerAmount() { return this.currentPlayers.size(); }

    public void setLobbySpawn(Location lobbySpawn) {
        this.lobbySpawn = lobbySpawn;
    }

    public Location getP1Spawn() {
        return p1Spawn;
    }

    public void setP1Spawn(Location p1Spawn) {
        this.p1Spawn = p1Spawn;
    }

    public Location getP2Spawn() {
        return p2Spawn;
    }

    public void setP2Spawn(Location p2Spawn) {
        this.p2Spawn = p2Spawn;
    }

    public void runStateLogic() {
        switch (arenaState) {
            case ARENA_STATE_WAITING -> {
                if( this.getPlayerAmount() >= 2 ) {
                    ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(ArenaState.ARENA_STATE_WAITING,ArenaState.ARENA_STATE_STARTING,this);
                    arenaStateChangeEvent.callEvent();
                    this.timer = 30;
                    arenaState = arenaStateChangeEvent.isCancelled() ? ArenaState.ARENA_STATE_WAITING : ArenaState.ARENA_STATE_STARTING;
                }
            }
            case ARENA_STATE_STARTING -> {
                    // create arenastatechangevbent from wait to start
            }
        }
    }
}