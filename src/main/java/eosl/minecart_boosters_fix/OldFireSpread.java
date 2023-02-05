package eosl.minecart_boosters_fix;

import net.minecraft.src.Block;
import net.minecraft.src.BlockFire;
import net.minecraft.src.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


/*
 * Thanks to Amb0s for the code: https://github.com/Amb0s
 */
@Mixin(value = BlockFire.class, remap = false)
public class OldFireSpread
{
	@ModifyConstant(method = "tickRate", constant = @Constant(intValue = 40))
	private int changeTickRate(int a)
	{
		// It is 10 in beta before 1.6:
		return 10;
	}


	@Redirect(method = "tryToCatchBlockOnFire", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/src/World;setBlockAndMetadataWithNotify(IIIII)Z"))
	private boolean infiniteSpreading(@NotNull World world, int x, int y, int z, int id, int meta)
	{
		// Make new fire blocks spawn with zero old:
		world.setBlockAndMetadataWithNotify(x, y, z, Block.fire.blockID, 0);
		return false;
	}


	@Redirect(method = "tryToCatchBlockOnFire", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/src/BlockFire;setBurnResult(Lnet/minecraft/src/World;III)V"))
	private void cancelSetBurnResult(BlockFire instance, World world, int x, int y, int z)
	{
		// Remove a chance to burn a block before the fire block old will reach 15.
	}
}
