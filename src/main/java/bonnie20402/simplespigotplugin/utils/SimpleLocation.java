package bonnie20402.simplespigotplugin.utils;


import org.bukkit.Location;

public class SimpleLocation {
    private double x, y, z;

    public SimpleLocation() { }

    public SimpleLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SimpleLocation(Location location) {
        setLocation(location);

    }

    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }



    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
