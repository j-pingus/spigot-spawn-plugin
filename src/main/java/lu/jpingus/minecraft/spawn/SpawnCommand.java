package lu.jpingus.minecraft.spawn;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final Location worldSpawn;
    public SpawnCommand(Location worldSpawn) {
        this.worldSpawn=worldSpawn;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // Here we need to give items to our player
            player.teleport(worldSpawn);
            player.sendMessage("You just got spawned...");
            return true;
        }
        commandSender.sendMessage("You are not a player");
        return false;
    }

}