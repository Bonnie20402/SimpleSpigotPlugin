package bonnie20402.simplewarpplugin.controllers.gui;

import bonnie20402.simplewarpplugin.controllers.gui.interfaces.GuiController;
import bonnie20402.simplewarpplugin.guiviews.SimpleGuiView;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class SimpleGuiController implements GuiController {
    private final SimpleGuiView simpleGuiView;

    public SimpleGuiController(Plugin plugin, SimpleGuiView simpleGuiView) {
        this.simpleGuiView = simpleGuiView;
    }

    @Override
    public boolean isOpen(Player player) {
        Inventory guiInventory = simpleGuiView.getGui().getInventory();
        Inventory playerInventory = player.getInventory();
        return Objects.equals(guiInventory,playerInventory);
    }

    @Override
    public void open(Player player) {
        this.simpleGuiView.show(player);
    }

    @Override
    public void close(Player player) {
        if(this.isOpen(player))player.closeInventory();
    }

    @Override
    public void update() {
        this.simpleGuiView.getGui().update();
    }
}
