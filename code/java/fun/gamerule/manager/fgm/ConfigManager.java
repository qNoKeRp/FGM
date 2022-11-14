package fun.gamerule.manager.fgm;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private File file;
    private FileConfiguration config;

    public ConfigManager(String name) {
        file = new File(FGM.getInstance().getDataFolder(), name);
        try {
            if(!file.exists() && !file.createNewFile()) throw new IOException();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать config.yml");
        }

        config = YamlConfiguration.loadConfiguration(file);

    }
    public FileConfiguration getConfig() {
        return config;

    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить config.yml");
        }
    }

}