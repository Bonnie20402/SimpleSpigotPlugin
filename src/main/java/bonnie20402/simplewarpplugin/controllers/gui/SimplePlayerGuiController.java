package bonnie20402.simplewarpplugin.controllers.gui;

import bonnie20402.simplewarpplugin.controllers.gui.interfaces.PlayerGuiController;
import bonnie20402.simplewarpplugin.guiviews.SimplePlayerGuiView;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public final class SimplePlayerGuiController implements PlayerGuiController {
    private final SimplePlayerGuiView simplePlayerGuiView;
    private final Plugin plugin;

    public SimplePlayerGuiController(Plugin plugin, SimplePlayerGuiView simplePlayerGuiView) {
        this.plugin = plugin;
        this.simplePlayerGuiView = simplePlayerGuiView;
    }

    @Override
    public boolean isOpen(Player player) {
        Inventory guiInventory = simplePlayerGuiView.getGui().getInventory();
        Inventory playerInventory = player.getInventory();
        return Objects.equals(guiInventory,playerInventory);
    }

    @Override
    public void open(Player player) {
        if(this.getPlayer() == null) throw new IllegalStateException("No player defined!");
        this.simplePlayerGuiView.getGui().open(player);
    }

    @Override
    public void close(Player player) {
        if(this.getPlayer() == null) throw new IllegalStateException("No player defined!");
        if(this.isOpen(player))player.closeInventory();
    }

    @Override
    public void update() {
        this.simplePlayerGuiView.getGui().update();
    }

    @Override
    public void setPlayer(Player player) {
        this.simplePlayerGuiView.setPlayer(player);
    }

    @Override
    public Player getPlayer() {
        return this.simplePlayerGuiView.getPlayer();
    }
}
