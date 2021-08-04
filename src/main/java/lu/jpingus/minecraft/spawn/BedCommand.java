package lu.jpingus.minecraft.spawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BedCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // Here we need to give items to our player
            Location bedLocation = player.getBedSpawnLocation();
            if (bedLocation != null) {
                player.teleport(bedLocation);
                player.sendMessage("You just got bedded...");
            } else {
                player.sendMessage("You know you have " +
                        ChatColor.RED + "no bed" +
                        ChatColor.RESET + " don't you?");
            }
            return true;
        }
        commandSender.sendMessage("You are not a player");
        return false;
    }

}
