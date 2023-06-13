package bonnie20402.simplespigotplugin.controllers.home;

import bonnie20402.simplespigotplugin.models.HomeModel;
import bonnie20402.simplespigotplugin.utils.gsonadapters.LocationAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class HomeController {
    private static final String HOME_FOLDER_NAME = "homes";
    private final HashMap<UUID,HomeModel> homes;
    private final Plugin plugin;
    private final GsonBuilder gsonBuilder;

    private final HashMap<String,String> HOME_MESSAGES;

    private String getHomeFolder() {
        return (this.plugin.getDataFolder().getPath() + File.separator + HomeController.HOME_FOLDER_NAME + File.separator);
    }

    public HomeController(HashMap<UUID,HomeModel> homes, Plugin plugin, GsonBuilder gsonBuilder) {
        this.homes = homes;
        this.plugin = plugin;
        this.gsonBuilder = gsonBuilder;
        gsonBuilder.registerTypeAdapter(Location.class,new LocationAdapter());
        gsonBuilder.setPrettyPrinting();
        this.HOME_MESSAGES = new HashMap<>();
        this.loadMessages();
        this.load();
    }
    private void loadMessages() {
        ConfigurationSection configurationSection = this.plugin.getConfig().getConfigurationSection("home_messages");
        HOME_MESSAGES.clear();
        int i = 0;
        for(String msgKey : configurationSection.getKeys(false)) {
            String msgValue = configurationSection.getString(msgKey);
            HOME_MESSAGES.put(msgKey,msgValue);
            i++;
        }
        this.plugin.getLogger().info("Loaded "+i+" home messages.");
    }
    public boolean hasHome(Player player) {
        return homes.containsKey(player.getUniqueId());
    }

    private HashMap<String, String> getHomeMessages() {
        return HOME_MESSAGES;
    }

    public String getHomeMessage(String key) {
        if(!this.getHomeMessages().containsKey(key))throw new IllegalArgumentException("No message found for provided key!");
        else return this.getHomeMessages().get(key);
    }

    public void teleportToHome(Player player) {
        if( !hasHome(player) )  throw new IllegalStateException("This player does NOT have a home!");
        HomeModel playerHome = getPlayerHome(player);
        player.teleport(playerHome.getLocation());
    }

    public HomeModel getPlayerHome(Player player) {
        if( !hasHome(player) )  throw new IllegalStateException("This player does NOT have a home!");
        return homes.get(player.getUniqueId());
    }
    public void deleteHome(Player player) {
        if( !hasHome(player) ) throw new IllegalStateException("This player does NOT have a home!");
        homes.remove(player.getUniqueId());
    }

    public void updateHome(Player player,HomeModel homeModel) {
        if(!hasHome(player))createHome(homeModel);
        HomeModel playerHome = homes.get(player.getUniqueId());
        playerHome.setLocation(homeModel.getLocation());
        this.save();
    }

    private void createHome(HomeModel homeModel) {
        homes.put(homeModel.getOwner(),homeModel);
    }
    public void load() {
        File homeFolder = new File( this.getHomeFolder() );
        if(homeFolder.mkdirs())this.plugin.getLogger().info("Created home folder!");
        HomeModel homeModel;
        Gson gson = gsonBuilder.create();
        for( File homeFile : Objects.requireNonNull(homeFolder.listFiles()) ) {
            try(JsonReader jsonReader = new JsonReader(new FileReader(homeFile))) {
                homeModel = gson.fromJson(jsonReader,HomeModel.class);
                this.homes.put(homeModel.getOwner(),homeModel);
            }catch (IOException exception) {
                exception.printStackTrace();
            }finally {
                this.plugin.getLogger().info("Loaded "+ Objects.requireNonNull(homeFolder.listFiles()).length + " homes");
            }
        }
    }

    public void save() {
        File homeFolder = new File(this.getHomeFolder());
        Gson gson = gsonBuilder.create();
        int quantity = 0;
        for(HomeModel home : homes.values()) {
            try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(homeFolder + File.separator + home.getOwner().toString() + ".json")) ) {
                String homeModelSerialized = gson.toJson(home);
                bufferedWriter.write(homeModelSerialized);
                quantity++;
            }catch(IOException exception) {
                exception.printStackTrace();
            }finally {
                this.plugin.getLogger().info("Saved " + quantity + " homes");
            }
        }
    }
}






































