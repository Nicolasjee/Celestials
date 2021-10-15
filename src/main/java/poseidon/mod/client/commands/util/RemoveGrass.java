package poseidon.mod.client.commands.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RemoveGrass extends CommandEffect {
	
	public RemoveGrass() {
		
	}
	
	public static void remove(EntityPlayer player, int rad) {
		
		
		World worldIn = player.world;
		BlockPos pos = player.getPosition();
		double radius = (double) rad;
		
		if(!worldIn.isRemote) {
			Iterable<BlockPos> list = pos.getAllInBox(pos.north(rad).east(rad).down(2), pos.south(rad).west(rad).up(3));
			
			for(BlockPos e : list) {
			
				if(worldIn.getBlockState(e) == Blocks.TALLGRASS.getDefaultState()) worldIn.setBlockState(e, Blocks.AIR.getDefaultState());
				if(worldIn.getBlockState(e) == Blocks.RED_FLOWER.getDefaultState()) worldIn.setBlockState(e, Blocks.AIR.getDefaultState());
				if(worldIn.getBlockState(e) == Blocks.YELLOW_FLOWER.getDefaultState()) worldIn.setBlockState(e, Blocks.AIR.getDefaultState());
				
			}
		}
		
	}
	
}
