package eosl.minecart_boosters_fix;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(value = EntityLiving.class, remap = false)
public class LadderGaps
{
	/**
	 * @author Eosl
	 * @reason For beta 1.2 ladder gaps bug.
	 */
	@Overwrite(remap = false)
	public boolean isOnLadder()
	{
		EntityLiving self = ((EntityLiving) ((Object) this));
		int i = MathHelper.floor_double(self.posX);
		int j = MathHelper.floor_double(self.boundingBox.minY);
		int k = MathHelper.floor_double(self.posZ);

		int id1 = self.worldObj.getBlockId(i, j, k);
		int id2 = self.worldObj.getBlockId(i, j + 1, k);

		return id1 == Block.ladderOak.blockID || id2 == Block.ladderOak.blockID;
	}
}
