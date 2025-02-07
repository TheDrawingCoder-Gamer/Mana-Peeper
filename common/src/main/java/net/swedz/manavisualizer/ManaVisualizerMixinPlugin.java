package net.swedz.manavisualizer;

import net.swedz.manavisualizer.util.Platform;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class ManaVisualizerMixinPlugin implements IMixinConfigPlugin
{
	@Override
	public void onLoad(String mixinPackage)
	{
	}
	
	@Override
	public String getRefMapperConfig()
	{
		return null;
	}

	// You should feel sick. Wash yourself after reading this.
	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
	{
		if(mixinClassName.equals("net.swedz.manavisualizer.mixins.BotaniaEMINumericalMixin"))
		{

			try {
				Class.forName("dev.emi.emi.api.widget.Widget");
			} catch (Throwable e) {
				return false;
			}
		}
		else if(mixinClassName.equals("net.swedz.manavisualizer.mixins.BotaniaREINumericalMixin"))
		{
			try {
				Class.forName("me.shedaniel.rei.api.client.gui.widgets.Widgets");
			} catch (Throwable e) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
	{
	}
	
	@Override
	public List<String> getMixins()
	{
		return List.of();
	}
	
	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
	{
	}
	
	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
	{
	}
}
