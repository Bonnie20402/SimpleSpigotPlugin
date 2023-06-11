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

    protected Map<UUID, FastBoard> getBoards() {
        return boards;
    }

    public FastBoard getBoard(final UUID uuid){
        return this.boards.get(uuid);
    }

    public void createBoard(final UUID uuid, final FastBoard fastBoard) {
        this.boards.put(uuid,fastBoard);
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
