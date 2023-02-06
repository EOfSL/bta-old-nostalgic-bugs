package eosl.nostalgic_bugs.features;

import eosl.nostalgic_bugs.NostalgicBugsMod;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(value = EntityMinecart.class, remap = false)
public class MinecartBooster
{
	@ModifyVariable(
			method = "applyEntityCollision",
			at = @At(value = "STORE"),
			ordinal = 6
	)
	private double d6ConditionDestroyer(double d6)
	{
		if (NostalgicBugsMod.g_settings.m_minecartBoosters)
			return 0;
		else
			return d6;
	}
}
