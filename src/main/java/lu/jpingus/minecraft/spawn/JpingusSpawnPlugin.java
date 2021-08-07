package lu.jpingus.minecraft.spawn;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class JpingusSpawnPlugin extends JavaPlugin {
    FileConfiguration config = getConfig();

    @Override
    public void onDisable() {
        System.out.println("jPingus plungin disabled");
    }

    @Override
    public void onEnable() {
        Location worldSpawn = getServer().getWorld("world").getSpawnLocation();
        this.getServer().getPluginManager().registerEvents(new JpingusEventListener(), this);
        BaseCommand command = new BaseCommand(this);
        this.getCommand("spawn").setExecutor(new SpawnCommand(worldSpawn));
        this.getCommand("bed").setExecutor(new BedCommand());
        this.getCommand("base").setExecutor(command);
        this.getCommand("base").setTabCompleter(command);
        this.getCommand("base-save").setExecutor(command);
        this.getCommand("base-save").setTabCompleter(command);
        this.getCommand("base-del").setExecutor(command);
        this.getCommand("base-del").setTabCompleter(command);
        System.out.println("jPingus plungin enabled");
    }
}
