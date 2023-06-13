package bonnie20402.simplespigotplugin.models;

import org.bukkit.Location;

import java.util.UUID;

public final class HomeModel {
    private Location location;
    private final UUID owner;

    public HomeModel(Location location, UUID owner) {
        this.location = location;
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UUID getOwner() {
        return owner;
    }

}
