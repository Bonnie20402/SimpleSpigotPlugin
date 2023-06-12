package bonnie20402.simplewarpplugin.guiviews;

import bonnie20402.simplewarpplugin.guiviews.base.BaseGuiView;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SimpleGuiView extends BaseGuiView {

    GuiItem closeButton;

    public SimpleGuiView() {
        this.setGui(
                Gui.gui()
                        .type(GuiType.CHEST)
                        .title(Component.text("Simple View"))
                        .disableAllInteractions()
                        .rows(3)
                        .create()
        );
        setupItems();
        addItems();
    }
    @Override
    protected void setupItems() {
        closeButton = ItemBuilder.from(Material.BARRIER).asGuiItem(inventoryClickEvent ->  {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            player.sendMessage("You closed my menu. Oh shoot!");
            player.closeInventory();
        });
    }

    @Override
    protected void addItems() {
        getGui().setItem(1,5,closeButton);
    }
}
