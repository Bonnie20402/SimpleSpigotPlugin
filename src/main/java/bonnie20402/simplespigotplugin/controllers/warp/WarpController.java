package bonnie20402.simplespigotplugin.controllers.warp;

import bonnie20402.simplespigotplugin.models.warp.WarpModel;
import bonnie20402.simplespigotplugin.utils.gsonadapters.LocationAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


/*
    TODO: Add custom message support.
 */
public final class WarpController {
    private final Map<String,WarpModel> warps;

    private static final String WARP_FOLDER_NAME = "warps";
    private final Plugin plugin;
    private final Gson gson;

    public WarpController(Map<String,WarpModel> warps, Plugin plugin) {
        this.warps = warps;
        this.plugin = plugin;
        gson = new GsonBuilder()
                .registerTypeAdapter(Location.class, new LocationAdapter())
                .setPrettyPrinting()
                .create();
        this.load();
    }

    private String getHomeFolder() {
        return (this.plugin.getDataFolder().getPath() + File.separator + WarpController.WARP_FOLDER_NAME + File.separator);
    }
    public WarpModel getWarp(String warpKey) {
        if( !this.warpExists(warpKey) ) return null;
        return warps.get(warpKey);
    }

    public void createWarp(WarpModel warp) {
        if ( warps.containsValue(warp) ) throw new IllegalArgumentException("The warp " + warp.getName() + " already exists!");
        warps.put(warp.getName(),warp);
        this.save();
    }

    public boolean warpExists(String warpKey) {
        return warps.containsKey(warpKey);
    }

    public void deleteWarp(String warpKey) {
        if( !warpExists(warpKey) ) return;
        this.deleteWarpFile(warpKey);
        this.save();
    }
    private void deleteWarpFile(String warpKey) {
        File file = new File(this.getHomeFolder() + File.separator + warpKey + ".json");
        if(!file.delete()) {
            plugin.getLogger().info("Could not delete the warp config file for warp key " + warpKey
            + "Here is the file path:" +file);
        }
    }
    public void updateWarp(String warpKey,WarpModel warp) {
        if( !warps.containsKey(warpKey) ) throw new IllegalArgumentException("Tried to overwrite a warp that does not exist");
        warps.get(warpKey).setLocation(warp.getLocation());
        this.save();
    }

    public void teleportToWarp(Entity entity, WarpModel warp) {
        if (warp == null) {
            plugin.getLogger().warning("Could not teleport to invalid warp object.");
            throw new IllegalArgumentException("Could not teleport to invalid warp");
        }
        entity.teleport(warp.getLocation());
    }

    public void save() {
        String path;
        File file;
        int i = 0;
        for (WarpModel warp : warps.values()) {
            path = this.getHomeFolder() + File.separator + warp.getName().toLowerCase() + ".json";
            file = new File(path);
            String warpSerialized = gson.toJson(warp);
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(warpSerialized);
                i++;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        plugin.getLogger().info("Saved " + i + " warps!");
    }

    public ArrayList<String> getWarpList() {
        return new ArrayList<>(warps.keySet());
    }
    public void load() {
        File folder = new File(this.getHomeFolder() + File.separator);
        if ( folder.mkdirs() ) plugin.getLogger().info("Created warps folder! ");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.getName().endsWith(".json")) {
                try( JsonReader jsonReader = new JsonReader(new FileReader(file)) ) {
                    WarpModel warp = gson.fromJson(jsonReader, WarpModel.class);
                    warps.put(warp.getName(),warp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        plugin.getLogger().info("Loaded " + warps.size() + " warps.");
    }
}
