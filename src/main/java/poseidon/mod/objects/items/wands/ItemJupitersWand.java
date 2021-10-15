package poseidon.mod.objects.items.wands;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.ModAbilities;
import poseidon.mod.util.handlers.EventHelper;
import poseidon.mod.util.interfaces.IHasModel;

public class ItemJupitersWand extends ItemTool implements IHasModel {

	NBTTagCompound nbt;
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Block.REGISTRY);
	private final ToolMaterial material;
	
	public ItemJupitersWand(String name, ToolMaterial mat, boolean b) {
		super(mat, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHarvestLevel("pickaxe", mat.getHarvestLevel());
		setCreativeTab(Main.ARISTOISITEMS);
		this.material = mat;
		maxStackSize = 1;
		setMaxDamage(material.getMaxUses());
		ItemInit.ITEMS.add(this);
	}
	
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			if(player.isSneaking() && stack.hasTagCompound() && EventHelper.isItemInHands(stack, player)) player.sendStatusMessage(new TextComponentTranslation(
					TextFormatting.GRAY + "Durability: " + TextFormatting.GREEN + Integer.toString(stack.getTagCompound().getInteger("Durability")) +
					TextFormatting.GRAY + " ; Charge: " + TextFormatting.GREEN + Integer.toString(stack.getTagCompound().getInteger("Charge")), new Object[0]), true);
		}
		
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
        
		Block block = blockIn.getBlock();
		
		if(block != Blocks.BEDROCK) {
			return true;
		}
		return true;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {

		if(itemstack.hasTagCompound()) {
			
			int dur = itemstack.getTagCompound().getInteger("Durability");
			
			if(dur != 0) return false;
			if(dur == 0) return true;
			
		}
		
		return true;
		
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return false;
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		getNBT(stack);
	}
	
	private void getNBT(ItemStack stack) {
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			} else {
			nbt = new NBTTagCompound();
		}
		if(nbt.hasKey("Durability")) {
			nbt.setInteger("Durability", nbt.getInteger("Durability"));
			} else {
			nbt.setInteger("Durability", 0);
		}
		if(nbt.hasKey("Charge")) {
			nbt.setInteger("Charge", nbt.getInteger("Charge"));
			} else {
			nbt.setInteger("Charge", 0);
		}
		stack.setTagCompound(nbt);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack held = playerIn.getHeldItem(handIn);
		
		if(!playerIn.isSneaking() && !worldIn.isRemote && held.hasTagCompound() && held.getTagCompound().getInteger("Charge") != 0) {

			RayTraceResult pos = playerIn.rayTrace(100,20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();
			Entity bolt = new EntityLightningBolt(worldIn, x, y, z, false);
			worldIn.addWeatherEffect(bolt);
			worldIn.createExplosion(playerIn, x, y, z, 3.0F, true);
			playerIn.getCooldownTracker().setCooldown(held.getItem(), 20);	
			held.getTagCompound().setInteger("Charge", held.getTagCompound().getInteger("Charge") - 1);
			
		}
		
		if(playerIn.isSneaking() && held.hasTagCompound() && held.getTagCompound().hasKey("Charge") && held.getTagCompound().getInteger("Charge") != 0) {
			ModAbilities.sky2(1, playerIn, worldIn);
			held.getTagCompound().setInteger("Charge", held.getTagCompound().getInteger("Charge") - 1);
			playerIn.getCooldownTracker().setCooldown(held.getItem(), 80);	
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, held);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		if(stack.hasTagCompound()) {
			tooltip.add(TextFormatting.GRAY + "Durability: " + TextFormatting.GREEN + Integer.toString(stack.getTagCompound().getInteger("Durability")));
			tooltip.add(TextFormatting.GRAY + "Charge: " + TextFormatting.GREEN + Integer.toString(stack.getTagCompound().getInteger("Charge")));
		}
		
	}
	
	//TODO RECIPE
	public static void registerRecipe() {
//		GameRegistry.addShapedRecipe(new ResourceLocation("psm:jupiters_staff"), new ResourceLocation("psm:smalladditions_items"), new ItemStack(ItemInit.JUPITERS_STAFF),
//				new Object[] {"OOA", "FAO", "AFO", 'A', ItemInit.CHARGED_ATLAS_CORE, 'O', ItemInit.OBSIDIAN_INGOT, 'F', ItemInit.FORBIDDEN_KNOWLEDGE});
//		
	}

	
}
