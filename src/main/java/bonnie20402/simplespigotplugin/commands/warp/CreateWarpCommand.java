package bonnie20402.simplespigotplugin.commands.warp;

import bonnie20402.simplespigotplugin.controllers.warp.WarpController;
import bonnie20402.simplespigotplugin.models.WarpModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class CreateWarpCommand implements CommandExecutor {
    private final WarpController warpController;
    public CreateWarpCommand(WarpController warpController) {
        this.warpController=warpController;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player))return true;
        if(args.length>=1) {
            Player player = (Player) sender;
            String warpKey = args[0];
            WarpModel newWarp = new WarpModel(player.getLocation(),warpKey);
            if( warpController.warpExists(warpKey) ) {
                warpController.updateWarp(warpKey,newWarp);
                sender.sendMessage("Warp has been updated!");
            }
            else {
                warpController.createWarp(newWarp);
                sender.sendMessage("Created warp!!");
            }
            return true;
        }
        sender.sendMessage("Usage: /setwarp <name>");
        return true;
    }
}
