package bonnie20402.simplewarpplugin.commands.gui;

import bonnie20402.simplewarpplugin.guiviews.PlayerInfoGuiView;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class GuiTestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player))return true;
        Player player = (Player) commandSender;
        PlayerInfoGuiView gui = new PlayerInfoGuiView(player);
        gui.showGui();
        return true;
    }
}
