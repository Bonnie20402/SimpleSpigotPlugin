package bonnie20402.simplewarpplugin.controllers.gui.interfaces;

import org.bukkit.entity.Player;

public interface GuiController {
    boolean isOpen(Player player);
    void open(Player player);
    void close(Player player);
    void update();

}
