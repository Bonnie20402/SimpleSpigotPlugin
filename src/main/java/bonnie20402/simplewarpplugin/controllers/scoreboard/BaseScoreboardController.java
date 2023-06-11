package bonnie20402.simplewarpplugin.controllers.scoreboard;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public abstract class BaseScoreboardController implements Listener {
    private final FastBoard fastBoard;
    protected final Plugin plugin;

    public BaseScoreboardController(FastBoard fastBoard,Plugin plugin) {
        this.fastBoard = fastBoard;
        this.plugin=plugin;
    }

    public FastBoard getFastBoard() {
        return fastBoard;
    }

    /*
        Starts the board update timer.
     */
    public void startBoard() {
        this.plugin.getServer().getScheduler().runTaskTimer(this.plugin, this::update, 5L, 20L);
    }

    /*
        Update the lines based on your extension right here.
     */
    public abstract void update();

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        if(this.getFastBoard() == null) return;
        if(this.getFastBoard().getPlayer() == e.getPlayer()) {
            this.getFastBoard().delete();
        }
    }
}
