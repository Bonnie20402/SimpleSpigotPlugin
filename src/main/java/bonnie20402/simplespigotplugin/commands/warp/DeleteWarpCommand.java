package bonnie20402.simplespigotplugin.commands.warp;
import bonnie20402.simplespigotplugin.controllers.warp.WarpController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public final class DeleteWarpCommand implements CommandExecutor {
    private final WarpController warpController;
    public DeleteWarpCommand(WarpController warpcontroller) {
        this.warpController=warpcontroller;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {;
        if(!(sender instanceof Player))return true;
        if (args.length == 0 ) {
            sender.sendMessage("Usage: /delwarp <name>");
            return true;
        }
        String warpKey = args[0];
        if( warpController.warpExists(warpKey) ) {
            warpController.deleteWarp(warpKey);
            sender.sendMessage("You deleted a warp!");
        }
        else {
            sender.sendMessage("The warp " + warpKey + " does not exist!");
        }
        return true;
    }
}
