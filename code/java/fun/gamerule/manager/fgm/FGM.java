package fun.gamerule.manager.fgm;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public final class FGM extends JavaPlugin {
    //Список правил и список значений
    List<String> s1 = Arrays.asList("announceAdvancements", "commandBlockOutput", "disableElytraMovementCheck", "disableRaids", "doDaylightCycle", "doEntityDrops", "doFireTick", "doImmediateRespawn", "doInsomnia", "doImmediateRespawn", "doLimitedCrafting", "doMobLoot", "doMobSpawning", "doPatrolSpawning", "doTileDrops", "doTraderSpawning", "doWeatherCycle", "drowningDamage", "fallDamage", "fireDamage", "forgiveDeadPlayers", "keepInventory", "logAdminCommands", "maxCommandChainLength", "maxEntityCramming", "mobGriefing", "naturalRegeneration", "randomTickSpeed", "reducedDebugInfo", "sendCommandFeedback", "showDeathMessages", "spawnRadius", "spectatorsGenerateChunks", "universalAnger");
    List<String> s2 = Arrays.asList("true", "false");

    @Override
    public void onEnable() {
        getCommand("grule").setTabCompleter(new TabCompleter() {

            @Override    //Таб комплитер для табулирования
            public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
                List<String> flist = Lists.newArrayList();

                if(args.length == 1) {
                    for(World world : Bukkit.getWorlds()) {
                        String worldname = world.getName();
                        if(worldname.startsWith(args[0])) {
                            flist.add(worldname);
                            flist.add("help");
                        }
                    }
                    return flist;
                }

                if(args.length == 2) {
                    for(String e: s1) {
                        if(e.startsWith(args[1])) flist.add(e);
                    }
                    return flist;
                }

                if(args.length == 3) {
                    for(String e: s2) {
                        if(e.startsWith(args[2])) flist.add(e);
                    }
                    return flist;
                }

                return null;
            }
        });

        getCommand("grule").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Player player = (Player) sender;
                World world = Bukkit.getWorld(args[0]);

                if(args[0].equals("help")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[FGM] &f/grule <Мир> <Правило> <Значение>" ));
                    return true;
                }

                //Исполнение команды
                world.setGameRuleValue(args[1], args[2]);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[FGM] &fВ мире &6" + args[0] + " &fбыло установлено &6" + args[1] + " " + args[2]));

                return true;
            }
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
