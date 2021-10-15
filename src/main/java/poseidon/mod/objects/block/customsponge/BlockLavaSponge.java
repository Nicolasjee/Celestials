package poseidon.mod.objects.block.customsponge;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.util.Utilities;

public class BlockLavaSponge extends BlockBase {

	public BlockLavaSponge(String name) {
		super(name, Material.SPONGE, 0.0F, 0.5F, 0.5F, 0);
		setSoundType(SoundType.GROUND);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		if(!worldIn.isRemote) {
			
			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
			
			List<BlockPos> list = bubble(worldIn, pos);
			int c = 0;
			
			for(int k = 0; k < list.size(); k++) {
				
				if(worldIn.getBlockState(list.get(k)) == Blocks.LAVA.getDefaultState()) {
					worldIn.setBlockState(list.get(k), BlockInit.MOD_AIR.getDefaultState());
					c++;
				}
				IBlockState cs = worldIn.getBlockState(list.get(k));
				if(cs.getBlock() instanceof BlockLiquid && cs.getMaterial() == Material.LAVA) {
					worldIn.setBlockState(list.get(k), BlockInit.MOD_AIR.getDefaultState());						
					c++;
				}
						
				if(c > 0) worldIn.setBlockState(pos, BlockInit.BURNINGLAVASPONGE.getDefaultState());
					
					
				Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.BLOCK_GRASS_BREAK, 1.0F, 0.10F);
				
			
			}
			
		}
	}
	
	public static List<BlockPos> bubble(World world, BlockPos pos) {
		return Utilities.getSpongeBlocks(pos);
	}
	
	
}


