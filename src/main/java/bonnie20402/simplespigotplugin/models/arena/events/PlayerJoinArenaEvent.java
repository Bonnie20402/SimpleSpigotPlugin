package bonnie20402.simplespigotplugin.models.arena.events;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
public class PlayerJoinArenaEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ArenaModel arenaModel;
    private final Player player;
    private String joinMessage = "";

    public PlayerJoinArenaEvent(ArenaModel arenaModel,Player player) {
        this.arenaModel = arenaModel;
        this.player = player;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public void setJoinMessage(String joinMessage) {
        this.joinMessage = joinMessage;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }

    public Player getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
