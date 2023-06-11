package bonnie20402.simplewarpplugin.controllers.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    public void deleteBoard(final UUID uuid) {
        if(boards.containsKey(uuid)) {
            Optional<FastBoard> board = Optional.
                    ofNullable(this.getBoard(uuid));
            if(board.isPresent()) board.get().delete();
            boards.remove(uuid);
        }

    }
    public void createBoard(final UUID uuid, final FastBoard fastBoard) {
        this.boards.put(uuid,fastBoard);
    }

    public void startBoards() {
        plugin.getServer().getScheduler().runTaskTimer(plugin,() -> {
            for(FastBoard board : boards.values()) {
                if(!board.isDeleted())updateBoard(board);
            }
        },5L,20L);
    }

    /*
        Add content to the board here.
     */
    public abstract void updateBoard(FastBoard board);
}
