package eosl.nostalgic_bugs.features;

import eosl.nostalgic_bugs.NostalgicBugsMod;
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
		if (!NostalgicBugsMod.g_settings.m_ladderGaps)
			return;

		EntityLiving self = ((EntityLiving) ((Object) this));
		int x = MathHelper.floor_double(self.posX);
		int yd = MathHelper.floor_double(self.boundingBox.minY);
		int yu = MathHelper.floor_double(self.boundingBox.maxY);
		int z = MathHelper.floor_double(self.posZ);

		int id1 = self.worldObj.getBlockId(x, yd, z);
		int id2 = self.worldObj.getBlockId(x, yu, z);

		cir.setReturnValue(id1 == Block.ladderOak.blockID || id2 == Block.ladderOak.blockID);
	}
}
