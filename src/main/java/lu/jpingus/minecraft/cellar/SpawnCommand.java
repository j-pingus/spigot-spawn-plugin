package lu.jpingus.minecraft.cellar;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class SpawnCommand extends AbstractPlayerCommand {
    private final Location worldSpawn;

    public SpawnCommand(Location worldSpawn) {
        this.worldSpawn = worldSpawn;
    }

    @Override
    public boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        player.teleport(worldSpawn);
        player.sendMessage("You just got spawned...");
        return true;
    }

}
