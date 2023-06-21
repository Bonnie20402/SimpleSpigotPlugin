package bonnie20402.simplespigotplugin.models.arena.listeners.vanilla;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final ArenaManager arenaManager;

    public BlockBreakListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent blockBreakEvent) {
        Player player = blockBreakEvent.getPlayer();
        if( arenaManager.isPlayerOnArena(player) ) {
            player.sendMessage("ur on arena");
            ArenaModel arenaModel = arenaManager.getPlayerArena(player);
            if( arenaModel.getArenaState() != ArenaState.ARENA_STATE_FIGHTING ) {
                blockBreakEvent.setCancelled(true);
            }
        }
        else player.sendMessage("not on arena ");
    }
}
