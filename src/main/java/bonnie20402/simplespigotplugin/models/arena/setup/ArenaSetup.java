package bonnie20402.simplespigotplugin.models.arena.setup;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.utils.SimpleLocation;
import org.bukkit.Location;

public class ArenaSetup {
    private final ArenaModel arenaModel;

    public ArenaSetup(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    public ArenaSetup(ArenaModel arenaModel, String name) {
        this.arenaModel = arenaModel;
        this.arenaModel.setArenaName(name);
    }

    public void setLobbySpawn(Location location) {
        this.arenaModel.setLobbyLoc( new SimpleLocation(location.getX(), location.y(), location.getZ()) );
        updateWorld(location);
    }

    public void setP1Spawn(Location location) {
        this.arenaModel.setP1Loc( new SimpleLocation(location.getX(), location.y(), location.getZ()) );
        updateWorld(location);
    }
    public void setP2Spawn(Location location) {
        this.arenaModel.setP2Loc( new SimpleLocation(location.getX(), location.y(), location.getZ()) );
        updateWorld(location);
    }


    private void updateWorld(Location location) {
        this.arenaModel.setArenaTemplate(location.getWorld().getName());
    }

    public boolean canCreate() {
        if (arenaModel.getArenaTemplate() == null || arenaModel.getArenaName() == null
                || arenaModel.getLobbyLoc() == null || arenaModel.getP1Loc() == null
                || arenaModel.getP2Loc() == null) return false;
        return true;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }
}
