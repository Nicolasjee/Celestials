package poseidon.mod.objects.block.summoner;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;

public class SummonerHelperKnowledge {

	public static BlockPos[] getStone(World world, BlockPos pos) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double yg = y - 2.0D;
		BlockPos[] stones = {new BlockPos(x + 1.0D, yg, z), new BlockPos(x + 1.0D, yg, z + 1.0D), new BlockPos(x + 1.0D, yg, z - 1.0D), new BlockPos(x, yg, z + 1.0D), new BlockPos(x, yg, z - 1.0D),
							 new BlockPos(x - 1.0D, yg, z), new BlockPos(x - 1.0D, yg, z + 1.0D), new BlockPos(x - 1.0D, yg, z - 1.0D)};
		return stones;
	}
	
	public static BlockPos[] getGlowstone(World world, BlockPos pos) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double yg = y - 2.0D;
		
		BlockPos[] glowes = {new BlockPos(x + 1.0D, yg, z + 2.0D), new BlockPos(x + 1.0D, yg, z - 2.0D), new BlockPos(x + 2.0D, yg, z + 1.0D), new BlockPos(x + 2.0D, yg, z - 1.0D),
							 new BlockPos(x - 2.0D, yg, z + 1.0D), new BlockPos(x - 2.0D, yg, z - 1.0D), new BlockPos(x - 1.0D, yg, z + 2.0D), new BlockPos(x - 1.0D, yg, z - 2.0D)};
		
		return glowes;
	}
	
	public static BlockPos[] getObb(World world, BlockPos pos) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double yg = y - 2.0D;
		
		BlockPos[] obb = {new BlockPos(x + 2.0D, yg, z), new BlockPos(x - 2.0D, yg, z), new BlockPos(x, yg, z + 2.0D), new BlockPos(x, yg, z - 2.0D),
						  new BlockPos(x + 1.0D, yg, z + 3.0D),new BlockPos(x + 2.0D, yg, z + 3.0D), new BlockPos(x - 1.0D, yg, z + 3.0D),new BlockPos(x - 2.0D, yg, z + 3.0D),
						  new BlockPos(x + 3.0D, yg, z + 1.0D),new BlockPos(x + 3.0D, yg, z + 2.0D), new BlockPos(x - 3.0D, yg, z + 1.0D),new BlockPos(x - 3.0D, yg, z + 2.0D),
						  new BlockPos(x + 3.0D, yg, z - 1.0D),new BlockPos(x + 3.0D, yg, z - 2.0D), new BlockPos(x - 3.0D, yg, z - 1.0D),new BlockPos(x - 3.0D, yg, z - 2.0D),
						  new BlockPos(x + 2.0D, yg, z - 3.0D),new BlockPos(x + 1.0D, yg, z - 3.0D), new BlockPos(x - 1.0D, yg, z - 3.0D),new BlockPos(x - 2.0D, yg, z - 3.0D),

		};
		
		return obb;
	}
	
	public static BlockPos[] getCotta(World world, BlockPos pos) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double y1 = y - 1.0D;
		
		BlockPos[] cot = {new BlockPos(x + 2.0D, y1, z + 2.0D), new BlockPos(x + 2.0D, y1, z - 2.0D), new BlockPos(x - 2.0D, y1, z - 2.0D), new BlockPos(x - 2.0D, y1, z + 2.0D),
						  new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D)};
		
		return cot;
	}
	
	public static BlockPos getBook(World world, BlockPos pos) {
		return new BlockPos(pos.getX(), pos.getY() - 1.0D, pos.getZ());
	}
	
	public static BlockPos[] getNether(World world, BlockPos pos) {
		double x = pos.getX(); double y1 = pos.getY(); double z = pos.getZ();
		double y = y1 + 1.0D;
		return new BlockPos[] {new BlockPos(x + 2.0D, y, z + 2.0D), new BlockPos(x + 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y, z - 2.0D), new BlockPos(x - 2.0D, y, z + 2.0D)};
	}
	
	public static int getMistakes(BlockPos[] bricks, BlockPos[] glow, BlockPos[] ob, BlockPos[] ter, BlockPos[] neth, World world) {
		int mistakes = 0;
		
		for(int i = 0; i < bricks.length; i++) {
			BlockPos pos = bricks[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.STONEBRICK.getDefaultState())) {
				mistakes++;
				System.out.println("mistake s");
			}
		}
		
		for(int i = 0; i < ob.length; i++) {
			BlockPos pos = ob[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.OBSIDIAN.getDefaultState())) {
				System.out.println("mistake o");
				mistakes++;
			}
		}
		
		for(int i = 0; i < glow.length; i++) {
			BlockPos pos = glow[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.GLOWSTONE.getDefaultState())) {
				mistakes++;
				System.out.println("mistake g");
			}
		}
		
		for(int i = 0; i < ter.length; i++) {
			BlockPos pos = ter[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.STONE.getDefaultState())) {
				mistakes++;
				System.out.println("mistake t:" + ter[i]);
			}
		}
		
		for(int i = 0; i < neth.length; i++) {
			BlockPos pos = neth[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == BlockInit.NETHER_STONE.getDefaultState())) {
				mistakes++;
				System.out.println("mistake n");
			}
		}
		
		
		return mistakes;
	}

	public static void execute(World world, BlockPos pos, EntityPlayer player, BlockPos[] g, ItemStack held) {
		world.setBlockState(pos, BlockInit.PARTICLE_HELPER2.getDefaultState());		
	}
	

	


	

}
