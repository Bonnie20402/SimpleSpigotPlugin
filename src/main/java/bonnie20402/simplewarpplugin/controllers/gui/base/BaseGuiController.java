package bonnie20402.simplewarpplugin.controllers.gui.base;

import bonnie20402.simplewarpplugin.guiviews.base.BaseGuiView;
import org.bukkit.entity.Player;

public abstract class BaseGuiController<GuiView extends BaseGuiView>{
    protected GuiView guiView;

    public BaseGuiController() { }
    public BaseGuiController(GuiView guiView) {
        this.guiView = guiView;
    }

    public boolean isOpen(Player player) {
/*        InventoryView playerOpenInventory = player.getOpenInventory();
        Inventory playerInventory = (Inventory) playerOpenInventory;
        Inventory guiInventory = guiView.getGui().getInventory();*/
        return false;
    }
    public void open(Player player) {
        if(!this.isOpen(player))this.guiView.getGui().open(player);
        else throw new IllegalStateException("The View is already open!");
    }
    public void close(Player player) {
        if(this.isOpen(player))player.closeInventory();
        else throw new IllegalStateException("The inventory is not open!");
    }
    public void update() {
        this.guiView.getGui().update();
    }
}
