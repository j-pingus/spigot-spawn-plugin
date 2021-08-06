package lu.jpingus.minecraft.spawn;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class BedCommand extends AbstractPlayerCommand {
    @Override
    public boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        player.teleport(player.getBedSpawnLocation());
        player.sendMessage("You just got bedded...");
        return true;
    }

}
