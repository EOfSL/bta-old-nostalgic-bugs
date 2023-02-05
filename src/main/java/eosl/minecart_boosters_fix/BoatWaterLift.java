package eosl.minecart_boosters_fix;

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
		return Double.MAX_VALUE;
	}
}
