package bonnie20402.simplespigotplugin.commands.warp;
import bonnie20402.simplespigotplugin.controllers.warp.WarpController;
import bonnie20402.simplespigotplugin.models.WarpModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
public final class DeleteWarpCommand implements CommandExecutor {
    private final WarpController warpController;
    public DeleteWarpCommand(WarpController warpcontroller) {
        this.warpController=warpcontroller;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {;

        if(args.length == 0) {
            sender.sendMessage("No arguments provided.");
            return true;
        }
        WarpModel warp = warpController.findWarpByName(args[0]);
        if(warp == null )  {
            sender.sendMessage("Could not find a warp named "+args[0]);
            return true;
        }
        else {
            warpController.removeWarp(warp);
            sender.sendMessage("You have removed the warp "+args[0]);
        }
        return true;
    }
}
