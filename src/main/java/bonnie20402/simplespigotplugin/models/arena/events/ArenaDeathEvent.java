package bonnie20402.simplespigotplugin.models.arena.events;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ArenaDeathEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final ArenaModel arena;
    private final Player victim;

    public ArenaDeathEvent(ArenaModel arena, Player victim) {
        this.arena = arena;
        this.victim = victim;
    }

    public ArenaModel getArena() {
        return arena;
    }

    public Player getVictim() {
        return victim;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
