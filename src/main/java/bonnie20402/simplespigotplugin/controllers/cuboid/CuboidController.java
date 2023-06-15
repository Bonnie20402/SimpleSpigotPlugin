package bonnie20402.simplespigotplugin.controllers.cuboid;

import bonnie20402.simplespigotplugin.models.geometry.CuboidModel;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;


public class CuboidController {
    private final Plugin plugin;
    private CuboidModel cuboid;
    private Location locationA;
    private Location locationB;

    private boolean active;

    public CuboidController(Plugin plugin) {
        this.plugin = plugin;
    }

    public void createCuboid(Location locationA, Location locationB) {
        cuboid = new CuboidModel(locationA.getWorld(),
                locationA.getX(),locationA.getY(),locationA.getZ(),
                locationB.getX(),locationB.getY(),locationB.getZ());
    }

    public CuboidModel getCuboid() {
        return cuboid;
    }

    public void setCuboid(CuboidModel cuboid) {
        this.cuboid = cuboid;
    }

    public Location getLocationA() {
        return locationA;
    }

    public void setLocationA(Location locationA) {
        this.locationA = locationA;
    }

    public Location getLocationB() {
        return locationB;
    }

    public void setLocationB(Location locationB) {
        this.locationB = locationB;
    }

    public boolean isActive() {
        return active;
    }

    public boolean contains(Location location) {
        return cuboid.contains(location);
    }
    public boolean overLaps(CuboidModel cuboid) {
        return cuboid.overlaps(cuboid);
    }
    public void setActive(boolean active) {
        if(!active) {
            cuboid = null;
            this.active=false;
        }
        else {
            cuboid = new CuboidModel(locationA,locationB);
            this.active=true;
        }
    }
}
