package bonnie20402.simplespigotplugin.controllers.scoreboard;

import bonnie20402.simplespigotplugin.controllers.scoreboard.base.BaseScoreboardController;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.plugin.Plugin;


public final class CoolScoreBoardController extends BaseScoreboardController {
    public CoolScoreBoardController(Plugin plugin) {
        super(plugin);
    }
    @Override
    public void updateBoard(FastBoard board)
    {
        board.updateTitle("O meu plugin em java");
        board.updateLines(
                "Hello there!",
                "",
                "Nice to see you ",
                "",
                "come back anytime dude"
        );
    }
}
