package bonnie20402.simplewarpplugin.controllers;

import bonnie20402.simplewarpplugin.gsonadapters.LocationAdapter;
import bonnie20402.simplewarpplugin.models.WarpModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.List;

public final class WarpController {
    private List<WarpModel> warps;
    private Plugin plugin;

    private Gson gson;
    public WarpController(List<WarpModel> warps, Plugin plugin) {
        this.warps=warps;
        this.plugin=plugin;
        gson = new GsonBuilder()
                .registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
        this.load();
    }

    public void createWarp(WarpModel warp) {
        if(warps.contains(warp)) {
            throw new IllegalArgumentException("The warp " + warp.getName() + "already exists!");
        }
        warps.add(warp);
    }
    public WarpModel findWarp(WarpModel warp) {
        if(warps.contains(warp))return warp;
        return null;
    }

    public void removeWarp(int index) {
        warps.remove(index);
    }
    public void removeWarp(WarpModel warp) {
        warps.remove(warp);
    }
    public WarpModel findWarpByName(String name) {
        for(WarpModel warp : warps) {
            if(warp.getName().equalsIgnoreCase(name))return warp;
        }
        return null;
    }
    public List<WarpModel> getWarps() {
        return warps;
    }
    public void teleportToWarp(Entity entity, WarpModel warp) {
        if(warp == null ) throw new IllegalArgumentException("Could not teleport to invalid warp");
        entity.teleport(warp.getLocation());
    }

    public void save() {
        String path;
        File file;
        for(WarpModel warp : warps) {
            path = plugin.getDataFolder() + File.separator + warp.getName() + ".json";
            file = new File(path);
            if(file.isFile())file.delete();
            String warpSerialized = gson.toJson(warp);
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(warpSerialized);
                writer.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        plugin.getLogger().info("[SimpleWarp] Saved "+ warps.size() + " warps!");
    }

    public void load() {
        File folder = new File(plugin.getDataFolder().toString());
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for(File file : fileList) {
                if(file.isFile() && file.getName().contains(".json")) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        WarpModel warp = gson.fromJson(reader,WarpModel.class);
                        warps.add(warp);
                        reader.close();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            plugin.getLogger().info("Loaded "+fileList.length+" warps.");
        }
        else plugin.getLogger().info("No warps to load because the folder does not exist");

    }
}
