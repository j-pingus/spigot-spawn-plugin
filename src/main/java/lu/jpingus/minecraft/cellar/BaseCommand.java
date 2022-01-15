package lu.jpingus.minecraft.cellar;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum BaseCommandType {
    BASE("base"),
    BASE_DELETE("base-del"),
    BASE_SAVE("base-save");

    String commandName;

    BaseCommandType(String commandName) {
        this.commandName = commandName;
    }

    static BaseCommandType resolveCommandName(String name) {
        return Stream.of(BaseCommandType.values())
                .filter(command -> command.commandName.equals(name))
                .findFirst()
                .orElseThrow(() -> new Error(name + " is not a supported command"));
    }
}

public class BaseCommand extends AbstractPlayerCommand implements TabCompleter {
    private final int maxBases;
    private final JavaPlugin plugin;
    Pattern acceptableName = Pattern.compile("[a-z0-9]{1,15}");
    Map<String, Map<String, Location>> bases = new HashMap<>();

    public BaseCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        config.addDefault("maxBases", -1);
        plugin.saveConfig();
        int maxBases = config.getInt("maxBases");
        this.maxBases = maxBases;
        System.out.println("Maxbase : " +
                (maxBases == -1
                        ? "Ubounded"
                        : "" + maxBases));
    }

    void saveConfig(Player player, String base, Location location) {
        String playerId = player.getUniqueId().toString();
        String world = location.getWorld().getName();
        Double x = location.getX();
        Double y = location.getY();
        Double z = location.getZ();
        FileConfiguration config = plugin.getConfig();
        String prefix = "base." + playerId + "." + base + ".";
        config.set(prefix + "x", x);
        config.set(prefix + "y", y);
        config.set(prefix + "z", z);
        config.set(prefix + "world", world);
        plugin.saveConfig();
    }

    void removeConfig(Player player, String base) {
        String playerId = player.getUniqueId().toString();
        FileConfiguration config = plugin.getConfig();
        String prefix = "base." + playerId + "." + base;
        config.set(prefix, null);
        plugin.saveConfig();
    }

    Map<String, Location> loadConfig(String playerId) {
        FileConfiguration config = plugin.getConfig();
        Object o = config.get("base." + playerId);
        if (o == null) return new HashMap<>();
        MemorySection section = (MemorySection) o;
        Map<String, Location> playerConfig = new HashMap<>();
        section.getKeys(false).forEach(key -> {
            String prefix = "base." + playerId + "." + key + ".";
            String world = config.getString(prefix + "world");
            double x = config.getDouble(prefix + "x");
            double y = config.getDouble(prefix + "y");
            double z = config.getDouble(prefix + "z");
            playerConfig.put(key, new Location(plugin.getServer().getWorld(world), x, y, z));
        });
        return playerConfig;
    }

    @Override
    public boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        BaseCommandType commandName = BaseCommandType.resolveCommandName(command.getName());
        if (args.length != 1) return false;
        String baseName = args[0].toLowerCase();
        if (!acceptableName.matcher(baseName.toLowerCase()).matches()) {
            player.sendMessage(baseName + " is too complex, or too long.  Only max 15 characters (a-z and 0-9) ");
        }
        Map<String, Location> playerBases = getPlayerBases(player);
        switch (commandName) {
            case BASE:
            case BASE_DELETE:
                if (playerBases.containsKey(baseName)) {
                    if (commandName == BaseCommandType.BASE) {
                        player.teleport(playerBases.get(baseName));
                    } else {
                        playerBases.remove(baseName);
                        removeConfig(player, baseName);
                    }
                    return true;
                } else {
                    player.sendMessage("Don't know where " + baseName + " is... did you save it?");
                }
                break;
            case BASE_SAVE:

                Location current = player.getLocation();
                if (playerBases.containsKey(baseName) || playerBases.size() <= maxBases || maxBases == -1) {
                    playerBases.put(baseName, current);
                    saveConfig(player, baseName, current);
                    return true;
                } else {
                    player.sendMessage("You can save maximum " + maxBases + " bases");
                }
                return true;
        }
        return false;
    }

    Map<String, Location> getPlayerBases(Player player) {
        String playerId = player.getUniqueId().toString();
        if (!bases.containsKey(playerId)) {
            bases.put(playerId, loadConfig(playerId));
        }
        return bases.get(playerId);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = getPlayer(sender);
        if (player == null) return Collections.emptyList();
        Map<String, Location> playerBases = getPlayerBases(player);
        return new ArrayList<>(playerBases.keySet());
    }
}
