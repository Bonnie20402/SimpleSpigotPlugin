package bonnie20402.simplespigotplugin.models.arena.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArenaManagerListener implements Listener {
    private final ArenaManager arenaManager;

    public ArenaManagerListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent playerQuitEvent) {
        Player player = playerQuitEvent.getPlayer();
        if(arenaManager.isPlayerOnArena(player)) {
            arenaManager.teleportFromArena(player);
        }
    }
}
