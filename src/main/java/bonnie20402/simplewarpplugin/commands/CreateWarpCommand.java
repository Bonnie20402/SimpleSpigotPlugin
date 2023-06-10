package bonnie20402.simplewarpplugin.commands;

import bonnie20402.simplewarpplugin.models.WarpModel;
import bonnie20402.simplewarpplugin.controllers.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateWarpCommand implements CommandExecutor {
    private final WarpController warpController;
    public CreateWarpCommand(WarpController warpController) {
        this.warpController=warpController;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("You are console! This command does not work on console yet!");
            return true;
        }
        if(args.length>=1) {
            Player player = (Player) sender;
            WarpModel newWarp = new WarpModel(player.getLocation(),args[0]);
            if(warpController.findWarpByName(newWarp.getName()) != null )  {
                warpController.updateWarp(newWarp);
                player.sendMessage("That warp already exists. It has been updated with your current location!");
            }
            else {
                warpController.createWarp(newWarp);
                player.sendMessage("Warp " +newWarp.getName()+ " successfully created.");
            }
        }
        return true;
    }
}
