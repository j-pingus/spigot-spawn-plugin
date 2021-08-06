package lu.jpingus.minecraft.spawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class BedCommand extends AbstractPlayerCommand {
    @Override
    public boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
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

}
