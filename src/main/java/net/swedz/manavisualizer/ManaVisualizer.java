package net.swedz.manavisualizer;

import net.fabricmc.api.ModInitializer;

public class ManaVisualizer implements ModInitializer
{
	@Override
	public void onInitialize()
	{
		instance = this;
	}
	
	private static ManaVisualizer instance;
	
	public static ManaVisualizer getInstance()
	{
		return instance;
	}
}
