package net.baydan.whsmgc.event;

import net.baydan.whsmgc.networking.ModPackets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_WHSMGC = "key.category.whsmgc.mgc";
    public static final String KEY_REMOVE_MANA = "key.whsmgc.remove_mana";
    public static final String KEY_REMOVE_MANA_LEVEL = "key.whsmgc.remove_mana_level";
    public static final String KEY_ADD_MANA_LEVEL = "key.whsmgc.add_mana_level";

    public static KeyBinding removeManaKey;
    public static KeyBinding addManaLevelKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(removeManaKey.wasPressed()) {
                // This will hapen when key is pressed
                ClientPlayNetworking.send(ModPackets.MANA_AMOUNT_ID, PacketByteBufs.create());
            }

            if(addManaLevelKey.wasPressed()) {
                ClientPlayNetworking.send(ModPackets.MANA_LEVEL_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        removeManaKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_REMOVE_MANA,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_WHSMGC
        ));

        addManaLevelKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_ADD_MANA_LEVEL,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                KEY_CATEGORY_WHSMGC
        ));

        registerKeyInputs();
    }



}
