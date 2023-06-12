package bonnie20402.simplewarpplugin.controllers.home;

import bonnie20402.simplewarpplugin.gsonadapters.LocationAdapter;
import bonnie20402.simplewarpplugin.models.HomeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.List;
import java.util.Objects;

public final class HomeController {

    private static final String HOME_FOLDER_NAME = "homes";

    private final List<HomeModel> homes;
    private final Plugin plugin;
    private final GsonBuilder gsonBuilder;

    private String getHomeFolder() {
        return (this.plugin.getDataFolder().getPath() + File.separator + HomeController.HOME_FOLDER_NAME + File.separator);
    }

    public HomeController(List<HomeModel> homes, Plugin plugin, GsonBuilder gsonBuilder) {
        this.homes = homes;
        this.plugin = plugin;
        this.gsonBuilder = gsonBuilder;
        gsonBuilder.registerTypeAdapter(Location.class,new LocationAdapter());
        gsonBuilder.setPrettyPrinting();
        this.load();
    }

    public boolean hasHome(Player player) {
        for(HomeModel home : homes) {
            if( Objects.equals(home.getOwner(),player.getUniqueId()) ) return true;
        }
        return false;
    }

    public int getHomeIndex(Player player) {
        if(!hasHome(player)) return -1;
        int i = 0;
        for(HomeModel home : homes) {
            if( Objects.equals(home.getOwner(),player.getUniqueId()) ) return i;
            i++;
        }
        return -1;
    }

    public void teleportToHome(Player player) {
        if( !hasHome(player) )  throw new IllegalStateException("This player does NOT have a home!");
        HomeModel playerHome = getPlayerHome(player);
        player.teleport(playerHome.getLocation());
    }

    public HomeModel getPlayerHome(Player player) {
        if( !hasHome(player) )  throw new IllegalStateException("This player does NOT have a home!");
        int index = getHomeIndex(player);
        return homes.get(index);
    }
    public void deleteHome(Player player) {
        if( !hasHome(player) ) throw new IllegalStateException("This player does NOT have a home!");
        int index = getHomeIndex(player);
        homes.remove(index);
    }

    public void updateHome(Player player,HomeModel homeModel) {
        if(!hasHome(player))createHome(player,homeModel);
        int index = getHomeIndex(player);
        if(index != -1) {
            homes.set(index,homeModel);
            this.save();
        }else throw new IllegalStateException("This player does NOT have a home!");

    }

    private void createHome(Player player,HomeModel homeModel) {
        homes.add(homeModel);
    }
    public void load() {
        File homeFolder = new File( this.getHomeFolder() );
        if(homeFolder.mkdirs())this.plugin.getLogger().info("Created home folder!");
        HomeModel homeModel;
        Gson gson = gsonBuilder.create();
        for( File homeFile : Objects.requireNonNull(homeFolder.listFiles()) ) {
            try(JsonReader jsonReader = new JsonReader(new FileReader(homeFile))) {
                homeModel = gson.fromJson(jsonReader,HomeModel.class);
                this.homes.add(homeModel);
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
        for(HomeModel home : homes) {
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






































