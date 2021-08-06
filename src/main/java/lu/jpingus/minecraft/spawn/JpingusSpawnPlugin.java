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
        BaseCommand command = new BaseCommand();
        this.getCommand("spawn").setExecutor(new SpawnCommand(worldSpawn));
        this.getCommand("bed").setExecutor(new BedCommand());
        this.getCommand("base").setExecutor(command);
        this.getCommand("base").setTabCompleter(command);
        this.getCommand("base-set").setExecutor(command);
        this.getCommand("base-set").setTabCompleter(command);
        this.getCommand("base-del").setExecutor(command);
        this.getCommand("base-del").setTabCompleter(command);
        System.out.println("jPingus plungin enabled");
    }
}
