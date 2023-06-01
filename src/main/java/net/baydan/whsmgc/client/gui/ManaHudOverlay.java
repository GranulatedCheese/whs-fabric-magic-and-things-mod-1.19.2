package net.baydan.whsmgc.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.baydan.whsmgc.MagicAndThings;
import net.baydan.whsmgc.util.IEntityDataSaver;
import net.baydan.whsmgc.util.ManaData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ManaHudOverlay implements HudRenderCallback {
    private static final Identifier MANA_BAR_BORDER = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_border.png");
    private static final Identifier MANA_BAR_BACKGROUND = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_background.png");
    private static final Identifier MANA_BAR_OVERLAY = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_overlay.png");
    private static final Identifier MANA_BAR_FILL = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_fill.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();
        IEntityDataSaver dataPlayer = ((IEntityDataSaver) MinecraftClient.getInstance().player);

        if(client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }

        int manaPercent = ManaData.calculateManaPercent(
                dataPlayer,
                ManaData.getManaAmount(dataPlayer),
                ManaData.getManaLevel(dataPlayer)
        );


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F,1.0F);
        RenderSystem.setShaderTexture(0,MANA_BAR_FILL);
        for(int i = 0; i < 10; i++) {
            if(manaPercent / 10 > i) {
                DrawableHelper.drawTexture(matrixStack, x - 94 + (i * (10 - 8)), y - 54, 0, 0, 16, 16, 16, 16);
            } else {
                break;
            }
        }

    }
}
