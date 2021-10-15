package poseidon.mod.objects.block.customsponge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.util.Utilities;

public class ModSponge extends BlockBase {

	public ModSponge(String name) {
		super(name, Material.SPONGE, 0.0F, 0.5F, 0.5F, 0);
		setSoundType(SoundType.GROUND);
	}
	
	
	//ABSORB
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		if(!worldIn.isRemote) {
			
			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
			
			List<BlockPos> bubble = bubble(worldIn, pos);
			int c = 0;
			
			for(int i = 0; i < bubble.size(); i++) {
				

				if(worldIn.getBlockState(bubble.get(i)) == Blocks.WATER.getDefaultState()) {
					worldIn.setBlockState(bubble.get(i), BlockInit.MOD_AIR.getDefaultState());
					c++;
				}
				IBlockState cs = worldIn.getBlockState(bubble.get(i));
				if(cs.getBlock() instanceof BlockLiquid && cs.getMaterial() == Material.WATER) {
					worldIn.setBlockState(bubble.get(i), BlockInit.MOD_AIR.getDefaultState());
					c++;
				}
						
				if(c > 0) worldIn.setBlockState(pos, BlockInit.WET_MODSPONGE.getDefaultState());
					
				Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.BLOCK_GRASS_BREAK, 1.0F, 0.1F);
			
			}
			
		}
	}
		
	
	private static List<BlockPos> bubble(World world, BlockPos pos) {
		return Utilities.getSpongeBlocks(pos);
	}
	
	/* WHAT IS THIS DOING HERE??
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.isRemote) {
			double x = pos.getX(); double y = pos.getY(); double z = pos.getZ(); List<BlockPos> list = new ArrayList<BlockPos>();
			
			for(int k = -3; k < 4; k++) {
				for(int i = -3; i < 3; i++) {	
					for(int j = -3; j < 3; j++) {	
						list.add(new BlockPos(x + (double) i, y + (double) k, z + (double) j));
						if(worldIn.getBlockState(new BlockPos(x + (double) i, y + (double) k, z + (double) j)) == BlockInit.MOD_AIR.getDefaultState()) {
							worldIn.setBlockState(new BlockPos(x + (double) i, y + (double) k, z + (double) j), Blocks.WATER.getDefaultState());
						}
						if(worldIn.getBlockState(new BlockPos(x + (double) i, y + (double) k, z + (double) j)) == BlockInit.MOD_AIR.getDefaultState()) {
							worldIn.setBlockState(new BlockPos(x + (double) i, y + (double) k, z + (double) j), Blocks.FLOWING_WATER.getDefaultState());
						}
					}
				}
			}
		}
		
	}
	*/
	
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.DYE;
	}
	
}
