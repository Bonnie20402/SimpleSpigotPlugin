package bonnie20402.simplespigotplugin.models.arena.listeners.arena;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaDeathEvent;
import bonnie20402.simplespigotplugin.models.arena.events.PlayerQuitArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaQuitListener implements Listener {
    @EventHandler
    private void onArenaQuit(PlayerQuitArenaEvent playerQuitArenaEvent) {
        ArenaModel arenaModel = playerQuitArenaEvent.getArenaModel();
        Player quittingPlayer = playerQuitArenaEvent.getPlayer();
        ArenaManager.restoreDataBeforeJoin(quittingPlayer);
        ArenaManager.clearMetadata(quittingPlayer,arenaModel);
        arenaModel.getCurrentPlayers().remove(quittingPlayer.getUniqueId());
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

            }
        }
    }
}
