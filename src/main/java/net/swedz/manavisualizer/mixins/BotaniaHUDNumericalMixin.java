package net.swedz.manavisualizer.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.swedz.manavisualizer.ManaNumberFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.client.gui.HUDHandler;

import java.awt.Color;

@Mixin(HUDHandler.class)
public class BotaniaHUDNumericalMixin
{
	@Inject(at = @At(value = "RETURN", shift = At.Shift.BEFORE), method = "drawSimpleManaHUD")
	private static void drawSimpleManaHUD(DrawContext ctx, int color, int mana, int maxMana, String name, CallbackInfo callback)
	{
		final MinecraftClient mc = MinecraftClient.getInstance();
		
		Text manaDisplayText = ManaNumberFormatting.capacity(mana, maxMana).formatted(Formatting.AQUA);
		
		final int x = mc.getWindow().getScaledWidth() / 2 - mc.textRenderer.getWidth(manaDisplayText) / 2;
		final int y = mc.getWindow().getScaledHeight() / 2 - 10 - mc.textRenderer.fontHeight;

		ctx.drawText(mc.textRenderer, manaDisplayText, x, y, Color.WHITE.getRGB(), true);
	}
}
