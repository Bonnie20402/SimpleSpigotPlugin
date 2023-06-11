package bonnie20402.simplewarpplugin.controllers.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MainScoreboard extends BaseScoreboardController {
    public MainScoreboard(FastBoard fastBoard, Plugin plugin) {
        super(fastBoard, plugin);
        this.getFastBoard().updateTitle("Learning java!!");
    }

    @Override
    public void update() {
        Player player = this.getFastBoard().getPlayer();
        this.getFastBoard().updateLines(
                "",
                "Welcome, "+player.getName(),
                "",
                "Online players: "+ this.plugin.getServer().getOnlinePlayers().size(),
                "",
                "§emycoolserver.net"
        );
    }
}
