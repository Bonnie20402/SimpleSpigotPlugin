package bonnie20402.simplespigotplugin.listeners.cuboid;

import bonnie20402.simplespigotplugin.controllers.cuboid.CuboidController;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CuboidListener implements Listener {

    private final CuboidController cuboidController;

    public CuboidListener(CuboidController cuboidController) {
        this.cuboidController = cuboidController;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        Location to = playerMoveEvent.getTo();
        Player player = playerMoveEvent.getPlayer();
        //If only yaw and pitch changes
        if(!cuboidController.isActive()) return;
        if( cuboidController.getCuboid().contains(to)) {
            player.sendMessage("You can't get there!");
            playerMoveEvent.setCancelled(true);
        }
    }
}
