package bonnie20402.simplewarpplugin.commands;

import bonnie20402.simplewarpplugin.models.WarpModel;
import bonnie20402.simplewarpplugin.controllers.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class ListWarpCommand implements CommandExecutor {

    private final WarpController warpController;

    public ListWarpCommand(WarpController warpController) {
        this.warpController = warpController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(String.valueOf(args.length));
        if(args.length == 0 ) {
            showWarpsList(sender);
            return true;
        }
        WarpModel warpToGo = warpController.findWarpByName(args[0]);
        if(warpToGo == null) {
            sender.sendMessage("The warp "+args[0]+" does not exist.");
            return true;
        }
        Player player = (Player) sender;
        warpController.teleportToWarp(player,warpToGo);
        sender.sendMessage("You have been teleported to the warp "+warpToGo.getName());
        return true;
    }

    private void showWarpsList(CommandSender sender) {
        int size = warpController.getWarps().size();
        sender.sendMessage("There are currently " + warpController.getWarps().size() +" warps in the server." + (size > 0 ? "\nHere is a list of them:" : ""));
        StringBuilder message = new StringBuilder();

        for(int i = 0; i < size; i++) {
            WarpModel warp = warpController.getWarps().get(i);
            if(i%2==0) {
                message.append("§6"+warp.getName()+"§f");
            }
            else {
                message.append("§e"+warp.getName()+"$f");
            }
            if(i == size - 2) {
                message.append(", and ");
            }
            else if (i != size - 1) {
                message.append(", ");
            }
        }
        if(message.length() > 0 ) sender.sendMessage(message.toString());
        }
}
