package bonnie20402.simplewarpplugin.listeners.scoreboard;

import bonnie20402.simplewarpplugin.controllers.scoreboard.MainScoreboard;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class MainScoreboardListener implements Listener {
    private final Plugin plugin;

    public MainScoreboardListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        MainScoreboard mainScoreboard = new MainScoreboard(
                new FastBoard(player),
                this.plugin
        );
        mainScoreboard.startBoard();
        e.getPlayer().sendMessage("Welcome !");
    }
}
