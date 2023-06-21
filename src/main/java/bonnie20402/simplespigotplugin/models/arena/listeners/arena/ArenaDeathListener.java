package bonnie20402.simplespigotplugin.models.arena.listeners.arena;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaDeathEvent;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaDeathListener implements Listener {

    @EventHandler
    public void onPlayerArenaDeath(ArenaDeathEvent arenaDeathEvent) {
        ArenaModel arenaModel = arenaDeathEvent.getArena();
        Player victim = arenaDeathEvent.getVictim();

        if( arenaModel.getArenaState() == ArenaState.ARENA_STATE_FIGHTING ) {
            ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(arenaModel.getArenaState(),ArenaState.ARENA_STATE_FINISHED,arenaModel);
            arenaStateChangeEvent.callEvent();

            victim.getInventory().clear();
            victim.setHealth(20);
            victim.teleport(arenaModel.getLobbySpawn());
            victim.setGameMode(GameMode.SPECTATOR);
        }
    }
}
