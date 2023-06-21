package bonnie20402.simplespigotplugin.models.arena.listeners.vanilla;

import bonnie20402.simplespigotplugin.models.arena.events.PlayerQuitArenaEvent;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final ArenaManager arenaManager;

    public PlayerQuitListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent playerQuitEvent) {
        Player player = playerQuitEvent.getPlayer();
        if( arenaManager.isPlayerOnArena(player) ) {
            PlayerQuitArenaEvent playerQuitArenaEvent = new PlayerQuitArenaEvent(arenaManager.getPlayerArena(player),player );
            playerQuitArenaEvent.callEvent();
        }
    }
}
