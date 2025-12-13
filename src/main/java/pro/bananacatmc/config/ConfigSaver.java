/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc.config;

import com.google.gson.Gson;
import pro.bananacatmc.BananaCat;
import pro.bananacatmc.feature.mod.Mod;
import pro.bananacatmc.feature.option.Option;
import pro.bananacatmc.gui.Style;
import pro.bananacatmc.helpers.OSHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigSaver {

    /**
     * Creates and saves a configuration in .minecraft/BananaCat/config.json
     */

    public static void saveConfig() throws IOException {
        createDir();

        FileWriter writer = new FileWriter(OSHelper.getBananaCatDirectory() + "config.json");

        Config config = new Config();

        for (Mod mod : BananaCat.INSTANCE.modManager.getMods()) {
            ModConfig modConfig = new ModConfig(
                    mod.getName(),
                    mod.isToggled(),
                    BananaCat.INSTANCE.settingManager.getSettingsByMod(mod),
                    BananaCat.INSTANCE.hudEditor.getHudMod(mod.getName()) != null ?
                            new int[] { BananaCat.INSTANCE.hudEditor.getHudMod(mod.getName()).getX(), BananaCat.INSTANCE.hudEditor.getHudMod(mod.getName()).getY() } :
                            new int[] { 0, 0 },
                    BananaCat.INSTANCE.hudEditor.getHudMod(mod.getName()) != null ?
                            BananaCat.INSTANCE.hudEditor.getHudMod(mod.getName()).getSize() : 1
            );
            config.addConfig(modConfig);
        }

        for(Option option : BananaCat.INSTANCE.optionManager.getOptions()){
            config.addConfigOption(option);
        }

        config.setDarkMode(Style.isDarkMode());
        config.setSnapping(Style.isSnapping());

        String json = new Gson().toJson(config);
        writer.write(json);
        writer.close();
    }

    /**
     * Creates the .minecraft/BananaCat directory if it cannot be found
     */

    private static void createDir() {
        File file = new File(OSHelper.getBananaCatDirectory());
        if (!file.exists()) {
            try {
                Files.createDirectory(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        createFile();
    }

    /**
     * Creates the .minecraft/BananaCat/config.json file if it cannot be found
     */

    private static void createFile() {
        File file = new File(OSHelper.getBananaCatDirectory() + "config.json");
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checks if the config .minecraft/BananaCat/config.json file exists
     *
     * @return Config can be found
     */

    public static boolean configExists() {
        File file = new File(OSHelper.getBananaCatDirectory() + "config.json");
        return file.exists();
    }
}