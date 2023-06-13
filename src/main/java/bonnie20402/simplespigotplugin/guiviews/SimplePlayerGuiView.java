package bonnie20402.simplespigotplugin.guiviews;

import bonnie20402.simplespigotplugin.guiviews.base.BasePlayerGuiView;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SimplePlayerGuiView extends BasePlayerGuiView {
    GuiItem infoButton;

    public SimplePlayerGuiView() {
        this.setGui(Gui.gui()
                .title(Component.text("Player Gui View"))
                .disableAllInteractions()
                .rows(3)
                .create());
    }

    @Override
    protected void setupItems() {
        ItemStack itemStack = new ItemStack(Material.PAPER,1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("I got your name, "+this.getPlayer().getName());
        itemStack.setItemMeta(itemMeta);
        infoButton = ItemBuilder.from(itemStack).asGuiItem(inventoryClickEvent -> {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            player.closeInventory();
            player.sendMessage("You closed the view, and player name grabbed was " + getPlayer().getName());
        });
    }

    @Override
    protected void addItems() {
        getGui().setItem(2,5,infoButton);
    }
}
