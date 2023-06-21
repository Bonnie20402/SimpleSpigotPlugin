package bonnie20402.simplespigotplugin.models.arena.events;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ArenaStateChangeEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final ArenaState oldState,newState;
    private final ArenaModel arenaModel;

    public ArenaStateChangeEvent(ArenaState oldState, ArenaState newState, ArenaModel arenaModel) {
        this.oldState = oldState;
        this.newState = newState;
        this.arenaModel = arenaModel;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    public ArenaState getOldState() {
        return oldState;
    }

    public ArenaState getNewState() {
        return newState;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }


    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
