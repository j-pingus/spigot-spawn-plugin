package lu.jpingus.minecraft.spawn;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class JpingusSpawnPlugin extends JavaPlugin {

    @Override
    public void onDisable() {
        System.out.println("jPingus plungin disabled");
    }

    @Override
    public void onEnable() {
        Location worldSpawn = getServer().getWorld("world").getSpawnLocation();

        this.getCommand("spawn").setExecutor(new SpawnCommand(worldSpawn));
        this.getCommand("bed").setExecutor(new BedCommand());
        System.out.println("jPingus plungin enabled");
    }
}
