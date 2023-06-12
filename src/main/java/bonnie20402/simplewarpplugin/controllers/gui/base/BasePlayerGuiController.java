package bonnie20402.simplewarpplugin.controllers.gui.base;

import bonnie20402.simplewarpplugin.guiviews.base.BasePlayerGuiView;
import org.bukkit.entity.Player;

public class BasePlayerGuiController<GuiType extends BasePlayerGuiView> extends BaseGuiController {

    GuiType guiView;
    public BasePlayerGuiController(GuiType basePlayerGuiView) {
        this.guiView = basePlayerGuiView;
    }

    public void setPlayer(Player player) {
        this.guiView.setPlayer(player);
    }

    @Override
    public void open(Player player) {
        this.guiView.getGui().open(player);
    }

    /*
            Gets the player that is inside the view.
         */
    public Player getPlayer() {
        return this.guiView.getPlayer();
    }
}
