package poseidon.mod.objects.items.wands;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.BlockHelper;
import poseidon.mod.objects.tools.ToolSword;
import poseidon.mod.util.handlers.EventHelper;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ItemWayPoint extends ToolSword {

	int durabilitystack;
	NBTTagCompound nbt;
	private boolean isActive = false;
	
	public ItemWayPoint(String name, int size, boolean e) {
		super(name, ItemInit.VELOCITY, e);
	}
	
	public static void registerRecipe() {


	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ();
	
		ItemStack stack = player.getHeldItem(hand);
		if(!stack.hasTagCompound() && !player.isSneaking()) {
			getNBT(stack);
			} else {
			System.out.println("New Coordinates init");
			setNewCoords(stack, player, stack.getTagCompound(), (int) x, (int) y, (int) z);
				
		}
		
		
		return EnumActionResult.SUCCESS;
	}
	
	private void setPlayerStatus(EntityPlayer player, int x, int y, int z) {
		player.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Saved Position" + TextFormatting.WHITE + ": " +
				TextFormatting.BLUE + "X" + 
				TextFormatting.WHITE + ":" + 
				TextFormatting.GRAY + x +
				TextFormatting.WHITE + "," + 
				TextFormatting.BLUE + "Y" + 
				TextFormatting.WHITE + ":" + 
				TextFormatting.GRAY + y + 
				TextFormatting.WHITE + "," + 
				TextFormatting.BLUE + "Z" + 
				TextFormatting.WHITE + ":" + 
				TextFormatting.GRAY + z + TextFormatting.RESET, new Object[0]), true
);
	}
	
	private void setNewCoords(ItemStack stack, EntityPlayer player, NBTTagCompound nbt, int x, int y, int z) {
		
		if(nbt.hasKey("Coordinates")) {
			
			int[] c = nbt.getIntArray("Coordinates");
			
			for(int i = 0; i < c.length; i++) {
				if(c[i] == 0 && c[i+1] == 0 && c[i+2] == 0) {

					setPlayerStatus(player, x, y, z);
					setNewCoordsAt(stack, nbt, i, c, x, y, z);
					System.out.println("i: " + i);
					break;
					
					} else {
					if(i < 15) i += 2;
					if(i == 15) break;
				}
			}
			
		}
		
	}
	
	
	private void setNewCoordsAt(ItemStack stack, NBTTagCompound nbt, int i, int[] c, int x, int y, int z) {
	
		if(i == 0) {
			nbt.setIntArray("Coordinates", new int[] {x, y, z, 0, 0, 0,  0, 0, 0,  0,0,0,  0, 0, 0,  0, 0, 0, c[c.length-1]});
		}
		
		if(i == 3) {
			nbt.setIntArray("Coordinates", new int[] {c[0], c[1], c[2], x,y,z,  0, 0, 0, 0, 0, 0,  0, 0, 0,  0, 0, 0, c[c.length-1]});
		}
		
		if(i == 6) {
			nbt.setIntArray("Coordinates", new int[] {c[0], c[1], c[2], c[3], c[4], c[5],  x, y, z,  0,0,0, 0, 0, 0,  0, 0, 0, c[c.length-1]});
		}
		
		if(i == 9) {
			nbt.setIntArray("Coordinates", new int[] {c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], x, y, z,  0, 0, 0, 0, 0, 0, c[c.length-1]});
		}
		
		if(i == 12) {
			nbt.setIntArray("Coordinates", new int[] {c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9], c[10], c[11], x, y, z, 0, 0, 0, c[c.length-1]});
		}
		
		if(i == 15) {
			nbt.setIntArray("Coordinates", new int[] {c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9], c[10], c[11], c[12], c[13], c[14], x, y, z, c[c.length-1]});
		}
		
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		
		if(target != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates") && attacker instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) attacker;
			
			if(target instanceof EntityPlayer && !((EntityPlayer)target).world.isRemote) {
				
				EntityPlayer tar = (EntityPlayer) target;
				BlockPos lastPos = tar.getPosition();
				int[] c = stack.getTagCompound().getIntArray("Coordinates");
				int focus = c[c.length - 1];
				
				int x = c[focus*3];
				int y = c[(focus*3)+1]+1;
				int z = c[(focus*3)+2];
				
				BlockPos pos = new BlockPos(x, y, z);
				tar.attemptTeleport(x, y, z);
				tar.serverPosX = (long) x;
				tar.serverPosY = (long) y;
				tar.serverPosZ = (long) z;
			
				Main.proxy.playSoundBlock(pos, tar.world, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
				Main.proxy.playSoundBlock(lastPos, tar.world, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
				stack.damageItem(1, player);
				return true;
			}
			
			BlockPos lastPos = target.getPosition();
			int[] c = stack.getTagCompound().getIntArray("Coordinates");
			int focus = c[c.length - 1];
			
			int x = c[focus*3];
			int y = c[(focus*3)+1]+1;
			int z = c[(focus*3)+2];
			
			BlockPos pos = new BlockPos(x, y, z);
			target.attemptTeleport(x, y,z);
			target.setPosition(x, y, z);
			
			Main.proxy.playSoundBlock(pos, player.world, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
			Main.proxy.playSoundBlock(lastPos, player.world, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
			stack.damageItem(1, player);
				
			return true;
			
		}
		
		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates")) {
			
			BlockPos lastPos = playerIn.getPosition();
			int[] c = stack.getTagCompound().getIntArray("Coordinates");
			int focus = c[c.length - 1];
			
			int x = c[focus*3];
			int y = c[(focus*3)+1]+1;
			int z = c[(focus*3)+2];
			
			BlockPos pos = new BlockPos(x, y, z);
			playerIn.attemptTeleport(x, y, z);
			playerIn.serverPosX = (long) x;
			playerIn.serverPosY = (long) y;
			playerIn.serverPosZ = (long) z;
		
			Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
			Main.proxy.playSoundBlock(lastPos, worldIn, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
			
		//playerIn.getCooldownTracker().setCooldown(this, 100);
		stack.damageItem(1, playerIn);



		
		return new ActionResult(EnumActionResult.SUCCESS, stack);
			
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
			
	private void bridge(World worldIn, EntityPlayer player) {
		
		if(!worldIn.isRemote) {
			
			BlockPos pos = player.getPosition();
			double x1 = pos.getX(); double y1 = pos.getY() - 1.0D; double z1 = pos.getZ();
			
			RayTraceResult pose = player.rayTrace(100,20);
			double x = pose.getBlockPos().getX();
			double y = pose.getBlockPos().getY();
			double z = pose.getBlockPos().getZ();
			
			if((x1 == x || z1 == z) && y == y1) {
				if(x1 == x && z1 != z && y == y1) BlockHelper.putBridge("x", worldIn, player, new double[] {x1, y1, z1}, new double[] {x,y,z});
				if(x1 != x && z1 == z && y == y1) BlockHelper.putBridge("z", worldIn, player, new double[] {x1, y1, z1}, new double[] {x,y,z});
			}
		}
	}

	
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}
		
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
		
			BlockPos pos = player.getPosition().down();
			double x = pos.getX(); double y = pos.getY() - 1.0D; double z = pos.getZ();
			BlockPos[] sur = new BlockPos[] {pos, pos.north(), pos.south(), pos.west(), pos.east(), pos.north().east(), pos.north().west(), pos.south().east(), pos.south().west()};
			
			for(int i = 0; i < sur.length; i++) {
				if(worldIn.getBlockState(sur[i]) == Blocks.AIR.getDefaultState() || isField(sur[i], worldIn)) {
					//worldIn.setBlockState(sur[i], BlockInit.FIELD4.getDefaultState());
				}
			}
			
		}
		
		

		
	}
	
	private boolean isField(BlockPos pos, World world) {
		if(world.getBlockState(pos) == BlockInit.FIELD1.getDefaultState() ||
		   world.getBlockState(pos) == BlockInit.FIELD2.getDefaultState() ||
		   world.getBlockState(pos) == BlockInit.FIELD3.getDefaultState() ||
		   world.getBlockState(pos) == BlockInit.FIELD4.getDefaultState()) return true;
		else return false;
	}
	
	private void getNBT(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			nbt = new NBTTagCompound();
		}
		if(nbt.hasKey("Coordinates")) {
			nbt.setIntArray("Coordinates", nbt.getIntArray("Coordinates"));
			} else {
			nbt.setIntArray("Coordinates", new int[] {0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 0});
		}

		stack.setTagCompound(nbt);
	}
	
	public void addInformation(ItemStack stack, World world, List<String> addlore, ITooltipFlag advanced) {

		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates")) {

			int[] c = stack.getTagCompound().getIntArray("Coordinates");
			int m = 0;
			
			for(int i = 0; i < c.length; i++) {
				if(c[i] != 0 && c[i+1] != 0 && c[i+2] != 0) {
					addlore.add(addList(m, c[i], c[i+1], c[i+2], c[c.length-1]));
					m++;
					if(i < 15) i += 2;
					if(i == 15) break;
					} else {
					if(i < 15) i += 2;
					if(i == 15) break;
				}
			}
			
		}
		


		

	}
	
	private String addList(int i, int x, int y, int z, int focus) {
		
		TextFormatting format = TextFormatting.GRAY;
		if(i == focus) format = TextFormatting.GREEN;
		
		return "" + format + "Saved " + Integer.toString(i+1) + TextFormatting.WHITE + ": " + 
		TextFormatting.BLUE + "X: " + TextFormatting.WHITE + Integer.toString(x) + TextFormatting.WHITE + "; " +
		TextFormatting.BLUE + "Y: " + TextFormatting.WHITE + Integer.toString(y) + TextFormatting.WHITE + "; " +
		TextFormatting.BLUE + "Z: " + TextFormatting.WHITE + Integer.toString(z) + TextFormatting.WHITE + "; ";	
		
	}
	
}
