package lu.jpingus.minecraft.cellar;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.*;

public class CreatureSpawnListener implements Listener {
    private Map<String, Set<Player>> listen = new HashMap<>();
    public void listen(Player listener, String creature ){
        if(!listen.containsKey(creature)){
            listen.put(creature,new HashSet<>());
        }
        listen.get(creature).add(listener);
        Bukkit.getLogger().info("Player:"+listener.getName()+" monitors "+creature);
        listener.sendMessage("You are monitoring "+creature+" spawn");
    }
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        EntityType type = event.getEntityType();
        Location location = event.getLocation();
        CreatureSpawnEvent.SpawnReason reason = event.getSpawnReason();
        if(listen.containsKey(type.name())){
            listen.get(type.name()).forEach(player->player.sendMessage(type.name()+" just spawned here ("
                    +location.getX()+","
                    +location.getY()+","
                    +location.getZ()
                    +")@"+location.getWorld()+
                    " because "+reason.name()));

        }
    }

    public void stopListening(Player listener, String creature) {
        if(!listen.containsKey(creature)){
            return;
        }
        listen.get(creature).remove(listener);
    }
}
