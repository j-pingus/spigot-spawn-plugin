package lu.jpingus.minecraft.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractPlayerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            return onPlayerCommand(player, command, label, args);
        }
        commandSender.sendMessage("You are not a player");
        return false;
    }

    public abstract boolean onPlayerCommand(Player player, Command command, String label, String[] args);
}
