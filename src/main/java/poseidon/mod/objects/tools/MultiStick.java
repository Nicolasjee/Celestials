package poseidon.mod.objects.tools;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.PolarisAbilities;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.interfaces.IHasModel;

public class MultiStick extends ItemTool implements IHasModel, TeslaProperties {
	
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Block.REGISTRY);
	private final ToolMaterial material;
	private boolean chop;
	
	public MultiStick(String name, ToolMaterial material)
	{
		super(material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHarvestLevel("pickaxe", material.getHarvestLevel());
		this.material = material;
		maxStackSize = 1;
		setMaxDamage(material.getMaxUses());
		ItemInit.ITEMS.add(this);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D(){ return true; }
	//public float getDamageVsEntity() { return this.material.getDamageVsEntity(); }
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(stack.getItem() == ItemInit.KILL_STICK) {
			if(!stack.hasTagCompound()) getNBT(stack); 
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
		
		ItemStack held = playerIn.getHeldItemMainhand();
		
		
		if(!playerIn.isSneaking() && held.getItem() == ItemInit.KILL_STICK) {
			
			if(held.hasTagCompound()) {
				
				NBTTagCompound nbt = held.getTagCompound();
				int[] a = nbt.getIntArray("Active");
				int i = a[1];
				ItemStack stack = held;
				EntityPlayer player = playerIn;
				
				if(i == 1) PolarisAbilities.choke(player, world);
				if(i == 2) PolarisAbilities.fireball(player, world);
				if(i == 3) PolarisAbilities.ground(player, world);
				if(i == 4) PolarisAbilities.lightning(player, world);
				if(i == 5) PolarisAbilities.snap(player, world);
				if(i == 6) PolarisAbilities.teleport(player, world);
				if(i == 7) PolarisAbilities.tnt(player, world);
				if(i == 8) PolarisAbilities.force(player);
				if(i == 9) toggle(held, playerIn);
				
			}
			
		}
		
		if(playerIn.isSneaking()) {
			
			if(held.hasTagCompound()) {
				
				NBTTagCompound nbt = held.getTagCompound();
				int[] a = nbt.getIntArray("Active");
				nbt.setIntArray("Active", new int[] {0,getNextNumber(a[1])});
				playerIn.sendStatusMessage(new TextComponentTranslation("Power set to " + TextFormatting.GREEN + s(getNextNumber(a[1])), new Object[0]), true);
			}
			
		}
		
		return super.onItemRightClick(world, playerIn, handIn);
	}
	
	private void toggle(ItemStack held, EntityPlayer player) {
		if(held.hasTagCompound() && held.getTagCompound().hasKey("Active")) {
			int[] a = held.getTagCompound().getIntArray("Active");
			if(a[1] == 9 && a[0] == 0) held.getTagCompound().setIntArray("Active", new int[] {1, a[1]});
			if(a[1] == 9 && a[0] == 1) held.getTagCompound().setIntArray("Active", new int[] {0, a[1]});
		}
	}
	
	private int getNextNumber(int i) {
		if(i == 9) return 1;
		else return i + 1;
	}
	
	private void getNBT(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setIntArray("Active", new int[] {0,1});
		stack.setTagCompound(nbt);
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
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}


	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
									EntityLivingBase entityLiving) {
    	super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    	if(worldIn.getBlockState(pos).getBlock() instanceof BlockLog) {
	    	int y = 0;
	    	IBlockState BROKEN  = null;
			BlockPos nextBlock = new BlockPos(pos.getX(), pos.getY()+1+y, pos.getZ());
			getLeaves(worldIn, nextBlock, BROKEN, pos, y);
			if(chop) {
				while(worldIn.getBlockState(nextBlock).getBlock() instanceof BlockLog) {
					IBlockState OLD = worldIn.getBlockState(nextBlock);
					if(worldIn.getBlockState(nextBlock) != BROKEN)
					BROKEN = worldIn.getBlockState(nextBlock);
					worldIn.destroyBlock(nextBlock, true);
					y++;
					nextBlock = new BlockPos(pos.getX(), pos.getY()+1+y, pos.getZ());
				}

			}
			return true;
    	} else {
    		return false;
    	}
    		
	}
	

	
	private void getLeaves(World worldIn, BlockPos nextBlock, IBlockState BROKEN, BlockPos pos, int y) {
		while(worldIn.getBlockState(nextBlock).getBlock() instanceof BlockLog) {
			if(worldIn.getBlockState(nextBlock) != BROKEN)
				BROKEN = worldIn.getBlockState(nextBlock);
			y++;
			nextBlock = new BlockPos(pos.getX(), pos.getY()+1+y, pos.getZ());
		}
		BlockPos newpos = new BlockPos(pos.getX(), pos.getY() + y + 1, pos.getZ());
		if(worldIn.getBlockState(newpos).getBlock() instanceof BlockLeaves) {
			chop = true;
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Shift and right-click to change ability");
		if(stack.getItem() == ItemInit.KILL_STICK && stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			for(int i = 1; i < 10; i++) {
				tooltip.add(t(nbt,i) + s(i));
			}
		}
	}
	
	private TextFormatting t(NBTTagCompound nbt, int b) {
		if(nbt.hasKey("Active")) {
			
			int[] a = nbt.getIntArray("Active");
			int i = a[1];
			int j = a[0];
			
			TextFormatting green = TextFormatting.GREEN;
			TextFormatting gray = TextFormatting.GRAY;
			
			if(i == b && i != 9) return green;
			else if(b == 9 && j == 1) return green;
			else return gray;
			
		}
		return TextFormatting.WHITE;
	}
	
	private String s(int b) {
		switch(b) {
		case 1: return "Freeze Entity";
		case 2: return "Fireball";
		case 3: return "Push All Entities";
		case 4: return "Lightning";
		case 5: return "Remove All Entities";
		case 6: return "Teleport";
		case 7: return "Place Primed TNT";
		case 8: return "Push Entity";
		case 9: return "Dead Eye";
		}
		return "Null";
	}
	

	

}
