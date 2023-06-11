package bonnie20402.simplewarpplugin.controllers.gui;

import org.bukkit.entity.Player;

public interface GuiController {
    boolean isOpen(Player player);
    void open(Player player);
    void close(Player player);
    void update();

}
