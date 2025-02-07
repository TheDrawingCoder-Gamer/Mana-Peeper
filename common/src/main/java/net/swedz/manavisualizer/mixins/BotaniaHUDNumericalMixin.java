package net.swedz.manavisualizer.mixins;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
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
	private static void drawSimpleManaHUD(GuiGraphics ctx, int color, int mana, int maxMana, String name, CallbackInfo callback)
	{
		final Minecraft mc = Minecraft.getInstance();
		
		Component manaDisplayText = ManaNumberFormatting.capacity(mana, maxMana).withStyle(ChatFormatting.AQUA);
		
		final int x = mc.getWindow().getGuiScaledWidth() / 2 - mc.font.width(manaDisplayText) / 2;
		final int y = mc.getWindow().getGuiScaledHeight() / 2 - 10 - mc.font.lineHeight;

		ctx.drawString(mc.font, manaDisplayText, x, y, Color.WHITE.getRGB(), true);
	}
}
