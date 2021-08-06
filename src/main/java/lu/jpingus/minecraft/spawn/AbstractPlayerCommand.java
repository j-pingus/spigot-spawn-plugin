package lu.jpingus.minecraft.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractPlayerCommand implements CommandExecutor {
    Player getPlayer(CommandSender commandSender) {
        if (commandSender instanceof Player) {
            return (Player) commandSender;
        }
        commandSender.sendMessage("You are not a player");
        return null;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player player = getPlayer(commandSender);
        if (player != null) {
            return onPlayerCommand(player, command, label, args);
        }
        return false;
    }

    public abstract boolean onPlayerCommand(Player player, Command command, String label, String[] args);
}
