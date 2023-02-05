package eosl.minecart_boosters_fix;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.Material;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = Entity.class, remap = false)
public class SouthEastRuleForLiquids
{
	@Inject(method = "handleWaterMovement", at = @At("HEAD"), cancellable = true)
	private void waterInject(@NotNull CallbackInfoReturnable<Boolean> cir)
	{
		// Just remove negative expanding of aabb:
		Entity self = ((Entity) ((Object) this));
		AxisAlignedBB aabb = self.boundingBox.expand(0.0, -0.4000000059604645, 0.0);
		boolean res = self.worldObj.handleMaterialAcceleration(aabb, Material.water, self);
		cir.setReturnValue(res);
	}


	@Inject(method = "handleLavaMovement", at = @At("HEAD"), cancellable = true)
	private void lavaInject(@NotNull CallbackInfoReturnable<Boolean> cir)
	{
		// Just remove negative expanding of aabb:
		Entity self = ((Entity) ((Object) this));
		AxisAlignedBB aabb = self.boundingBox.expand(0.0, -0.4000000059604645, 0.0);
		boolean res = self.worldObj.isMaterialInBB(aabb, Material.lava);
		cir.setReturnValue(res);
	}

}
