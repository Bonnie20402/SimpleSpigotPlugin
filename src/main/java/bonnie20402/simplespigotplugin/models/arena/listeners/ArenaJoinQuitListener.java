package bonnie20402.simplespigotplugin.models.arena.listeners;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerJoinArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerQuitArenaEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

public class ArenaJoinQuitListener implements Listener {
    @EventHandler
    private void onArenaJoin(PlayerJoinArenaEvent playerJoinArenaEvent) {
        ArenaModel arenaModel = playerJoinArenaEvent.getArenaModel();
        Player joiningPlayer = playerJoinArenaEvent.getPlayer();
        arenaModel.getCurrentPlayers().add(joiningPlayer.getUniqueId());
        switch( arenaModel.getArenaState() ) {
            case ARENA_STATE_WAITING, ARENA_STATE_STARTING -> {
                joiningPlayer.setMetadata("locationBeforeArena", new FixedMetadataValue(arenaModel.getPlugin(), joiningPlayer.getLocation()) );
                joiningPlayer.setMetadata("currentArena", new FixedMetadataValue(arenaModel.getPlugin(), arenaModel) );
                joiningPlayer.teleport(arenaModel.getLobbySpawn());
                String message = String.format("%s §ejoined the arena! (%d/2)",joiningPlayer.getName(),arenaModel.getCurrentPlayers().size() );
                playerJoinArenaEvent.setJoinMessage(message);
                if( arenaModel.getPlayerAmount() > 1 ) {
                    ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(arenaModel.getArenaState(),ArenaState.ARENA_STATE_STARTING,arenaModel);
                    arenaStateChangeEvent.callEvent();
                    arenaModel.setArenaState(arenaStateChangeEvent.isCancelled() ? arenaModel.getArenaState() : ArenaState.ARENA_STATE_STARTING);
                }
            }
        }
    }

    @EventHandler
    private void onArenaQuit(PlayerQuitArenaEvent playerQuitArenaEvent) {
        ArenaModel arenaModel = playerQuitArenaEvent.getArenaModel();
        Player quittingPlayer = playerQuitArenaEvent.getPlayer();
        arenaModel.getCurrentPlayers().remove(quittingPlayer.getUniqueId());
        quittingPlayer.removeMetadata("currentArena", arenaModel.getPlugin());
        quittingPlayer.teleport( (Location) quittingPlayer.getMetadata("locationBeforeArena").get(0).value() );
        quittingPlayer.removeMetadata("locationBeforeArena", arenaModel.getPlugin());
        switch( arenaModel.getArenaState() ) {
            case ARENA_STATE_WAITING, ARENA_STATE_STARTING -> {
                String message = String.format("%s §eleft the arena! (%d/2)",quittingPlayer.getName(),arenaModel.getCurrentPlayers().size() );
                playerQuitArenaEvent.setQuitMessage(message);
                if( arenaModel.getPlayerAmount() <= 1 ) {
                    ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(arenaModel.getArenaState(),ArenaState.ARENA_STATE_WAITING,arenaModel);
                    arenaStateChangeEvent.callEvent();
                    arenaModel.setArenaState(arenaStateChangeEvent.isCancelled() ? arenaModel.getArenaState() : ArenaState.ARENA_STATE_WAITING);
                }
            }
            case ARENA_STATE_FIGHTING -> {
                String message = String.format("%s §eleft mid fight!",quittingPlayer.getName());
                playerQuitArenaEvent.setQuitMessage(message);
                //TODO: Add leaving mid-fight logic.
            }
        }
    }
}
