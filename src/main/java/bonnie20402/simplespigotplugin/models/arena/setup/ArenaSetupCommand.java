package bonnie20402.simplespigotplugin.models.arena.setup;

import bonnie20402.simplespigotplugin.models.arena.ArenaModel;
import bonnie20402.simplespigotplugin.models.arena.manager.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ArenaSetupCommand implements CommandExecutor {
    private final ArenaManager arenaManager;

    public ArenaSetupCommand(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player))return true;
        Player player = (Player) commandSender;
        ArenaSetup playerArenaSetup = this.arenaManager.getArenaSetups().get(player.getUniqueId());
        if ( args.length == 0 ) {
            return true;
        }
        switch (args[0]) {
            case "create" -> {
                playerArenaSetup = new ArenaSetup(new ArenaModel(),args[1]);
                player.sendMessage("Creating arena named " + args[1]);
            }
            case "setlobby" -> {
                playerArenaSetup.setLobbySpawn(player.getLocation());
                player.sendMessage("Defined lobby location for arena! ");
            }
            case "setp1" -> {
                playerArenaSetup.setP1Spawn(player.getLocation());
                player.sendMessage("Defined P1 loc!");
            }
            case "setp2" -> {
                playerArenaSetup.setP2Spawn(player.getLocation());
                player.sendMessage("Defined P2 LOC!");
            }
            case "save" -> {
                if( playerArenaSetup.canCreate() ) {
                    arenaManager.addArena(playerArenaSetup.getArenaModel());
                    player.sendMessage("Saved arena!");
                } else player.sendMessage("Could not save arena, something is missing! ");
            }
            case "goto" -> {
                if( arenaManager.arenaExists(args[1]) ) arenaManager.teleportToArena(player,args[1]);
                else player.sendMessage("The arena named " + args[1] + " does not exist.");
            }
            case "quit" -> {
                ArenaModel arenaModel = arenaManager.getPlayerArena(player);
                if(arenaModel == null) player.sendMessage("You are not in a arena!");
                else arenaManager.teleportFromArena(player);
            }
            default -> {
                showHelp(player);
            }

        }
        save(player.getUniqueId(),playerArenaSetup);
        return true;
    }

    private void save(UUID uuid,ArenaSetup arenaSetup) {
        this.arenaManager.getArenaSetups().replace(uuid,arenaSetup);
    }

    private void showHelp(Player player) {
        player.sendMessage(" Admin commands");
        player.sendMessage(" /arena create <name> - Begins a creation of arena");
        player.sendMessage(" /arena setlobby - Sets the lobby of the current creating arena");
        player.sendMessage(" /arena setp1 - Sets player one spawnpoint");
        player.sendMessage(" /arena setp2 - Sets player two spawnpoint");
        player.sendMessage( " /arena save - saves the arena to disk.");
        player.sendMessage("/arena goto <name> - goes to a arena");
        player.sendMessage( "/arena quit or /arena - leaves a arena.");
    }
}
