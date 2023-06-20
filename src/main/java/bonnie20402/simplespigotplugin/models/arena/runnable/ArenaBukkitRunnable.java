package bonnie20402.simplespigotplugin.models.arena.runnable;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaBukkitRunnable extends BukkitRunnable {
    private final ArenaModel arenaModel;

    public ArenaBukkitRunnable(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }

    @Override
    public void run() {

    }
}
