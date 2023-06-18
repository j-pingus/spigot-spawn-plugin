package lu.jpingus.minecraft.cellar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MonitorCreatureSpawnCommand extends AbstractPlayerCommand implements TabCompleter {
    private final CreatureSpawnListener listener;
    private final List<String> creatures;
    private List<String> onOffList;

    public MonitorCreatureSpawnCommand(CreatureSpawnListener listener){
        this.listener = listener;
        creatures=Arrays.stream(EntityType.values())
                .filter(e->e.isSpawnable())
                .map(e->e.name()).toList();
        onOffList = Arrays.asList("on","off");
    }
    @Override
    public boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        if(args.length==2){
            boolean listen = "on".equalsIgnoreCase(args[1]);
            Optional<String> creature = creatures.stream().filter(c->c.equalsIgnoreCase(args[0])).findFirst();
            if(creature.isPresent()){
                if(listen) {
                    this.listener.listen(player, creature.get());
                }else{
                    this.listener.stopListening(player, creature.get());
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length==1){
            return creatures.stream().filter(c->c.toUpperCase().contains(args[0].toUpperCase())).toList();
        }else if(args.length==2){
            return onOffList;
        }
        return null;
    }
}
