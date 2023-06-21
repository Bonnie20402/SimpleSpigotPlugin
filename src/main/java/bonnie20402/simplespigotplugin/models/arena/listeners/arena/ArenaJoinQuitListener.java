package bonnie20402.simplespigotplugin.models.arena.listeners.arena;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaDeathEvent;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerJoinArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerQuitArenaEvent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class ArenaJoinQuitListener implements Listener {
    @EventHandler
    private void onArenaJoin(PlayerJoinArenaEvent playerJoinArenaEvent) {
        ArenaModel arenaModel = playerJoinArenaEvent.getArenaModel();
        Player joiningPlayer = playerJoinArenaEvent.getPlayer();
        arenaModel.getCurrentPlayers().add(joiningPlayer.getUniqueId());
        switch( arenaModel.getArenaState() ) {
            case ARENA_STATE_WAITING, ARENA_STATE_STARTING -> {
                saveDataBeforeJoin(joiningPlayer,arenaModel);
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
        restoreDataBeforeJoin(quittingPlayer,arenaModel);
        clearMetadata(quittingPlayer, arenaModel.getPlugin());
        switch( arenaModel.getArenaState() ) {
            case ARENA_STATE_WAITING, ARENA_STATE_STARTING -> {
                String message = String.format("%s §eleft the arena! (%d/2)",quittingPlayer.getName(),arenaModel.getCurrentPlayers().size() );
                playerQuitArenaEvent.setQuitMessage(message);
            }
            case ARENA_STATE_FIGHTING -> {
                String message = String.format("%s §eleft mid fight!",quittingPlayer.getName());
                playerQuitArenaEvent.setQuitMessage(message);
                ArenaDeathEvent arenaDeathEvent = new ArenaDeathEvent(arenaModel,quittingPlayer);
                arenaDeathEvent.callEvent();
            }

            case ARENA_STATE_FINISHED -> {
                restoreDataBeforeJoin(quittingPlayer,arenaModel);
                clearMetadata(quittingPlayer, arenaModel.getPlugin());
            }
        }
    }

    private void saveDataBeforeJoin(Player player,ArenaModel arenaModel) {
        player.setMetadata("locationBeforeArena", new FixedMetadataValue(arenaModel.getPlugin(), player.getLocation()) );
        player.setMetadata("currentArena", new FixedMetadataValue(arenaModel.getPlugin(), arenaModel) );
        player.setMetadata("GMBeforeArena",new FixedMetadataValue(arenaModel.getPlugin(), player.getGameMode()));
    }

    private void restoreDataBeforeJoin(Player player,ArenaModel arenaModel) {
        player.teleport( (Location) player.getMetadata("locationBeforeArena").get(0).value() );
        player.setGameMode( (GameMode) player.getMetadata("GMBeforeArena").get(0).value() );
    }
    private void clearMetadata(Player player, Plugin plugin) {
        player.removeMetadata("currentArena", plugin);
        player.removeMetadata("locationBeforeArena", plugin);
        player.removeMetadata("GMBeforeArena", plugin);
    }
}
