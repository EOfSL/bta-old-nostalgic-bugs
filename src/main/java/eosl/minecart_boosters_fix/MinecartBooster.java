package eosl.minecart_boosters_fix;

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
		return 0;
	}
}
