package bonnie20402.simplewarpplugin.models;

import org.bukkit.Location;

import java.util.UUID;

public final class HomeModel {
    private Location location;
    private UUID owner;

    public HomeModel() { }
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

    public void setOwner(UUID owner) {
        this.owner = owner;
    }
}
