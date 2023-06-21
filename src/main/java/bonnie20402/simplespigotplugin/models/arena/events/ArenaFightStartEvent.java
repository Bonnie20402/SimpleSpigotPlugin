package bonnie20402.simplespigotplugin.models.arena.events;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ArenaFightStartEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ArenaModel arenaModel;
    private final Player p1,p2;
    private String p1Message,p2Message;

    public ArenaFightStartEvent(ArenaModel arenaModel, Player p1, Player p2) {
        this.arenaModel = arenaModel;
        this.p1 = p1;
        this.p2 = p2;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public String getP1Message() {
        return p1Message;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public void setP1Message(String p1Message) {
        this.p1Message = p1Message;
    }

    public String getP2Message() {
        return p2Message;
    }

    public void setP2Message(String p2Message) {
        this.p2Message = p2Message;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
