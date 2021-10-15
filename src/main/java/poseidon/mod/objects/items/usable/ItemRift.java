package poseidon.mod.objects.items.usable;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.objects.items.general.ItemBase;
import poseidon.mod.util.ParticleUtil;

public class ItemRift extends ItemBase {
	
	NBTTagCompound nbt;
	private boolean armorEquipped;
	private ItemStack armorEquippedStack;
	
	
	public ItemRift(String name, boolean effect) {
		super(name, 1, effect);
	}
	
	public static void registerRecipe() {

	}
	

	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.AMBIENT, 1.0F, 1.0F);
		getNBT(stack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if(!stack.hasTagCompound()) {
			getNBT(stack);
		}

		
		if(entityIn != null && entityIn instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityIn;
			if(playerIn.inventory.hasItemStack(stack) && playerIn.getHeldItemMainhand() == stack) {
				if(!worldIn.isRemote) ParticleUtil.riftUpdate((WorldServer) worldIn, playerIn);
			}
		}
    		
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt2 = stack.getTagCompound();
			int dur = nbt2.getInteger("Durability");
			if(dur == 1) {
				//Skill activated
				if(entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					BlockPos pos = player.getPosition();
					BlockPos check = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
					BlockPos check2 = new BlockPos(pos.getX(), pos.getY() - 2, pos.getZ());
					
					IBlockState iBlock = worldIn.getBlockState(check);
					Block block = iBlock.getBlock();
					IBlockState iBlock2 = worldIn.getBlockState(check2);
					Block block2 = iBlock2.getBlock();
					
					if(block != null && block.getDefaultState() == Blocks.AIR.getDefaultState()) {
						//Teleported in the sky
						stack.getTagCompound().setInteger("Durability", 2);
					}
					
				}
			}
			
			if(nbt2.getInteger("Durability") == 2) {
				if(entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					BlockPos pos2 = player.getPosition();
					BlockPos pos = player.getPosition();
					
					if((player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()) >= 10) {
						player.sendStatusMessage(new TextComponentTranslation(TextFormatting.WHITE + "Durability: " + TextFormatting.GREEN + (player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()), new Object[0]), true);
					}
					if((player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()) < 10 
							&& player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage() > 5) {
						player.sendStatusMessage(new TextComponentTranslation(TextFormatting.WHITE + "Durability: " + TextFormatting.RED + (player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()), new Object[0]), true);
					}
					if((player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()) < 5) {
						player.sendStatusMessage(new TextComponentTranslation(TextFormatting.WHITE + "Durability: " + TextFormatting.DARK_RED + (player.inventory.armorItemInSlot(2).getMaxDamage() - player.inventory.armorItemInSlot(2).getItemDamage()), new Object[0]), true);
					}
					
					BlockPos check1 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());

					
					IBlockState iBlock1 = worldIn.getBlockState(check1);
					Block block1 = iBlock1.getBlock();
					
					if(block1 != null && block1.getDefaultState() != Blocks.AIR.getDefaultState()) {
						stack.getTagCompound().setInteger("Durability", 0);	
						stack.shrink(1);
						if(armorEquipped) {
							player.inventory.armorItemInSlot(2).shrink(1);
							player.inventory.armorInventory.set(2, armorEquippedStack);
							} else {
							player.inventory.armorItemInSlot(2).shrink(1);
						}
					}
				
				}
			}
		}
	}



	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack heldStack = playerIn.getHeldItem(handIn);
		ItemStack heldEquipment;
		boolean equipment;

		if(playerIn.isSneaking() && heldStack.hasTagCompound() && heldStack.getTagCompound().getBoolean("Activated")) {

			boolean isArmorEmpty = (playerIn.inventory.armorItemInSlot(2) == ItemStack.EMPTY);
			ItemStack armor = playerIn.inventory.armorItemInSlot(2);
			BlockPos pos = playerIn.getPosition();
			double x = playerIn.posX;
			double y = playerIn.posY;
			double z = playerIn.posZ;
			int heightAdded = 200;
			
			ItemStack stack2 = new ItemStack(Items.ELYTRA);
			stack2.addEnchantment(Enchantments.BINDING_CURSE, 1); stack2.addEnchantment(Enchantments.VANISHING_CURSE, 1);
			
			heldStack.getTagCompound().setInteger("Durability", 1);
			heldStack.getTagCompound().setBoolean("Activated", false);
			
			setCoordinates(worldIn, pos, playerIn, heightAdded);
			
			if(isArmorEmpty) {
				armorEquipped = false;
				} else {
				armorEquippedStack = armor.copy();
				armorEquipped = true;
			}
		
				
			if(!worldIn.isRemote) {
				ParticleUtil.riftOpening((WorldServer)worldIn, playerIn.getPosition().up(), EnumParticleTypes.DRAGON_BREATH);
			}
			stack2.damageItem(420, playerIn);
				
			if(armor.getItem() == stack2.getItem()) {
				
				sound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, pos, worldIn);

			} else if(armor.getItem() != stack2.getItem()) {
						
				setElytra(playerIn, stack2);
				sound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, pos, worldIn);
				playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.WHITE + "An elytra has been added to your inventory", new Object[0]), true);
						
			}
				
		}
			

		return new ActionResult(EnumActionResult.SUCCESS, heldStack);
		
	}
	
	private void setCoordinates(World world, BlockPos pos, EntityPlayer playerIn, int heightAdded) {
		playerIn.serverPosX = (long) playerIn.posX;
		playerIn.serverPosY = (long) ((long) playerIn.posY + (double) heightAdded);
		playerIn.serverPosZ = (long) playerIn.posZ;
		playerIn.setPosition(playerIn.posX, playerIn.posY + (double) heightAdded, playerIn.posZ);
	}
	
	private void sound(SoundEvent event, BlockPos pos, World world) {
		Main.proxy.playSoundBlock(pos, world, event, 1.0F, 1.0F);
	}
	
	private void setElytra(EntityPlayer player, ItemStack elytra) {
		player.inventory.armorInventory.set(2, elytra);
	}
	
	private void particle7(EntityPlayer player, World world) {
		if(!world.isRemote) ParticleUtil.riftTakeOff((WorldServer) world, player);
	}
	
	private void getOthers(EntityPlayer player, World world) {
		AxisAlignedBB axis = new AxisAlignedBB(player.posX + 5.0D, player.posY - 3.0D, player.posZ - 5.0D, player.posX - 5.0D, player.posY + 3.0D, player.posZ + 5.0D);
		List<Entity> playerList = world.getEntitiesWithinAABBExcludingEntity(player, axis);
		
		for(int i = 0; i < playerList.size(); i++) {
			Entity playerIns = playerList.get(i);
			if(playerIns instanceof EntityPlayer) {
				EntityPlayer playerIn = (EntityPlayer) playerIns;
				playerIn.setPosition(playerIn.posX, playerIn.posY + 200.0D, playerIn.posZ);
				playerIn.serverPosX = (long) playerIn.posX;
				playerIn.serverPosY = (long) ((long) playerIn.posY + 200.0D);
				playerIn.serverPosZ = (long) playerIn.posZ;
				world.playSound(playerIn, new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ), SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.WEATHER, 0.5F, 1.0F);
			}
		}
		
	}
	
	public void getNBT(ItemStack stack) {
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		} //Gets The compound which holds keys!
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		} //Gets the key .. if not exists it creates one (if it was taken out of the tab)
		if(nbt.hasKey("Activated")) {
			nbt.setBoolean("Activated", nbt.getBoolean("Activated"));
			} else {
			nbt.setBoolean("Activated", true);
		}
		stack.setTagCompound(nbt);
	}
	
	
	

	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.WHITE + "Sneak and right click to open a rift in the sky");
		if(stack.hasTagCompound()) {
			if(stack.getTagCompound().hasKey("Activated")) {
				if(stack.getTagCompound().getBoolean("Activated")) {
					tooltip.add(TextFormatting.WHITE + "State:" + TextFormatting.GREEN + " Activated");
					} else {
					tooltip.add(TextFormatting.WHITE + "State:" + TextFormatting.DARK_RED + " Deactivated");
				}
			}
		}
	}
	
}
