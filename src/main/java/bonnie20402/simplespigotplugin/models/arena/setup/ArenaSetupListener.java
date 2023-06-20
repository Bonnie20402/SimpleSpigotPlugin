package bonnie20402.simplespigotplugin.models.arena.setup;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArenaSetupListener implements Listener {
    private final ArenaManager arenaManager;
    public ArenaSetupListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        arenaManager.getArenaSetups().put(playerJoinEvent.getPlayer().getUniqueId(),new ArenaSetup(new ArenaModel()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent playerQuitEvent) {
        arenaManager.getArenaSetups().remove(playerQuitEvent.getPlayer().getUniqueId());
    }
}
