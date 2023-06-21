package bonnie20402.simplespigotplugin.models.arena.listeners.vanilla;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.enums.ArenaState;
import bonnie20402.simplespigotplugin.models.arena.events.ArenaDeathEvent;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    private final ArenaManager arenaManager;

    public EntityDamageListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent entityDamageEvent) {
        if(!(entityDamageEvent.getEntity() instanceof Player) )return;
        Player victim = (Player) entityDamageEvent.getEntity();
        if( arenaManager.isPlayerOnArena(victim) ) {
            ArenaModel arenaModel = arenaManager.getPlayerArena(victim);
            if( arenaModel.getArenaState() != ArenaState.ARENA_STATE_FIGHTING ) {
                entityDamageEvent.setCancelled(true);
            }
            else if( entityDamageEvent.getFinalDamage() >= victim.getHealth() ) {
                //right here the error fires
                entityDamageEvent.setCancelled(true);
                ArenaDeathEvent arenaDeathEvent = new ArenaDeathEvent(arenaModel,victim);
                arenaDeathEvent.callEvent();
            }
        }

    }
}
