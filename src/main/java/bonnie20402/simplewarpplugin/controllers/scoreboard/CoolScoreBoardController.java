package bonnie20402.simplewarpplugin.controllers.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.plugin.Plugin;


public final class CoolScoreBoardController extends BaseScoreboardController {
    public CoolScoreBoardController(Plugin plugin) {
        super(plugin);
    }
    @Override
    public void updateBoard(FastBoard board) {
        board.updateTitle("cool server");
        board.updateLines(
                "Hello there!",
                "",
                "Nice to see you",
                "",
                "come back anytime dude"
        );
    }
}
