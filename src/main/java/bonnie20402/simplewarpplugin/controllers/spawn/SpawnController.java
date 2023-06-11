package bonnie20402.simplewarpplugin.controllers.spawn;

import bonnie20402.simplewarpplugin.gsonadapters.LocationAdapter;
import bonnie20402.simplewarpplugin.models.SpawnModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.Optional;

public final class SpawnController {
    private SpawnModel spawn;
    private final File file;
    private final Plugin plugin;
    private final Gson gson;
    public SpawnController(Plugin plugin,SpawnModel spawn) {
        this.spawn=spawn;
        this.plugin=plugin;
        gson = new GsonBuilder()
                .registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
        file = new File(plugin.getDataFolder().toString() + File.separator + "spawn.json");
        this.load();
    }
    public void updateSpawn(SpawnModel spawn) {
        this.spawn=spawn;
        this.save();
    }
    public boolean isSpawnSet() {
        return this.getSpawn().isPresent();
    }
    public Optional<SpawnModel> getSpawn() {
        return Optional.ofNullable(this.spawn);
    }
    public void teleportToSpawn(Entity entity) {
        entity.teleport(spawn.getLocation());
    }
    public void deleteSpawn() {
        this.spawn=null;
        this.save();

    }

    public void save() {
        if(spawn != null) {
            String spawnSerialized = gson.toJson(spawn);
            FileWriter writer;
            try {
                writer = new FileWriter(file);
                writer.write(spawnSerialized);
                writer.close();
            }catch(IOException exception) {
                exception.printStackTrace();
            }
        }
        else {
            file.delete();
        }
    }

    public void load() {
        if(!file.isFile()) {
            try {
                file.createNewFile();
            }catch (IOException exception) {
                plugin.getServer().getLogger().warning("Could not create the spawn.json file. Am I missing writing permissions?!\n"+ exception);
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            spawn = gson.fromJson(reader,SpawnModel.class);
            reader.close();
        }catch(IOException exception) {
            plugin.getServer().getLogger().warning("Could not read spawn.json!\n"+exception);
        }

    }

}
