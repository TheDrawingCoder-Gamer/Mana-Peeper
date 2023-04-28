package net.swedz.manavisualizer.mixins;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.swedz.manavisualizer.ManaNumberFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.mana.ManaItem;
import vazkii.botania.common.item.ManaTabletItem;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;

@Mixin({
		Item.class,
		ManaTabletItem.class,
		BaubleItem.class
})
public class BotaniaItemNumericalMixin
{
	@Inject(at = @At("HEAD"), method = "appendTooltip")
	private void appendTooltip(ItemStack stack, World world, List<Text> lines, TooltipContext context, CallbackInfo callback)
	{
		final ManaItem manaItem = XplatAbstractions.INSTANCE.findManaItem(stack);
		if(manaItem != null && !ManaTabletItem.isStackCreative(stack))
		{
			lines.add(ManaNumberFormatting.capacity(manaItem.getMana(), manaItem.getMaxMana()).formatted(Formatting.AQUA));
		}
	}
}
