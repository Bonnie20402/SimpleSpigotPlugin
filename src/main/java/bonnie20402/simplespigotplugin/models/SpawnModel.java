package bonnie20402.simplespigotplugin.models;

import org.bukkit.Location;

public final class SpawnModel {
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SpawnModel() {

    }

    public SpawnModel(Location location) {
        this.location = location;
    }
}
