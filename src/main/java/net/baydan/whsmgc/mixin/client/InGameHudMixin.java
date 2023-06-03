package net.baydan.whsmgc.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.baydan.whsmgc.MagicAndThings;
import net.baydan.whsmgc.util.IEntityDataSaver;
import net.baydan.whsmgc.util.ManaData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    private static final Identifier MANA_BAR_BORDER = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_border.png");
    private static final Identifier MANA_BAR_BACKGROUND = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_background.png");
    private static final Identifier MANA_BAR_OVERLAY = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_overlay.png");
    private static final Identifier MANA_BAR_FILL = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_fill.png");

    private static final Identifier MANA_BAR_EMPTY = new Identifier(MagicAndThings.MOD_ID,
            "textures/gui/mana/mana_bar_empty.png");

    @Shadow @Final private MinecraftClient client;

    @Shadow private int scaledHeight;
    @Shadow private int scaledWidth;

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Inject(method = "render", at = @At("TAIL"))
    public void render(MatrixStack ms, float tickDelta, CallbackInfo ci) {
        PlayerEntity player = this.getCameraPlayer();
        if(this.client.player != null && this.client.player.isAlive() && player != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            this.scaledWidth = width / 2;
            this.scaledHeight = height;

            renderManaHud(ms, player);
        }
    }

    private void renderManaHud(MatrixStack ms, PlayerEntity player) {
        InGameHud hud = (InGameHud) (Object) this;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, MANA_BAR_EMPTY);
        for(int i = 0; i < 100; i++) {
            hud.drawTexture(ms, scaledWidth - 104 + (i * (10 - 8)), scaledHeight - 36, 0, 0, 12, 12, 16, 14);
            this.client.getProfiler().pop();
        }

        RenderSystem.setShaderTexture(0, MANA_BAR_FILL);
        for(int i = 0; i < 100; i++) {
            if(ManaData.calculateManaPercent(((IEntityDataSaver) player)) > i) {
                hud.drawTexture(ms, scaledWidth - 104 + (i * (10 - 8)), scaledHeight - 36, 0, 0, 12, 12, 16, 14);
                this.client.getProfiler().pop();
            } else {
                break;
            }
        }
    }
}
