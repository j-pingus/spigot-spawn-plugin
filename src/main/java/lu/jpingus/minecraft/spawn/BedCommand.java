package lu.jpingus.minecraft.spawn;

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
            player.teleport(player.getBedSpawnLocation());
            player.sendMessage("You just got bedded...");
            return true;
        }
        commandSender.sendMessage("You are not a player");
        return false;
    }

}
