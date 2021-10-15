package poseidon.mod.objects.items.wands;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.util.helpers.diamondfinder.DiamondFinder;
import poseidon.mod.util.helpers.tracehelper.TraceSpawner;

public class ItemMozesStaff extends ToolHoe {

	int i = 0;
	
	public ItemMozesStaff(String name, int size, boolean b) {
		super(name, ItemInit.WOOD_MULTI);
	}
	
	public static void registerRecipe() {

	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

		
		return EnumActionResult.PASS;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()) getNBT(stack);
		
		if(stack.hasTagCompound() && entityIn instanceof EntityPlayer) {
			
			NBTTagCompound nbt = stack.getTagCompound();
			EntityPlayer player = (EntityPlayer) entityIn;
			boolean flag1 = nbt.hasKey("Active");
			boolean flag2 = nbt.hasKey("Cooldown");
			int dur = 0;
			
			if(flag1) {
				boolean active = nbt.getBoolean("Active");
				
				if(flag1 && active && stack.getItemDamage() > 800) nbt.setBoolean("Active", false);
				
				if(flag1 && active && !isInHands(player)) nbt.setBoolean("Active", false);
				
				if(active && stack.getItemDamage() < ItemInit.WOOD_MULTI.getMaxUses() && flag2 && nbt.getInteger("Cooldown") == 20) {
					stack.damageItem(1, player);
					nbt.setInteger("Cooldown", 0);
				}
				
				if(active) {
					removeBlocks(worldIn, player.getPosition());
					nbt.setInteger("Cooldown", nbt.getInteger("Cooldown") + 1);
				}
				
				if(nbt.getInteger("Cooldown") > 20) nbt.setInteger("Cooldown", 20);
			}
		}
	}
	
	private boolean isInHands(EntityPlayer player) {
		if(player.getHeldItemMainhand().getItem() == ItemInit.MOZES_STAFF || player.getHeldItemOffhand().getItem() == ItemInit.MOZES_STAFF) return true;
		else return false;
	}
	
	private void getBox(World world, BlockPos pos, EntityPlayer playerIn) {
		
		Iterable<BlockPos> posses = pos.getAllInBox(pos.north(10).east(10).up(6), pos.south(10).west(10).down(6));
		int i = 0;

		for(BlockPos check : posses) {
			
			if(world.getBlockState(check) == Blocks.DIAMOND_ORE.getDefaultState()) {
				i++;
			}
		}
		
		for(BlockPos check : posses) {
			
			if(world.getBlockState(check) == Blocks.DIAMOND_ORE.getDefaultState()) {
				
				DiamondFinder.execute(world, playerIn, check);
				break;
				
			}
			
		}
		
		System.out.println("i: " + i);
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		
		if(stack.hasTagCompound()) {
			NBTTagCompound n = stack.getTagCompound();

			if(n.hasKey("Active")) {
				n.setBoolean("Active", !n.getBoolean("Active"));
			}
			
			
		}
		
//		RayTraceResult pos = playerIn.rayTrace(100,20);
//		Random rand = new Random();
//		double x = pos.getBlockPos().getX();
//		double y = pos.getBlockPos().getY() + 1;
//		double z = pos.getBlockPos().getZ();
//		
//		EntityAreaEffectCloud a = new EntityAreaEffectCloud(worldIn,x,y,z);
//		a.addEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE));
//		worldIn.spawnEntity(a);
		
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().getBoolean("Active")) return true;
		if(stack.hasTagCompound() && !stack.getTagCompound().getBoolean("Active")) return false;
		else return false;
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}

	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		} // Gets The compound which holds keys!

		if (nbt.hasKey("Active")) {
			nbt.setBoolean("Active", nbt.getBoolean("Active"));
		} else {
			nbt.setBoolean("Active", false);
		}
		if (nbt.hasKey("Cooldown")) {
			nbt.setInteger("Cooldown", nbt.getInteger("Cooldown"));
		} else {
			nbt.setInteger("Cooldown", 0);
		}
		stack.setTagCompound(nbt);
	}
	
	private void removeBlocks(World world, BlockPos pos) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		BlockPos[] p = new BlockPos[] {new BlockPos(x, y, z), pos.east(), pos.east().up(),
						 	 	 	   pos.west(), pos.west().up(), pos.north(), pos.north().up(),
						 	 	 	   pos.south(), pos.south().up(),
						 	 	 	   pos.north().west(), pos.north().west().up(),
						 	 	 	   pos.north().east(), pos.north().east().up(),
						 	 	 	   pos.south().west(), pos.south().west().up(),
						 	 	 	   pos.south().east(), pos.south().east().up(),
						 	 	 	   
						 	 	 	   pos.south().up(2), pos.north().up(2), pos.west().up(2),
						 	 	 	   pos.east().up(2),
						 	 	 	   
						 	 	 	   pos.north().west().up(2), pos.north().east().up(2),
						 	 	 	   pos.south().west().up(2), pos.south().east().up(2),
						 	 	 	   
					 	 	 	   pos.down(1), pos.west().down(1), pos.east().down(1), pos.north().down(1), pos.south().down(1),
						 	 	 	 pos.north().east().down(1), pos.west().north().down(1), pos.south().east().down(1), pos.south().west().down(1),
						 	 	 	   
						 	 	 	   pos.down(2), pos.west().down(2), pos.east().down(2), pos.north().down(2), pos.south().down(2),
						 	 	 	   pos.north().east().down(2), pos.west().north().down(2), pos.south().east().down(2), pos.south().west().down(2)
						 	 	 	   
						 	 	 
		};
		
		for(int i = 0; i < p.length; i++) {
			
			if(!world.isRemote) {
			
				if(world.getBlockState(p[i]) == Blocks.WATER.getDefaultState()) 
					((WorldServer) world).setBlockState(p[i], BlockInit.MOZESAIR.getDefaultState());
				
				if(world.getBlockState(p[i]).getMaterial() == Material.WATER) 
					((WorldServer) world).setBlockState(p[i], BlockInit.MOZESAIR.getDefaultState());
				
			}
		}
		
	}
	
	private void removeSur(World world, BlockPos pos) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		
		BlockPos[] p = new BlockPos[] {pos.east(), pos.east().up(),
						 	 	 	   pos.west(), pos.west().up(), pos.north(), pos.north().up(),
						 	 	 	   pos.south(), pos.south().up(),
						 	 	 	   pos.north().west(), pos.north().west().up(),
						 	 	 	   pos.north().east(), pos.north().east().up(),
						 	 	 	   pos.south().west(), pos.south().west().up(),
						 	 	 	   pos.south().east(), pos.south().east().up(),
						 	 	 	   
						 	 	 	   pos.south().up(2), pos.north().up(2), pos.west().up(2),
						 	 	 	   pos.east().up(2),
						 	 	 	   
						 	 	 	   pos.north().west().up(2), pos.north().east().up(2),
						 	 	 	   pos.south().west().up(2), pos.south().east().up(2),
						 	 	 	   
						 	 	 	   pos.down(1), pos.west().down(1), pos.east().down(1), pos.north().down(1), pos.south().down(1),
						 	 	 	   pos.north().east().down(1), pos.west().north().down(1), pos.south().east().down(1), pos.south().west().down(1),
						 	 	 	   
						 	 	 	   pos.down(2), pos.west().down(2), pos.east().down(2), pos.north().down(2), pos.south().down(2),
						 	 	 	   pos.north().east().down(2), pos.west().north().down(2), pos.south().east().down(2), pos.south().west().down(2)
						 	 	 	   
						 	 	 
		};
		
		for(int i = 0; i < p.length; i++) {
			if(world.getBlockState(p[i]) == BlockInit.MOZESAIR.getDefaultState()) 
				world.setBlockState(p[i], Blocks.AIR.getDefaultState());
			
		}
		
	}
	
	
}
