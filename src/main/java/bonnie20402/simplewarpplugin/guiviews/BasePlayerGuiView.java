package bonnie20402.simplewarpplugin.guiviews;

import org.bukkit.entity.Player;

public abstract class BasePlayerGuiView extends BaseGuiView {
    Player player;

    protected void setPlayer(Player player) {
        this.player=player;
    }

    protected Player getPlayer() {
        return this.player;
    }

}
