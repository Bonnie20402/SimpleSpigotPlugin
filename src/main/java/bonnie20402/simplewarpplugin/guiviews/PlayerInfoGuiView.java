package bonnie20402.simplewarpplugin.guiviews;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public final class PlayerInfoGuiView {
    Gui gui = Gui.gui(GuiType.CHEST)
            .disableAllInteractions()
            .rows(3)
            .title(Component.text("LOADING"))
            .create();

    GuiItem guiItem;
    Player player;
    public PlayerInfoGuiView(Player player) {
        this.player=player;
    }

    public void showGui() {
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwningPlayer(player);
        gui.updateTitle("Welcome, "+player.getName());
        ArrayList<String> lores = new ArrayList<>();
        lores.add("");
        lores.add("§7Name:" + player.getName());
        lores.add("");
        meta.setDisplayName(player.getName());
        meta.setLore(lores);
        stack.setItemMeta(meta);


        guiItem = ItemBuilder.from(stack).asGuiItem(inventoryClickEvent -> {
           inventoryClickEvent.getClickedInventory().close();
        });

        gui.setItem(2,5,guiItem);
        gui.open(player);
    }
}

