package net.swedz.manavisualizer.mixins;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.swedz.manavisualizer.ManaNumberFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.mana.ManaItem;
import vazkii.botania.common.item.ManaTabletItem;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.awt.*;
import java.util.List;

@Mixin({
		Item.class,
		ManaTabletItem.class,
		BaubleItem.class
})
public class BotaniaItemNumericalMixin
{
	@Inject(at = @At("HEAD"), method = "appendHoverText")
	private void appendTooltip(ItemStack stack, Level world, List<Component> lines, TooltipFlag context, CallbackInfo callback)
	{
		final ManaItem manaItem = XplatAbstractions.INSTANCE.findManaItem(stack);
		if(manaItem != null && !ManaTabletItem.isStackCreative(stack))
		{
			lines.add(ManaNumberFormatting.capacity(manaItem.getMana(), manaItem.getMaxMana()).withStyle(ChatFormatting.AQUA));
		}
	}
}
