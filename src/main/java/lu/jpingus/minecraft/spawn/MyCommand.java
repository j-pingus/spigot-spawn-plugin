package lu.jpingus.minecraft.spawn;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MyCommand implements CommandExecutor {
    private final Location worldSpawn;
    public MyCommand(Location worldSpawn) {
        this.worldSpawn=worldSpawn;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // Here we need to give items to our player
            player.teleport(worldSpawn);
            System.out.println(player.getName() + " teleported to world spawn");
            return true;
        }
        System.err.println("You are not a player");
        return false;
    }

}
