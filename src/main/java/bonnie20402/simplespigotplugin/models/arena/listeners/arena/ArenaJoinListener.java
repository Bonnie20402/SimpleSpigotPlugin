package bonnie20402.simplespigotplugin.models.arena.listeners.arena;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaStateChangeEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerJoinArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaJoinListener implements Listener {
    @EventHandler
    private void onArenaJoin(PlayerJoinArenaEvent playerJoinArenaEvent) {
        ArenaModel arenaModel = playerJoinArenaEvent.getArenaModel();
        Player joiningPlayer = playerJoinArenaEvent.getPlayer();
        arenaModel.getCurrentPlayers().add(joiningPlayer.getUniqueId());
        switch( arenaModel.getArenaState() ) {
            case ARENA_STATE_WAITING, ARENA_STATE_STARTING -> {
                ArenaManager.saveDataBeforeJoin(joiningPlayer,arenaModel);
                joiningPlayer.setGameMode(GameMode.SURVIVAL);
                joiningPlayer.getInventory().clear();
                joiningPlayer.teleport(arenaModel.getLobbySpawn());
                String message = String.format("%s §ejoined the arena! (%d/2)",joiningPlayer.getName(),arenaModel.getCurrentPlayers().size() );
                playerJoinArenaEvent.setJoinMessage(message);
                if( arenaModel.getPlayerAmount() > 1 ) {
                    ArenaStateChangeEvent arenaStateChangeEvent = new ArenaStateChangeEvent(arenaModel.getArenaState(),ArenaState.ARENA_STATE_STARTING,arenaModel);
                    arenaStateChangeEvent.callEvent();
                    arenaModel.setArenaState(arenaStateChangeEvent.isCancelled() ? arenaModel.getArenaState() : ArenaState.ARENA_STATE_STARTING);
                }
            }
            default -> {
                joiningPlayer.sendMessage("You cant join this arena now!");
            }
        }
    }

}
