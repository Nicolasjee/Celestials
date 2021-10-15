package poseidon.mod.objects.block.general.elytroncrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;

public class ElytronCrop extends BlockCrops {

	private static final AxisAlignedBB[] weed = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
	
	public ElytronCrop(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		spawnAsEntity(worldIn, pos, new ItemStack(this.getSeed(), getFortune(false, getMultiplier(true))));
		if(this.isMaxAge(state)) spawnAsEntity(worldIn, pos, new ItemStack(this.getCrop(), getFortune(true, getMultiplier(true))));
	}
	
	
	private int getFortune(boolean b, int c) {
		Random rand = new Random();
		int maximum = 100;
		int minimum = 1;
		int range = maximum - minimum + 1;
		
		boolean k = getBooleanFromInt(c);
		
		if((rand.nextInt(range) + minimum) < 50) {
			Main.log("1");
			if(b) return 2 + getMultiplier(k) - isTooMuch(2 + getMultiplier(k));
			else return 1;
		}
		else {
			Main.log("0");
			if(b) return 1 + getMultiplier(k);
			else return 0;
		}
	}
	
	private int isTooMuch(int o) {
		if(o >= 5) return Math.abs(4 - o);
		else return 0;
		
	}
	
	private boolean getBooleanFromInt(int c) {
		if(c <= 2) return true;
		else return false;
	}
	
	private int getMultiplier(boolean b) {
		Random rand = new Random();
		int maximum = 3;
		int minimum = 1;
		int range = maximum - minimum + 1;
		if(b) return rand.nextInt(range) + minimum;
		else return 1;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 2;
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		if(pos.getY() > 40) return false;
		if(worldIn.canSeeSky(pos)) return false;
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.ELYTRON_DUST;
	}
	
	@Override
	protected Item getSeed() {
		return ItemInit.ELYTRON_SEEDS;
	}

	@Override
	protected Item getCrop() {
		return ItemInit.ELYTRON_DUST;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return weed[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	}
	
}
