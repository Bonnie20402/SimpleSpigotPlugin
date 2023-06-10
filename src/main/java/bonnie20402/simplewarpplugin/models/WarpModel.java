package bonnie20402.simplewarpplugin.models;

import org.bukkit.Location;

public class WarpModel {
    private Location location;
    private String name;

    public WarpModel(Location location, String name) {
        this.location=location;
        this.name=name;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
}
