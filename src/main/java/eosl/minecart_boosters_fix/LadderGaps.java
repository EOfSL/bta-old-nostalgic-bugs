package eosl.minecart_boosters_fix;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = EntityLiving.class, remap = false)
public class LadderGaps
{
	@Inject(method = "isOnLadder", at = @At("HEAD"), cancellable = true)
	public void increaseLadderCoverage(@NotNull CallbackInfoReturnable<Boolean> cir)
	{
		EntityLiving self = ((EntityLiving) ((Object) this));
		int i = MathHelper.floor_double(self.posX);
		int j = MathHelper.floor_double(self.boundingBox.minY);
		int k = MathHelper.floor_double(self.posZ);

		int id1 = self.worldObj.getBlockId(i, j, k);
		int id2 = self.worldObj.getBlockId(i, j + 1, k);

		cir.setReturnValue(id1 == Block.ladderOak.blockID || id2 == Block.ladderOak.blockID);
	}
}
