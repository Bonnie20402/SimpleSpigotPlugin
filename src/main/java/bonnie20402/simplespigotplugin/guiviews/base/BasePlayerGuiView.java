package bonnie20402.simplespigotplugin.guiviews.base;

import org.bukkit.entity.Player;

public abstract class BasePlayerGuiView extends BaseGuiView {
    private Player player;

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player=player;
        setupItems();
        addItems();
    }

}
