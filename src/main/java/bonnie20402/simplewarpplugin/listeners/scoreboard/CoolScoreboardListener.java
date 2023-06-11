package bonnie20402.simplewarpplugin.listeners.scoreboard;

import bonnie20402.simplewarpplugin.controllers.scoreboard.CoolScoreBoardController;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public final class CoolScoreboardListener implements Listener {

    CoolScoreBoardController coolScoreBoardController;

    public CoolScoreboardListener(CoolScoreBoardController coolScoreBoardController) {
        this.coolScoreBoardController = coolScoreBoardController;
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent playerQuitEvent) {
        UUID uuid = playerQuitEvent.getPlayer().getUniqueId();
        coolScoreBoardController.deleteBoard(uuid);

    }
    @EventHandler
    private void onJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        UUID uuid = playerJoinEvent.getPlayer().getUniqueId();
        coolScoreBoardController.createBoard(uuid,new FastBoard(player));
    }
}
