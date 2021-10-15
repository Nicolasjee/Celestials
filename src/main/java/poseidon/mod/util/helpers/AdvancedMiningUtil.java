package poseidon.mod.util.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.util.ParticleUtil;

public class AdvancedMiningUtil {

	public static final PropertyEnum<BlockStone.EnumType> VARIANT = PropertyEnum.<BlockStone.EnumType>create("variant", BlockStone.EnumType.class);
	private static IBlockState state;
	private static int b = 0;
	
    public static void removeBlockStoneCall(BlockPos pos, World worldIn, ItemStack stack, EntityPlayer player, boolean hasLooting, boolean shouldSmelt, int lootingLvl, Block type, IBlockState statet) {
    	state = statet;
    	removeBlocks(pos, worldIn, stack, player, false, false, 1, type);
    }
    
   public static void removeBlocks(BlockPos pos, World worldIn, ItemStack stack, EntityPlayer player, boolean hasLooting, boolean shouldSmelt, int lootingLvl, Block type) {

	   boolean isBlockStone = false;
	   boolean isBlockSand = false;
	   if(type == Blocks.STONE) isBlockStone = true;
	   if(type == Blocks.SAND) isBlockSand = true;
	   int generalSize = 0;
	   
	   if(!worldIn.isRemote){
		   	
			List<BlockPos> coal = checkForTarget(worldIn, pos, type);
			if(coal.size() == 1) return;
			List<BlockPos> coalExpanded = new ArrayList<BlockPos>();
			
			for(int i = 0; i < coal.size(); i++) {
				coalExpanded = addPos(worldIn, coal.get(i), coalExpanded, type);
			}
			b++;
			
			for(int i = 0; i < coalExpanded.size(); i++) {
				coalExpanded = addPos(worldIn, coalExpanded.get(i), coalExpanded, type);
			}b++;
			
			for(int i = 0; i < coalExpanded.size(); i++) {
				coalExpanded = addPos(worldIn, coalExpanded.get(i), coalExpanded, type);
			}
			
			List<BlockPos> general = new ArrayList<BlockPos>();
			general.addAll(coalExpanded);
			if(!general.contains(pos)) general.add(pos);

			for(int i = 0; i < general.size(); i++) {
				
				if((isBlockStone || isBlockSand) && i == 15) break;
				
				worldIn.destroyBlock(general.get(i), destroyBlock(shouldSmelt, isBlockStone));

				if(isBlockStone) {
					getEntityStone(worldIn, general.get(i), state);
				}
				
				if(!isBlockStone && shouldSmelt) {
					getEntityItem(worldIn, general.get(i), getFortuneSize(lootingLvl), type, shouldSmelt, hasLooting);
				}
				
			}
			generalSize = general.size();
		   	int damageSize = (generalSize - (generalSize % 2)) / 2;
		   	if(isBlockStone) stack.damageItem(5, player);
		   	else stack.damageItem(damageSize, player);
		   	
	   	}

	}

	private static List<BlockPos> checkForTarget(World world, BlockPos pos, Block type) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		boolean isRedstone = isBlockRedstone(type);
		boolean isStone = isStone(world.getBlockState(pos));
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		BlockPos[] array = new BlockPos[]{new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x, y, z + 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z), 
										  new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D), 
										  new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D), 
										  new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z), 
										  new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D), 
										  new BlockPos(x, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D),};
		
		for(int i = 0; i < array.length; i++) {
			
			if(world.getBlockState(array[i]) == type.getDefaultState() && !isStone) 
				list.add(array[i]);
			
			if(isRedstone) 
				if(world.getBlockState(array[i]) == Blocks.LIT_REDSTONE_ORE.getDefaultState())
					list.add(array[i]);
			
			if(isStone && isStone(world.getBlockState(array[i]))) 
				if(getMetaData(world.getBlockState(array[i])) == getMetaData(world.getBlockState(pos))) 
					list.add(array[i]);
				
			
		}
		
		return list;
		
	}
	
	private static List<BlockPos> addPos(World world, BlockPos pos, List<BlockPos> coalExpanded, Block type) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		boolean isRedstone = isBlockRedstone(type);
		boolean isStone = isStone(world.getBlockState(pos));
		
		EnumParticleTypes s = EnumParticleTypes.FLAME;
		if(b==1) s = EnumParticleTypes.END_ROD;
		if(b==2) s = EnumParticleTypes.REDSTONE;
		
		BlockPos[] coal = new BlockPos[] {new BlockPos(x + 1.0D, y, z), new BlockPos(x + 1.0D, y, z + 1.0D), new BlockPos(x - 1.0D, y, z), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x, y, z + 1.0D), new BlockPos(x + 1.0D, y, z - 1.0D), new BlockPos(x, y, z - 1.0D), new BlockPos(x - 1.0D, y, z - 1.0D),
										  new BlockPos(x - 1.0D, y, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z), new BlockPos(x + 1.0D, y - 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z), 
										  new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x, y - 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y - 1.0D, z - 1.0D), 
										  new BlockPos(x, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y - 1.0D, z + 1.0D), 
										  new BlockPos(x + 1.0D, y + 1.0D, z), new BlockPos(x + 1.0D, y + 1.0D, z + 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z), 
										  new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x, y + 1.0D, z + 1.0D), new BlockPos(x + 1.0D, y + 1.0D, z - 1.0D), 
										  new BlockPos(x, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z - 1.0D), new BlockPos(x - 1.0D, y + 1.0D, z + 1.0D),};
		
		for(int i = 0; i < coal.length; i++) {
			if(world.getBlockState(coal[i]) == type.getDefaultState() && !coalExpanded.contains(coal[i]) && !isStone) {
				coalExpanded.add(coal[i]);
				ParticleUtil.highlightBlock((WorldServer)world, coal[i], 0.3D, s);
			}
			if(isRedstone && !coalExpanded.contains(coal[i])) 
				if(world.getBlockState(coal[i]) == Blocks.LIT_REDSTONE_ORE.getDefaultState() && !coalExpanded.contains(coal[i]))
					coalExpanded.add(coal[i]);

			if(isStone && isStone(world.getBlockState(coal[i])) && !coalExpanded.contains(coal[i])) 
				if(getMetaData(world.getBlockState(coal[i])) == getMetaData(world.getBlockState(pos))) 
					coalExpanded.add(coal[i]);
		}

		return coalExpanded;
		
	}
	

	public static int getItemStackCount() {
		Random rn1 = new Random();
		int maximum = 200;
		int minimum = 1;
		int range = maximum - minimum + 1;		
		int rn = rn1.nextInt(range) + minimum;
		int count = 1;
		
		if(rn < 10) count = 4;
		if(rn >= 10 && rn < 40) count = 3;
		if(rn >= 40 && rn < 100) count = 2;
		if(rn >= 100 && rn < 200) count = 1;
		
		return count;
	}

	public static boolean isFurnaceResultOre(Block block)  {
		boolean flag = (block == Blocks.IRON_ORE || block == Blocks.GOLD_ORE || block == Blocks.SAND);
		return flag;
	}
	
	public static boolean isStone(IBlockState block) {
		//System.out.println("variant: " + block.getDefaultState().);
		
		if(block == Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.DIORITE) ||
		   block == Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.ANDESITE) ||
		   block == Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.GRANITE)) {
			
			return true;
			
		}
		
		return false;
	}
	
	public static ItemStack getStone(IBlockState block) {
		if(block == Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.DIORITE)) 
			return new ItemStack(Blocks.STONE, 1, getMetaData(block) + 1);
		
		if(block == Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.ANDESITE)) 
			return new ItemStack(Blocks.STONE, 1, getMetaData(block) + 1);
		
		if(block == Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.GRANITE)) 
			return new ItemStack(Blocks.STONE, 1, getMetaData(block) + 1);
		
		return new ItemStack(Item.getItemFromBlock(Blocks.STONE.getDefaultState().withProperty(VARIANT, BlockStone.EnumType.STONE).getBlock()));
	}
	
	public static int getMetaData(IBlockState state) {
		return ((BlockStone.EnumType)state.getValue(VARIANT)).getMetadata();
	}
	
	public static ItemStack getPolishedDiorite() {
		return new ItemStack(Blocks.STONE, 1, 4);
	}

	public static void getEntityItem(World world, BlockPos pos, int size, Block type, boolean shouldSmelt, boolean hasLooting) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		ItemStack coal = getStack(type, size, shouldSmelt, hasLooting);
		EntityItem entity = new EntityItem(world, x, y, z, coal);
		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
		world.spawnEntity(entity);
	}

	public static int getFortuneSize(int lootingLvl) {
	
		if(lootingLvl == 2) {
			Random rand = new Random();
			int num = 1 + rand.nextInt(100);
			if(num < 75) return 1;
			if(num >= 75 && num < 100) return 2;
		}
		
		if(lootingLvl == 3) {
			Random rand = new Random();
			int num = 1 + rand.nextInt(100);
			
			if(num < 50) {
				return 1;
			}
			if(num >= 50 && num < 85) {
				return 2;
			}
			if(num >= 85 && num < 100) {
				return 3;
			}
		}
		
		if(lootingLvl == 4) {
			Random rand = new Random();
			int num = 1 + rand.nextInt(100);
			if(num < 30) return 1;
			if(num >= 30 && num < 70) return 2;
			if(num >= 70 && num < 100) return 3;
		}
		
		return lootingLvl;
	}

	public static ItemStack getStack(Block type, int size, boolean shouldSmelt, boolean hasLooting) {
		ItemStack stack = ItemStack.EMPTY;
		if(!hasLooting) size = 1;
		if(type == Blocks.COAL_ORE) stack = new ItemStack(Items.COAL, size);
		if(type == Blocks.REDSTONE_ORE) stack = new ItemStack(Items.REDSTONE, size);
		if(type == Blocks.QUARTZ_ORE) stack = new ItemStack(Items.QUARTZ, size);
		if(type == Blocks.IRON_ORE && !shouldSmelt) stack = new ItemStack(Item.getItemFromBlock(Blocks.IRON_ORE), size);
		if(type == Blocks.GOLD_ORE && !shouldSmelt) stack = new ItemStack(Item.getItemFromBlock(Blocks.GOLD_ORE), size);
		if(type == Blocks.IRON_ORE && shouldSmelt) stack = new ItemStack(Items.IRON_INGOT, size);
		if(type == Blocks.GOLD_ORE && shouldSmelt) stack = new ItemStack(Items.GOLD_INGOT, size);
		return stack;
	}

	public static boolean isBlockRedstone(Block type) {
		if(type == Blocks.REDSTONE_ORE) {
			return true;
		}
		return false;
	}

	public static void getEntityStone(World world, BlockPos pos, IBlockState state) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		System.out.println("state: " + state);
		//TODO state = air. method call state is wrong
		ItemStack stack = getStone(state);
		EntityItem entity = new EntityItem(world, x, y, z, stack);
		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
		world.spawnEntity(entity);
	}
	
	public static boolean destroyBlock(boolean shouldSmelt, boolean isBlockStone) {
		
		if(shouldSmelt && !isBlockStone) return false;
		if(!shouldSmelt && !isBlockStone) {
			System.out.println("both negative");
			return true;
		}
		if(!shouldSmelt && isBlockStone) return false;
		if(shouldSmelt && isBlockStone) return false;
		
		return true;
	}

	
	
}
