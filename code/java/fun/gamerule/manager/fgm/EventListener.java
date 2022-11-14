package fun.gamerule.manager.fgm;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class EventListener implements Listener {
    @EventHandler
    public void WorldCreateInit(WorldInitEvent event) {
        World world = event.getWorld();
        System.out.println("[FGM] Изменяются правила сервера");

        for (String i : FGM.getData().getConfig().getConfigurationSection("default_gamerules").getKeys(false)) {

            String gamerule_value = FGM.getData().getConfig().getString("default_gamerules." + i);
            world.setGameRuleValue(i, gamerule_value);

        }
        System.out.println("[FGM] Правила изменены");

    }

}
