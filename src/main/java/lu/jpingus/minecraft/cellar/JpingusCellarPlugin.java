package lu.jpingus.minecraft.cellar;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class JpingusCellarPlugin extends JavaPlugin {
    FileConfiguration config = getConfig();

    @Override
    public void onDisable() {
        System.out.println("jPingus plungin disabled");
    }

    @Override
    public void onEnable() {
        Location worldSpawn = getServer().getWorld("world").getSpawnLocation();
        this.getServer().getPluginManager().registerEvents(new JpingusGreetPlayer(this), this);
        CreatureSpawnListener listener = new CreatureSpawnListener();
        this.getServer().getPluginManager().registerEvents(listener, this);
        BaseCommand baseCommand = new BaseCommand(this);
        this.getCommand("spawn").setExecutor(new SpawnCommand(worldSpawn));
        this.getCommand("bed").setExecutor(new BedCommand());
        this.getCommand("base").setExecutor(baseCommand);
        this.getCommand("base").setTabCompleter(baseCommand);
        this.getCommand("base-save").setExecutor(baseCommand);
        this.getCommand("base-save").setTabCompleter(baseCommand);
        this.getCommand("base-del").setExecutor(baseCommand);
        this.getCommand("base-del").setTabCompleter(baseCommand);
        MonitorCreatureSpawnCommand monitor = new MonitorCreatureSpawnCommand(listener);
        this.getCommand("monitor-spawn").setExecutor(monitor);
        this.getCommand("monitor-spawn").setTabCompleter(monitor);
        System.out.println("jPingus plungin enabled");
    }
}
