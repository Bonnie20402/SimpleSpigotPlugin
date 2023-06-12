package bonnie20402.simplewarpplugin.controllers.gui.interfaces;

import org.bukkit.entity.Player;

public interface PlayerGuiController extends GuiController {


    /*
        Sets the player that is passed to the view
     */
    void setPlayer(Player player);

    /*
        Gets the player that is inside the view.
     */
    Player getPlayer();


}
