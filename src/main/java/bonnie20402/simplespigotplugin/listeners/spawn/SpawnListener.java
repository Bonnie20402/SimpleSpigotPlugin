package bonnie20402.simplespigotplugin.listeners.spawn;

import bonnie20402.simplespigotplugin.controllers.spawn.SpawnController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SpawnListener implements Listener {
    private final SpawnController spawnController;
    private final Plugin plugin;

    public SpawnListener(SpawnController spawnController, Plugin plugin) {
        this.spawnController = spawnController;
        this.plugin=plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(spawnController.getSpawn().isPresent()) {
                    spawnController.teleportToSpawn(event.getPlayer());
                }
                else {
                    event.getPlayer().sendMessage(
                            "You know, I (the server) really wanted to teleport you to the my spawn." +
                            "But there is no god damn spawn, would you believe this?!" +
                            " Unacceptable!!!!");
                }
            }
        }.runTaskLater(this.plugin,5L);
    }
    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent event) {
        if(spawnController.getSpawn().isPresent()) spawnController.teleportToSpawn(event.getPlayer());
    }
}
