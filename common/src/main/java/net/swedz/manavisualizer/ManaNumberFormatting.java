package net.swedz.manavisualizer;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.text.DecimalFormat;

public final class ManaNumberFormatting
{
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");
	
	private static String numericalDisplay(int mana)
	{
		return String.format("%,d", mana);
	}
	
	private static String shortenedNumericalDisplay(int mana)
	{
		String abbreviation;
		double shortenedMana = mana;
		if(mana >= 1000000000)
		{
			abbreviation = "B";
			shortenedMana /= 1000000000;
		}
		else if(mana >= 1000000)
		{
			abbreviation = "M";
			shortenedMana /= 1000000;
		}
		else if(mana >= 1000)
		{
			abbreviation = "K";
			shortenedMana /= 1000;
		}
		else
		{
			return Integer.toString(mana);
		}
		return DECIMAL_FORMAT.format(shortenedMana) + abbreviation;
	}
	
	public static MutableComponent amount(int mana)
	{
		return Component.translatable("manavisualizer.mana_display.amount", numericalDisplay(mana));
	}
	
	private static String percentageDisplay(int mana, int maxMana)
	{
		return DECIMAL_FORMAT.format(((double) mana / maxMana) * 100);
	}
	
	public static MutableComponent percentage(int mana, int maxMana)
	{
		return Component.translatable("manavisualizer.mana_display.percentage", percentageDisplay(mana, maxMana));
	}
	
	public static MutableComponent capacity(int mana, int maxMana)
	{
		return Component.translatable(
				"manavisualizer.mana_display.capacity",
				shortenedNumericalDisplay(mana),
				shortenedNumericalDisplay(maxMana),
				percentageDisplay(mana, maxMana)
		);
	}
}
