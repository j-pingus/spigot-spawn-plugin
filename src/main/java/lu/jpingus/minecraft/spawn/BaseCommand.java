package lu.jpingus.minecraft.spawn;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

enum BaseCommandName {
    BASE("base"),
    BASE_DELETE("base-del"),
    BASE_SAVE("base-save");

    String commandName;

    BaseCommandName(String commandName) {
        this.commandName = commandName;
    }

    static BaseCommandName resolveCommandName(String name) {
        return Stream.of(BaseCommandName.values())
                .filter(command -> command.commandName.equals(name))
                .findFirst()
                .orElseThrow(() -> new Error(name + " is not a supported command"));
    }
}

public class BaseCommand extends AbstractPlayerCommand implements TabCompleter {
    private final int maxBases;
    Pattern acceptableName = Pattern.compile("[a-z0-9]{1,15}");
    Map<String, Map<String, Location>> bases = new HashMap<>();

    public BaseCommand(int maxBases) {
        this.maxBases = maxBases;
        System.out.println("Maxbase : " +
                (maxBases == -1
                        ? "Ubounded"
                        : "" + maxBases));
    }

    @Override
    public boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        BaseCommandName commandName = BaseCommandName.resolveCommandName(command.getName());
        if (args.length != 1) return false;
        String baseName = args[0].toLowerCase();
        if (!acceptableName.matcher(baseName.toLowerCase()).matches()) {
            player.sendMessage(baseName + " is too complex, or too long.  Only max 15 characters (a-z and 0-9) ");
        }
        String playerId = player.getUniqueId().toString();
        Map<String, Location> playerBases;
        if (!bases.containsKey(playerId)) {
            bases.put(playerId, new HashMap<>());
        }
        playerBases = bases.get(playerId);
        switch (commandName) {
            case BASE:
                if (playerBases.containsKey(baseName)) {
                    player.teleport(playerBases.get(baseName));
                } else {
                    player.sendMessage("Don't know where " + baseName + " is... did you save it?");
                }
                break;
            case BASE_SAVE:

                Location current = player.getLocation();
                if (playerBases.containsKey(baseName) || playerBases.size() <= maxBases || maxBases == -1) {
                    playerBases.put(baseName, current);
                    return true;
                } else {
                    player.sendMessage("You cas save maximum " + maxBases + " bases");
                }
                return true;
            case BASE_DELETE:
                break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList("Hello", "world");
    }
}
