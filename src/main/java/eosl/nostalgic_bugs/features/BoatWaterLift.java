package eosl.nostalgic_bugs.features;

import eosl.nostalgic_bugs.NostalgicBugsMod;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(value = EntityBoat.class, remap = false)
public class BoatWaterLift
{
	@ModifyConstant(method = "onUpdate", constant = @Constant(doubleValue = 1.0, ordinal = 1))
	private double dConditionDestroyer(double d)
	{
		if (NostalgicBugsMod.g_settings.m_boatWaterLift)
			return Double.MAX_VALUE;
		else
			return d;
	}
}
