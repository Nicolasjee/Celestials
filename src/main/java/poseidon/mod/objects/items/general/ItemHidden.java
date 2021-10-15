package poseidon.mod.objects.items.general;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.ModAbilities;
import poseidon.mod.util.interfaces.IHasModel;

public class ItemHidden extends Item implements IHasModel {
	
	boolean effect;

	public ItemHidden(String name, int size, boolean eff) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(size);
		this.effect = eff;
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//		if(stack.getItem() == ItemInit.ZOMBIE_STAFF && !stack.hasTagCompound()) {
//			getNBT(stack);
//		}
		if(stack.getItem() == ItemInit.WARRANT && !stack.hasTagCompound()) {
			getNBT(stack);
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(stack.getItem() == ItemInit.KILL_STICK) target.onKillCommand();
		
		return true;
	}
	
	public void getNBT(ItemStack stack) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 10);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		stack.setTagCompound(nbt);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack held = player.getHeldItem(hand); 
		//if(held.getItem() != ItemInit.ZOMBIE_STAFF) return EnumActionResult.PASS;
		
		if(!worldIn.isRemote) {
			if(held.hasTagCompound()) {
				NBTTagCompound n = held.getTagCompound();
				int dur = n.getInteger("Durability");
				if(dur > 0) {
					EntityZombie vil = new EntityZombie(worldIn);
					vil.setLocationAndAngles(pos.getX(), pos.getY() + 1.0D, pos.getZ(), vil.rotationYaw, vil.rotationPitch);
					worldIn.spawnEntity(vil);
					n.setInteger("Durability", dur - 1);
				}
			}
		}
		
		return EnumActionResult.SUCCESS;
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(stack.getItem() != ItemInit.WARRANT) return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		if(stack.hasTagCompound()) {
			stack.getTagCompound().setInteger("Durability", 11);
			ModAbilities.freezeEntities(1, playerIn, worldIn);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return effect;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D(){ return true; }
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
