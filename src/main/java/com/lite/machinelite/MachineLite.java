package com.lite.machinelite;

import com.lite.machinelite.command.Command;
import com.lite.machinelite.command.CommandManager;
import com.lite.machinelite.config.Profile;
import com.lite.machinelite.event.EventManager;
import com.lite.machinelite.module.ModuleManager;
import com.lite.machinelite.utilities.IMC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

@Mod(modid = MachineLite.CLIENT_ID, name = MachineLite.CLIENT_NAME, version = MachineLite.CLIENT_VERSION)
public class MachineLite implements IMC {
    public final static String CLIENT_ID = "lite";
    public final static String CLIENT_NAME = "MachineLite";
    public final static String CLIENT_VERSION = "v1.6";
    private boolean helpNotifier;

    private static EventManager eventManager;
    private static ModuleManager moduleManager;
    private static CommandManager commandManager;
    private static Profile profile;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws Exception {
        MinecraftForge.EVENT_BUS.register(this);
        profile = new Profile();
        (eventManager = new EventManager()).initialize();
        (moduleManager = new ModuleManager()).initialize();
        (commandManager = new CommandManager()).initialize();
        profile.loadFile();
        helpNotifier = true;
    }

    @SubscribeEvent
    public void keyEvent(InputEvent.KeyInputEvent e) {
        if (mc.currentScreen == null) {
            try {
                if (Keyboard.isCreated()) {
                    if (Keyboard.getEventKeyState()) {
                        int k = Keyboard.getEventKey();
                        if (k != 0) {
                            moduleManager.getModules().forEach(module -> {
                                if (k == module.getKeyCode()) {
                                    module.toggle();
                                }
                            });
                        }
                    }
                }
            } catch (Exception var1) {
                var1.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void onWorldEvent(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayerSP && helpNotifier) {
            Command command = commandManager.getCommand("help");
            command.fire(null);
            helpNotifier = false;
        }
    }

    @SubscribeEvent
    public void chatEvent(ClientChatEvent event) {
        String message = event.getMessage();

        if (message.startsWith(".")) {
            String[] commandBits = message.substring(".".length()).split(" ");
            String commandName = commandBits[0];
            Command command = commandManager.getCommand(commandName);
            if (command != null) {
                if (commandBits.length > 1) {
                    String[] commandArguments = Arrays.copyOfRange(commandBits, 1, commandBits.length);
                    command.fire(commandArguments);
                } else {
                    command.fire(null);
                }
            } else {
                MachineLite.WriteChat("\247cInvalid Command");
            }

            event.setCanceled(true);
        }
    }

    public static void WriteChat(Object message) {
        mc.ingameGUI.addChatMessage(ChatType.SYSTEM, new TextComponentString("\2477[\2475Machine \247fLite\2477] \247f" + message.toString()));
    }

    public static Profile getProfile() {
        return profile;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }
}
