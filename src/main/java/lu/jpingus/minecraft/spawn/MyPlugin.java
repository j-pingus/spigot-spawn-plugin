package lu.jpingus.minecraft.spawn;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class MyPlugin extends JavaPlugin {

    @Override
    public void onDisable() {
        System.out.println("jPingus plungin disabled");
    }

    @Override
    public void onEnable() {
        Location worldSpawn = getServer().getWorld("world").getSpawnLocation();

        this.getCommand("spawn").setExecutor(new MyCommand(worldSpawn));
        System.out.println("jPingus plungin enabled");
    }
}
