package lu.jpingus.minecraft.spawn;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JpingusEventListener implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(new String[]{"Welcome " +
                ChatColor.BLUE + event.getPlayer().getName() + ChatColor.RESET
                + " in the jPingus Cellar",
                "Have you tried " +
                        ChatColor.BOLD + ChatColor.GOLD + "/bed" +
                        ChatColor.RESET + " and " +
                        ChatColor.BOLD + ChatColor.GOLD + "/spawn" +
                        ChatColor.RESET + " ?"
        });
    }
}
