package bonnie20402.simplespigotplugin.commands.warp;

import bonnie20402.simplespigotplugin.controllers.warp.WarpController;
import bonnie20402.simplespigotplugin.models.WarpModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class ListWarpCommand implements CommandExecutor {

    private final WarpController warpController;

    public ListWarpCommand(WarpController warpController) {
        this.warpController = warpController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0 ) {
            showWarpsList(sender);
            return true;
        }
        Player player = (Player) sender;
        String warpKey = args[0];

        if(warpController.warpExists(warpKey)) {
            WarpModel warp = warpController.getWarp(warpKey);
            warpController.teleportToWarp(player,warp);
            sender.sendMessage("You have been teleported to the warp " + args[0]);
        }
        else sender.sendMessage("The warp " + args[0] + "does not exist.");
        return true;
    }

    private void showWarpsList(CommandSender sender) {
        int size = warpController.getWarpList().size();
        ArrayList<String> warpNameList = warpController.getWarpList();
        sender.sendMessage("There are currently " + size +" warps in the server." + (size > 0 ? "\nHere is a list of them:" : ""));
        StringBuilder message = new StringBuilder();
        int i = 0;
        for(String warpName : warpNameList) {
            if ( i != size-1 && i != size) {
                message.append(warpName).append(", ");
            }
            else if( size > 1) {
                message.append(" and ").append(warpName);
            }
            else {
                message.append(warpName);
            }
            i++;
        }
        if(size > 0 ) sender.sendMessage(message.toString());
        }
}
