package net.baydan.whsmgc.client.core.helper;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.DrawableHelper;

public class RenderHelper {
    public static void drawTexturedModalRect(MatrixStack ms, int x, int y, int u, int v, int width, int height) {
        DrawableHelper.drawTexture(ms, x, y, u, v, width, height, 256, 256);
    }
}
