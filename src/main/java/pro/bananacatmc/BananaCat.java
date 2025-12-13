/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package pro.bananacatmc;

import pro.bananacatmc.config.ConfigLoader;
import pro.bananacatmc.config.ConfigSaver;
import pro.bananacatmc.feature.mod.ModManager;
import pro.bananacatmc.feature.option.OptionManager;
import pro.bananacatmc.feature.setting.SettingManager;
import pro.bananacatmc.gui.hudeditor.HudEditor;
import pro.bananacatmc.helpers.CpsHelper;
import pro.bananacatmc.helpers.MessageHelper;
import pro.bananacatmc.helpers.font.FontHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;

import java.io.FileNotFoundException;
import java.io.IOException;

@Mod(
        modid = BananaCat.modID,
        name = BananaCat.modName,
        version = BananaCat.modVersion,
        acceptedMinecraftVersions = "[1.8.9]"
)
public class BananaCat {

    @Mod.Instance()
    public static BananaCat INSTANCE;

    public static final String modID = "bananacatmc";
    public static final String modName = "BananaCat";
    public static final String modVersion = "1.0.1 [1.8.9]";

    public Minecraft mc = Minecraft.getMinecraft();

    public ModManager modManager;
    public SettingManager settingManager;
    public HudEditor hudEditor;
    public OptionManager optionManager;
    public FontHelper fontHelper;
    public CpsHelper cpsHelper;
    public MessageHelper messageHelper;

    /**
     * Initializes the client
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        Display.setTitle(BananaCat.modName + " Client " + BananaCat.modVersion);
        registerEvents(
                cpsHelper = new CpsHelper(),
                settingManager = new SettingManager(),
                modManager = new ModManager(),
                optionManager = new OptionManager(),
                hudEditor = new HudEditor(),
                fontHelper = new FontHelper(),
                messageHelper = new MessageHelper()
        );

        try {
            if (!ConfigSaver.configExists()) {
                ConfigSaver.saveConfig();
            }
            ConfigLoader.loadConfig();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        fontHelper.init();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ConfigSaver.saveConfig();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }));
    }

    private void registerEvents(Object... events) {
        for (Object event : events) {
            MinecraftForge.EVENT_BUS.register(event);
        }
    }
}
