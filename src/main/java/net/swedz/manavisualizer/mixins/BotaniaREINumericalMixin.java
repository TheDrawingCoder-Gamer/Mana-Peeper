package net.swedz.manavisualizer.mixins;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.common.display.Display;
import net.minecraft.ChatFormatting;
import net.swedz.manavisualizer.ManaNumberFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.fabric.integration.rei.BotaniaRecipeDisplay;
import vazkii.botania.fabric.integration.rei.ManaPoolREICategory;
import vazkii.botania.fabric.integration.rei.RunicAltarREICategory;
import vazkii.botania.fabric.integration.rei.RunicAltarREIDisplay;
import vazkii.botania.fabric.integration.rei.TerrestrialAgglomerationREICategory;
import vazkii.botania.fabric.integration.rei.TerrestrialAgglomerationREIDisplay;

import java.util.List;

@Mixin({
		ManaPoolREICategory.class,
		RunicAltarREICategory.class,
		TerrestrialAgglomerationREICategory.class
})
public class BotaniaREINumericalMixin
{
	@Inject(at = @At("RETURN"), method = "setupDisplay(Lme/shedaniel/rei/api/common/display/Display;Lme/shedaniel/math/Rectangle;)Ljava/util/List;", cancellable = true, remap = false)
	private void setupDisplay(Display display, Rectangle bounds, CallbackInfoReturnable<List> callback)
	{
		List widgets = callback.getReturnValue();
		
		BotaniaRecipeDisplay botaniaDisplay = (BotaniaRecipeDisplay) display;
		
		// This isn't exactly the cleanest thing... lol
		int centerXOffset = -8;
		int centerYOffset = -14;
		int xOffset = -43;
		int yOffset = 37;
		if(botaniaDisplay instanceof RunicAltarREIDisplay)
		{
			centerXOffset = -8;
			centerYOffset = -6;
			xOffset = -43;
			yOffset = 52;
		}
		else if(botaniaDisplay instanceof TerrestrialAgglomerationREIDisplay)
		{
			centerXOffset = -8;
			centerYOffset = -12;
			xOffset = -43;
			yOffset = 52;
		}
		Point center = new Point(bounds.getCenterX() + centerXOffset, bounds.getCenterY() + centerYOffset);
		int x = center.x + xOffset;
		int y = center.y + yOffset;
		
		widgets.add(Widgets.createTooltip(
				new Rectangle(x, y, 101, 4),
				ManaNumberFormatting.amount(botaniaDisplay.getManaCost())
						.withStyle(ChatFormatting.AQUA),
				ManaNumberFormatting.percentage(botaniaDisplay.getManaCost(), ManaPoolBlockEntity.MAX_MANA)
						.withStyle(ChatFormatting.BLUE)));
		
		callback.setReturnValue(widgets);
	}
}
