package bonnie20402.simplespigotplugin.models.geometry;

import org.bukkit.Location;
import org.bukkit.World;

public class CuboidModel {
    private final World world;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;
    private final double minZ;
    private final double maxZ;

    public CuboidModel(Location loc1, Location loc2) {
        this(loc1.getWorld(), loc1.getBlockX(), loc1.getBlockY(), loc1.getBlockZ(), loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
    }
    public CuboidModel(World world, double x1, double y1, double z1, double x2, double y2, double z2) {
        this.world = world;

        minX = Math.min(x1, x2);
        minY = Math.min(y1, y2);
        minZ = Math.min(z1, z2);
        maxX = Math.max(x1, x2);
        maxY = Math.max(y1, y2);
        maxZ = Math.max(z1, z2);
    }

    public World getWorld() {
        return world;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMinZ() {
        return minZ;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMaxZ() {
        return maxZ;
    }

    public boolean contains(CuboidModel cuboid) {
        return cuboid.getWorld().equals(world) &&
                cuboid.getMinX() >= minX && cuboid.getMaxX() <= maxX &&
                cuboid.getMinY() >= minY && cuboid.getMaxY() <= maxY &&
                cuboid.getMinZ() >= minZ && cuboid.getMaxZ() <= maxZ;
    }

    public boolean contains(Location location) {
        return contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public boolean contains(double x, double y, double z) {
        return x >= minX && x <= maxX &&
                y >= minY && y <= maxY &&
                z >= minZ && z <= maxZ;
    }

    public boolean overlaps(CuboidModel cuboid) {
        return cuboid.getWorld().equals(world) &&
                !(cuboid.getMinX() > maxX || cuboid.getMinY() > maxY || cuboid.getMinZ() > maxZ ||
                        minZ > cuboid.getMaxX() || minY > cuboid.getMaxY() || minZ > cuboid.getMaxZ());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CuboidModel)) {
            return false;
        }
        final CuboidModel other = (CuboidModel) obj;
        return world.equals(other.world)
                && minX == other.minX
                && minY == other.minY
                && minZ == other.minZ
                && maxX == other.maxX
                && maxY == other.maxY
                && maxZ == other.maxZ;
    }

    @Override
    public String toString() {
        return "Cuboid[world:" + world.getName() +
                ", minX:" + minX +
                ", minY:" + minY +
                ", minZ:" + minZ +
                ", maxX:" + maxX +
                ", maxY:" + maxY +
                ", maxZ:" + maxZ + "]";
    }
}
