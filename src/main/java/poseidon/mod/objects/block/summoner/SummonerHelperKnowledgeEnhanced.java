package poseidon.mod.objects.block.summoner;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.summoner.extended.TileEntitySummoner;
import poseidon.mod.util.ParticleUtil;

public class SummonerHelperKnowledgeEnhanced {

	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	
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
		
		BlockPos[] obb = {new BlockPos(x + 2.0D, yg, z), new BlockPos(x - 2.0D, yg, z), new BlockPos(x, yg, z + 2.0D), 
				new BlockPos(x, yg, z - 2.0D), new BlockPos(x + 2.0D, yg, z + 3.0D), new BlockPos(x + 1.0D, yg, z + 3.0D),
				new BlockPos(x + 3.0D, yg, z + 1.0D), new BlockPos(x + 3.0D, yg, z + 2.0D), new BlockPos(x - 1.0D, yg, z + 3.0D),
				new BlockPos(x - 2.0D, yg, z + 3.0D), new BlockPos(x - 3.0D, yg, z + 2.0D), new BlockPos(x - 3.0D, yg, z + 1.0D),
				new BlockPos(x - 3.0D, yg, z - 1.0D), new BlockPos(x - 3.0D, yg, z - 2.0D), new BlockPos(x - 2.0D, yg, z - 3.0D),
				new BlockPos(x - 1.0D, yg, z - 3.0D), new BlockPos(x + 1.0D, yg, z - 3.0D), new BlockPos(x + 2.0D, yg, z - 3.0D),
				new BlockPos(x + 3.0D, yg, z - 2.0D), new BlockPos(x + 3.0D, yg, z - 1.0D)};
		
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
	
	public static BlockPos[] getPillars(World world, BlockPos pos) {
		double x = pos.getX(); double y1 = pos.getY(); double z = pos.getZ();
		double ya = y1 - 1.0D; double yb = y1 + 1.0D;
		return new BlockPos[] {new BlockPos(x, ya, z + 4.0D), new BlockPos(x, yb, z + 4.0D), new BlockPos(x, y1, z + 4.0D),
							   new BlockPos(x + 4.0D, ya, z), new BlockPos(x + 4.0D, yb, z), new BlockPos(x + 4.0D, y1, z),
							   new BlockPos(x - 4.0D, ya, z), new BlockPos(x - 4.0D, yb, z), new BlockPos(x - 4.0D, y1, z),
							   new BlockPos(x, ya, z - 4.0D), new BlockPos(x, yb, z - 4.0D), new BlockPos(x, y1, z - 4.0D)};
	}
	
	public static BlockPos[] valueables(World world, BlockPos pos) {
		
		double x = pos.getX(); double y = pos.getY() - 2.0D; double z = pos.getZ();
		int points = 0;
		
		BlockPos[] v = new BlockPos[]{new BlockPos(x + 3.0D, y, z), new BlockPos(x, y, z + 3.0D) ,new BlockPos(x - 3.0D, y, z) ,new BlockPos(x, y, z - 3.0D)};
		

		return v;
	}
	
	public static BlockPos[] getGlass(World world, BlockPos pos) {
		double x = pos.getX(); double ya = pos.getY() + 2.0D; double z = pos.getZ();
		
		return new BlockPos[] {new BlockPos(x, ya, z + 4.0D), new BlockPos(x + 4.0D, ya, z), new BlockPos(x - 4.0D, ya, z), new BlockPos(x, ya, z - 4.0D)};
	}
	
	public static int getMistakes(BlockPos[] bricks, BlockPos[] glow, BlockPos[] ob, BlockPos[] ter, BlockPos[] neth, BlockPos[] pillars, BlockPos[] glass, World world, BlockPos pos2, BlockPos[] quartz) {
		int mistakes = 0;
		
		for(int i = 0; i < quartz.length; i++) {
			BlockPos pos = quartz[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.QUARTZ_BLOCK.getDefaultState())) {
				mistakes++;
				System.out.println("mistake t:" + quartz[i]);
			}
		}
		
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
			if(!(block.getDefaultState() == Blocks.STONEBRICK.getDefaultState())) {
				mistakes++;
				System.out.println("mistake g");
			}
		}
		
		for(int i = 0; i < ter.length; i++) {
			BlockPos pos = ter[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.QUARTZ_BLOCK.getDefaultState())) {
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
		
		for(int i = 0; i < glass.length; i++) {
			BlockPos pos = glass[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == BlockInit.NETHER_STONE.getDefaultState())) {
				mistakes++;
				System.out.println("mistake glass (netherstone)");
			}
		}
		
		for(int i = 0; i < pillars.length; i++) {
			BlockPos pos = pillars[i];
			IBlockState st = world.getBlockState(pos);
			Block block = st.getBlock();
			if(!(block.getDefaultState() == Blocks.NETHER_BRICK.getDefaultState())) {
				mistakes++;
				System.out.println("mistake pillars: " + pos);
			}
		}
		
		if(world.getTileEntity(pos2) instanceof TileEntitySummoner) {
			
			TileEntitySummoner tile = (TileEntitySummoner) world.getTileEntity(pos2);
			ItemStack stack = tile.slot(0); ItemStack up = tile.slot(1);
			ItemStack stack2 = tile.slot(2); ItemStack stack3 = tile.slot(3); ItemStack stack4 = tile.slot(4); ItemStack stack5 = tile.slot(5);
			
			//if(!(stack.getItem() == ItemInit.KNOWLEDGE_BOOK && stack2.getItem() == ItemInit.KNOWLEDGE_BOOK && stack3.getItem() == ItemInit.KNOWLEDGE_BOOK && stack4.getItem() == Items.ENDER_PEARL && up.getItem() == ItemInit.UPGRADE_MODULE && stack5.getItem() == ItemInit.KNOWLEDGE_BOOK)) mistakes++;
			
		}
		
		
		return mistakes;
	}
	
	public static void execute(World world, BlockPos pos, EntityPlayer player, BlockPos[] g, ItemStack held) {
		System.out.println("relative");
		held.shrink(1);
		if(world.getTileEntity(pos) instanceof TileEntitySummoner) {
			TileEntitySummoner tile = (TileEntitySummoner) world.getTileEntity(pos);
			tile.shrinkStack(1); tile.shrinkStack(0); tile.shrinkStack(3); tile.shrinkStack(5); tile.shrinkStack(4);
		}
		
		//getRelative(g, pos, world);
		world.setBlockState(pos.up(), BlockInit.PARTICLE_HELPER.getDefaultState());
	}
	
	public static void getRelative(BlockPos[] post, BlockPos pos, World world) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		/*
		 * 		1  4
		 *    
		 *    	2  3
		 */

		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 1);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() + 2.0D), pos, (WorldServer) world, 4);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 2);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 2.0D, pos.getY() + 1.0D, pos.getZ() - 2.0D), pos, (WorldServer) world, 3);

		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 5);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 6);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 7);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 8);
		
		//pos extra pillar to little pillars
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 1);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() + 4.0D), pos, (WorldServer) world, 4);
		
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 3);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() - 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 4);
		
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 2);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX(), pos.getY() + 2.0D, pos.getZ() - 4.0D), pos, (WorldServer) world, 3);
		
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 1);
		if(!world.isRemote) ParticleUtil.knowledge(new BlockPos(pos.getX() + 4.0D, pos.getY() + 2.0D, pos.getZ()), pos, (WorldServer) world, 2);
		
		
	}
	
	public static boolean sameCo(BlockPos pos, BlockPos pos2) {
		double x1 = pos.getX();
		double y1 = pos.getY();
		double z1 = pos.getZ();
		double x = pos2.getX();
		double y = pos2.getY();
		double z = pos.getZ();
		
		if(x1 == x && y1 == y && z1 == z) {
			return true;
		}
		return false;
	}
	
//	public static void spawnParticlePosPos(BlockPos pos, BlockPos center, World w) {
//		System.out.println("1");
//		Main.instance.proxy.generateKnowledgeP1(pos, center, w);
//	}
//	
//	public static void spawnParticlePosNeg(BlockPos pos, BlockPos center, World w) {
//		System.out.println("2");
//		Main.instance.proxy.generateKnowledgeP2(pos, center, w);
//	}
//	
//	public static void spawnParticleNegNeg(BlockPos pos, BlockPos center, World w) {
//		Main.instance.proxy.generateKnowledgeP3(pos, center, w);
//		System.out.println("3");
//	}
//	
//	public static void spawnParticleNegPos(BlockPos pos, BlockPos center, World w) {
//		Main.instance.proxy.generateKnowledgeP4(pos, center, w);
//		System.out.println("4");
//	}
	
	
}

