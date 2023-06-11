package bonnie20402.simplewarpplugin.controllers.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class BaseScoreboardController {
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private final Plugin plugin;

    public BaseScoreboardController(Plugin plugin) {
        this.plugin = plugin;
        this.startBoards();
    }

    public Map<UUID, FastBoard> getBoards() {
        return boards;
    }

    public void startBoards() {
        plugin.getServer().getScheduler().runTaskTimer(plugin,() -> {
            for(FastBoard board : boards.values()) {
                updateBoard(board);
            }
        },5L,20L);
    }

    /*
        Add content to the board here.
     */
    public abstract void updateBoard(FastBoard board);
}
