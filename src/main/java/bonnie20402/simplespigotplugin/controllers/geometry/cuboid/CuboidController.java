package bonnie20402.simplespigotplugin.controllers.geometry.cuboid;

import bonnie20402.simplespigotplugin.models.geometry.CuboidModel;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;


public class CuboidController {
    private final Plugin plugin;
    private CuboidModel cuboid;
    private Location locationA;
    private Location locationB;

    public CuboidController(Plugin plugin) {
        this.plugin = plugin;
    }

    public void createCuboid(Location locationA, Location locationB) {
        cuboid = new CuboidModel(locationA.getWorld(),
                locationA.getX(),locationA.getY(),locationA.getZ(),
                locationB.getX(),locationB.getY(),locationB.getZ());
    }

}
