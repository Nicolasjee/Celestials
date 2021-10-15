package poseidon.mod.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Helper {
	
	public static Block getBlock(IBlockAccess world, int i, int j, int k) {
		return world.getBlockState(new BlockPos(i, j, k)).getBlock();
	}
	
	public static void IExtendedEntityProperties(EntityPlayer player) {
		
		player.getEntityData();
		
	}
	
	
}
