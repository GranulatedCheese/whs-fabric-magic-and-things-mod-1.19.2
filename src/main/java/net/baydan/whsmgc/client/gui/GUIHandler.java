package net.baydan.whsmgc.client.gui;

import net.baydan.whsmgc.MagicAndThings;

import net.baydan.whsmgc.client.core.helper.RenderHelper;
import net.baydan.whsmgc.client.lib.ResourcesLib;
import net.baydan.whsmgc.config.MagicAndThingsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.profiler.Profiler;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;


public class GUIHandler {
    private GUIHandler(){}

    public static final Identifier manaBar = new Identifier(MagicAndThings.MOD_ID, "textures/gui/mana_hud.png");

    public static void onDrawScreenPost(MatrixStack ms, float partialTicks) {
        MinecraftClient player = MinecraftClient.getInstance();
        if(player.options.hudHidden) {
            return;
        }
        Profiler profiler = player.getProfiler();
        ItemStack main = player.player.getMainHandStack();
        ItemStack offhand = player.player.getOffHandStack();

        profiler.push("whsmgc-hud");
        profiler.push("manaBar");

        profiler.pop();
        profiler.pop();

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
    }

    public static void renderManaInvBar(MatrixStack ms, int manaAmount, int maxMana) {
        MinecraftClient player = MinecraftClient.getInstance();
        int width = 182;
        int x = player.getWindow().getScaledWidth() / 2 - width / 2;
        int y = player.getWindow().getScaledHeight() - MagicAndThingsConfig.client().manaBarHeight();

        if(maxMana == 0) {
            width = 0;
        } else {
            width *= (double) manaAmount / (double) maxMana;
        }

        if(width == 0) {
            if(manaAmount > 0) {
                width = 1;
            } else {
                return;
            }
        }

        int color = MathHelper.hsvToRgb(0.55F, (float) Math.min(1F, Math.sin(Util.getMeasuringTimeMs() / 200D) * 0.5 + 1F), 1F);
        int r = (color >> 16 & 0xFF);
        int g = (color >> 8 & 0xFF);
        int b = color & 0xFF;
        RenderSystem.setShaderColor(r / 255F, g / 255F, b / 255F, 1 - (r /255F));
        RenderSystem.setShaderTexture(0, manaBar);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.drawTexturedModalRect(ms, x, y, 0, 251, width, 5);
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1,1,1,1);
    }

    public static void drawSimpleManaHud(MatrixStack ms, int color, int manaAmount, int maxMana, String name) {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        MinecraftClient player = MinecraftClient.getInstance();
        int x = player.getWindow().getScaledWidth() / 2 - player.textRenderer.getWidth(name) / 2;
        int y = player.getWindow().getScaledHeight() / 2 + 10;

        player.textRenderer.drawWithShadow(ms, name, x, y, color);

        x = player.getWindow().getScaledWidth() / 2 - 51;
        y += 10;

        renderManaBar(ms, x, y, color, 1F, manaAmount, maxMana);

        RenderSystem.disableBlend();
    }

    public static void drawComplexManaHud(int color, MatrixStack ms, int manaAmount, int maxMana, String name,
                                          ItemStack bindDisplay, boolean properlyBound) {
        drawSimpleManaHud(ms, color, manaAmount, maxMana, name);

        MinecraftClient player = MinecraftClient.getInstance();

        int x = player.getWindow().getScaledWidth() / 2 + 55;
        int y = player.getWindow().getScaledHeight() / 2 + 12;

        player.getItemRenderer().renderGuiItemIcon(bindDisplay, x, y);

        RenderSystem.disableDepthTest();;
        player.getProfiler().push("manaAmount");

        //player.translate(0, 0, mc.getItemRenderer().blitOffset + 50 + 200 + 1);
        if(properlyBound) {
            player.textRenderer.drawWithShadow(ms, "✔", x + 10, y + 9, 0x004C00);
            player.textRenderer.drawWithShadow(ms, "✔", x + 10, y + 8, 0x0BD20D);
        } else {
            player.textRenderer.drawWithShadow(ms, "✔", x + 10, y + 9, 0x4C0000);
            player.textRenderer.drawWithShadow(ms, "✔", x + 10, y + 8, 0xD2080D);
        }

        player.getProfiler().pop();
        RenderSystem.enableDepthTest();
    }

    public static void renderManaBar(MatrixStack ms, int x, int y, int color, float alpha, int manaAmount, int maxMana) {
        MinecraftClient player = MinecraftClient.getInstance();

        RenderSystem.setShaderColor(1F, 1F, 1F, alpha);
        RenderSystem.setShaderTexture(0, manaBar);
        RenderHelper.drawTexturedModalRect(ms, x, y, 0, 0, 102, 5);

        int manaPercentage =  Math.max(0, (int) ((double) manaAmount / (double) maxMana * 100));

        if(manaPercentage == 0 && manaAmount > 0) {
            manaPercentage = 1;
        }

        RenderHelper.drawTexturedModalRect(ms, x + 1, y + 1, 0, 5, 100, 3);

        float red = (color >> 16 & 0xFF) / 255F;
        float green = (color >> 8 & 0xFF) / 255F;
        float blue = (color & 0xFF) / 255F;
        RenderSystem.setShaderColor(red, green, blue, alpha);
        RenderHelper.drawTexturedModalRect(ms, x + 1, y + 1, 0, 5, Math.min(100, manaPercentage), 3);
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
}
