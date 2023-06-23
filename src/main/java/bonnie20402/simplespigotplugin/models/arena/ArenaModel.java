package bonnie20402.simplespigotplugin.models.arena;

import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerQuitArenaEvent;
import bonnie20402.simplespigotplugin.utils.SimpleLocation;
import com.infernalsuite.aswm.api.SlimePlugin;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

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
    private transient Plugin plugin;
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
        this.currentPlayers = new ArrayList<>();
        createWorld();
        this.arenaWorld = Bukkit.getWorld( this.getArenaName() );
        this.lobbySpawn = new Location(arenaWorld, getLobbyLoc().getX(), getLobbyLoc().getY(), getLobbyLoc().getZ(), getLobbyLoc().getYaw(), getLobbyLoc().getPitch() );
        this.p1Spawn = new Location(arenaWorld,getP1Loc().getX(), getP1Loc().getY(), getP1Loc().getZ(), getP1Loc().getYaw(), getP1Loc().getPitch() );
        this.p2Spawn = new Location(arenaWorld,getP2Loc().getX(), getP2Loc().getY(), getP2Loc().getZ(), getP1Loc().getYaw(), getP2Loc().getPitch() );
        ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent( this.getArenaState(), ArenaState.ARENA_STATE_WAITING,this);
        arenaStateChangeEvent.callEvent();
        this.setArenaState(ArenaState.ARENA_STATE_WAITING);
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

    public void reduceTimer() { this.timer--; }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public SimpleLocation getLobbyLoc() {
        return lobbyLoc;
    }


    public void clearArenaPlayers() {
        for(UUID uuid : this.getCurrentPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            PlayerQuitArenaEvent playerQuitArenaEvent = new PlayerQuitArenaEvent(this,player);
            playerQuitArenaEvent.callEvent();
        }
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
}