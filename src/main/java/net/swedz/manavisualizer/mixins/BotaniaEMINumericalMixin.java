package net.swedz.manavisualizer.mixins;

import dev.emi.emi.api.widget.Widget;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.util.Formatting;
import net.swedz.manavisualizer.ManaNumberFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.client.integration.emi.ManaWidget;

import java.util.List;

@Mixin(ManaWidget.class)
public abstract class BotaniaEMINumericalMixin extends Widget
{
	@Final
	@Shadow(remap = false)
	private int mana;
	
	@Override
	public List<TooltipComponent> getTooltip(int mouseX, int mouseY)
	{
		return List.of(
				TooltipComponent.of(ManaNumberFormatting.amount(mana)
						.formatted(Formatting.AQUA).asOrderedText()),
				TooltipComponent.of(ManaNumberFormatting.percentage(mana, ManaPoolBlockEntity.MAX_MANA)
						.formatted(Formatting.BLUE).asOrderedText())
		);
	}
}
