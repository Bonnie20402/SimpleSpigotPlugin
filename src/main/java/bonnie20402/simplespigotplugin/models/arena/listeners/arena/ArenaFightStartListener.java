package bonnie20402.simplespigotplugin.models.arena.listeners.arena;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaFightStartEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaFightStartListener implements Listener {
    @EventHandler
    private void onFightStart(ArenaFightStartEvent arenaFightStartEvent) {
        ArenaModel arenaModel = arenaFightStartEvent.getArenaModel();
        Player p1 = arenaFightStartEvent.getP1();
        Player p2 = arenaFightStartEvent.getP2();

        p1.teleport(arenaModel.getP1Spawn());
        p2.teleport(arenaModel.getP2Spawn());

    }
}
