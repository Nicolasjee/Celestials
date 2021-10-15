package poseidon.mod.objects.items.wands;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.init.PotionInit;
import poseidon.mod.objects.block.general.lighthelp.TileEntityRipple;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class ItemBadJuju extends ToolHoe {
	
	private EntityAreaEffectCloud previous;
	
	public ItemBadJuju(String name) {
		super(name, ItemInit.DEPTCHARM);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack stack = playerIn.getHeldItemMainhand();

	
		if(stack.hasTagCompound()) {
		
			NBTTagCompound nbt = stack.getTagCompound();
			int sound = nbt.getInteger("Sound");

			RayTraceResult pos = playerIn.rayTrace(40,1.0F);
			Random rand = new Random();
			double x = pos.getBlockPos().getX() + 0.5D;
			double y = pos.getBlockPos().getY() + 1;
			double z = pos.getBlockPos().getZ() + 0.5D;
			
			if(!worldIn.isRemote) {
			
				EntityAreaEffectCloud a = new EntityAreaEffectCloud(worldIn,x,y,z);
				a.addEffect(new PotionEffect(PotionInit.BADJUJU));
				a.setDuration(40);
		        a.setRadius(3.0F);
		        a.setRadiusOnUse(-0.5F);
		        a.setWaitTime(2);
		        a.setRadiusPerTick(-a.getRadius() / (float)a.getDuration());
		        a.setPotion(PotionInit.LONG_BADJUJU_POTION);
				((WorldServer)worldIn).spawnEntity(a);
			

//				if(worldIn.getBlockState(new BlockPos(x,y,z)) == Blocks.AIR.getDefaultState()) (worldIn).setBlockState(new BlockPos(x,y,z), BlockInit.RIPPLE.getDefaultState());
//				if((worldIn).getTileEntity(new BlockPos(x,y,z)) instanceof TileEntityRipple) {
//					TileEntityRipple t = (TileEntityRipple) (worldIn).getTileEntity(new BlockPos(x,y,z));
//					t.isParent = true;
//				}
				
				stack.damageItem(1, playerIn);
			}
			
			Main.proxy.playSoundBlock(playerIn.getPosition(), worldIn, getSound(sound), 1.0F, 1.0F);

			
			checkSound(sound, stack);
			
			playerIn.getCooldownTracker().setCooldown(stack.getItem(), 10);
		}
			
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound()) getNBT(stack);
		
	}
	
	private void getNBT(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setInteger("Sound", 1);
			
			stack.setTagCompound(nbt);
		}
	}
	
	private SoundEvent getSound(int change) {
		if(change == 1) return SoundsHandler.J1;
		if(change == 2) return SoundsHandler.J2;
		if(change == 3) return SoundsHandler.J3;
		if(change == 4) return SoundsHandler.J4;
		else return SoundsHandler.J1;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		return EnumActionResult.PASS;
	}
	
	private void checkSound(int change, ItemStack stack) {
		change++;
		if(change == 5) change = 1;
		if(change > 5 || change < 1) change = 1;
		
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			nbt.setInteger("Sound", change);
		}
		
	}
	
}
