package bonnie20402.simplespigotplugin.models.arena.listeners;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaFightStartEvent;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaStateChangeListener implements Listener {

    @EventHandler
    private void onArenaStateChange(ArenaStateChangeEvent arenaStateChangeEvent) {
        ArenaModel arenaModel = arenaStateChangeEvent.getArenaModel();
        ArenaState oldState = arenaStateChangeEvent.getOldState();
        ArenaState newState = arenaStateChangeEvent.getNewState();

        if( oldState == newState ) return;
        switch( newState ) {
            case ARENA_STATE_WAITING -> {
                if( oldState == ArenaState.ARENA_STATE_STARTING ) arenaModel.annunceMessage("Cancelled due to not enough players");

            }
            case ARENA_STATE_STARTING -> {
                arenaModel.setTimer(30);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int timer = arenaModel.getTimer();
                        if(timer == 0 ) {
                            ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(ArenaState.ARENA_STATE_STARTING, ArenaState.ARENA_STATE_FIGHTING, arenaModel);
                            arenaStateChangeEvent.callEvent();
                            arenaModel.setArenaState(arenaStateChangeEvent.isCancelled() ? ArenaState.ARENA_STATE_STARTING : ArenaState.ARENA_STATE_FIGHTING);
                            this.cancel();
                        }
                        else if (arenaModel.getPlayerAmount() <= 1) {
                            ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(ArenaState.ARENA_STATE_STARTING, ArenaState.ARENA_STATE_WAITING, arenaModel);
                            arenaStateChangeEvent.callEvent();
                            arenaModel.setArenaState(arenaStateChangeEvent.isCancelled() ? ArenaState.ARENA_STATE_STARTING : ArenaState.ARENA_STATE_WAITING);
                        }
                        if( timer == 30 || timer == 15 || timer <= 5) arenaModel.annunceMessage("Starting the fight in " + timer + " second" + (timer == 1 ? "" : "s") );
                        arenaModel.reduceTimer();
                    }
                }.runTaskTimer(arenaModel.getPlugin(), 0L, 20L);
            }
            case ARENA_STATE_FIGHTING -> {
                Player p1 = Bukkit.getPlayer(arenaModel.getCurrentPlayers().get(0));
                Player p2 = Bukkit.getPlayer(arenaModel.getCurrentPlayers().get(1));
                ArenaFightStartEvent arenaFightStartEvent = new ArenaFightStartEvent(arenaModel,p1,p2);
                arenaFightStartEvent.callEvent();
            }
        }
    }
}
