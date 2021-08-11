package lu.jpingus.minecraft.cellar;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JpingusGreetPlayer implements Listener {


    private final JavaPlugin plugin;

    public JpingusGreetPlayer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String getPlayerGreeting(Player player) {
        String id = "greet." + player.getUniqueId().toString();
        String defaultGreeting = "Welcome " + ChatColor.BLUE + player.getName() + ChatColor.RESET + " in the jPingus Cellar";
        FileConfiguration config = plugin.getConfig();
        if (config.contains(id)) {
            return config.getString(id);
        } else {
            config.set(id, defaultGreeting);
            plugin.saveConfig();
            return defaultGreeting;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(new String[]{
                getPlayerGreeting(event.getPlayer()),
                "Have you tried " +
                        ChatColor.BOLD + ChatColor.GOLD + "/bed" +
                        ChatColor.RESET + ", " +
                        ChatColor.BOLD + ChatColor.GOLD + "/spawn" +
                        ChatColor.RESET + " and " +
                        ChatColor.BOLD + ChatColor.GOLD + "/base..." +
                        ChatColor.RESET + " commands ?"
        });
    }
}
