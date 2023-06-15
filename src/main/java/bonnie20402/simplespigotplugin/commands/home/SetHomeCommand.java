package bonnie20402.simplespigotplugin.commands.home;

import bonnie20402.simplespigotplugin.controllers.home.HomeController;
import bonnie20402.simplespigotplugin.models.home.HomeModel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SetHomeCommand implements CommandExecutor {

    private final HomeController homeController;

    public SetHomeCommand(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player))return true;
        Player player = (Player) sender;
        if( !homeController.hasHome(player) ) sender.sendMessage(ChatColor.GREEN + homeController.getHomeMessage( "homeset" ) );
        else sender.sendMessage(ChatColor.GREEN + homeController.getHomeMessage( "homeupdate" ) );
        HomeModel homeModel = new HomeModel(player.getLocation(),player.getUniqueId());
        homeController.updateHome(player,homeModel);
        return true;

    }
}
