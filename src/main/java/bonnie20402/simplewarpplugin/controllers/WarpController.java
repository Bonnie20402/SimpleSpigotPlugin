package bonnie20402.simplewarpplugin.controllers;

import bonnie20402.simplewarpplugin.gsonadapters.LocationAdapter;
import bonnie20402.simplewarpplugin.models.WarpModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

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

    public void createWarp(@NotNull WarpModel warp) {
        if(warps.contains(warp)) {
            throw new IllegalArgumentException("The warp " + warp.getName() + "already exists!");
        }
        warps.add(warp);
        this.save();
    }
    public void deleteWarp(@NotNull WarpModel warp) {
        if(warp == null) throw new IllegalArgumentException("Tried to remove a warp, but got null!");
        if(!warps.contains(warp)) throw new IllegalArgumentException("Tried to remove a warp that does NOT exist");
        warps.remove(warp);
        this.save();
    }
    public void updateWarp(@NotNull WarpModel warp) {
        WarpModel warpToUpdate = this.findWarpByName(warp.getName());
        if(warpToUpdate == null) throw new IllegalArgumentException("Tried to overwrite a warp that does not exist");
        warpToUpdate.setLocation(warp.getLocation());
        this.save();
    }
    public WarpModel findWarp(@NotNull WarpModel warp) {
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


    public int getWarpIndex(WarpModel warp) {
        int size = warps.size();
        WarpModel warpFor;
        for(int i = 0;i<size;i++) {
            warpFor = warps.get(i);
            if(warp.getName().equalsIgnoreCase(warp.getName()))return i;
        }
        return -1;
    }
    public List<WarpModel> getWarps() {
        return warps;
    }
    public void teleportToWarp(Entity entity, WarpModel warp) {
        if(warp == null )  {
            plugin.getLogger().warning("Could not teleport to invalid warp object.");
            throw new IllegalArgumentException("Could not teleport to invalid warp");
        }
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

        if(warps.isEmpty()) plugin.getLogger().info("Something told me to save warps, but are no warps to save.");
        else plugin.getLogger().info("Saved "+ warps.size() + " warps!");
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
