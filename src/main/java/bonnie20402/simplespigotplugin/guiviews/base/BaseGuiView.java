package bonnie20402.simplespigotplugin.guiviews.base;

import dev.triumphteam.gui.guis.Gui;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class BaseGuiView {
    private Gui gui;

    /*
        Setup the item logic right here.
     */
    protected abstract void setupItems();

    /*
        Add the items to their respective slots right here.
     */
    protected abstract void addItems();


    protected void update() {
        gui.update();
    }

    public void show(Player player) {
        gui.open(player);
    }
    public boolean isOpen(Player player) {
        return Objects.equals(player.getInventory(),gui.getInventory());
    }
    protected void close(Player player) {
        if(Objects.equals(player.getInventory(),gui.getInventory())) {
            player.getInventory().close();
        }else throw new IllegalStateException("This GUI is not open!");
    }

    public Gui getGui() {
        return gui;
    }

    protected void setGui(Gui gui) {
        this.gui = gui;
    }
}
