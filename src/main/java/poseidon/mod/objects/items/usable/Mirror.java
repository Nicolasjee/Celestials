package poseidon.mod.objects.items.usable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.tools.ToolHoe;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.handlers.registry.SoundsHandler;

public class Mirror extends ToolHoe {
	
	boolean tped = false;
	int tick = 0;
	BlockPos[] pos = new BlockPos[] {};

	public Mirror(String name, int size) {
		super(name, ItemInit.WOOD_MULTI);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos post, EnumHand handIn,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean a = false;
		BlockPos pos = playerIn.getPosition();
		if(!worldIn.isRemote) {
			pos = playerIn.getPosition();
			if(!playerIn.isSneaking()) pos = getTop(playerIn.getPosition(), worldIn, playerIn);
			if(playerIn.isSneaking()) pos = getDip(playerIn.getPosition(), worldIn, playerIn);
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			
			playerIn.attemptTeleport(x + 0.5D, y, z + 0.5D);
			playerIn.serverPosX = (long) (x + 0.5D);
			playerIn.serverPosY = (long) y;
			playerIn.serverPosZ = (long) (z + 0.5D);
			playerIn.getHeldItem(handIn).damageItem(1, playerIn);
			ParticleUtil.mirror((WorldServer) worldIn, playerIn.getPosition(), EnumParticleTypes.END_ROD);
			
		}
		Main.proxy.playSoundBlock(playerIn.getPosition(), worldIn, SoundsHandler.MIRROR, 1.0F, 1.0F);

		
		return EnumActionResult.PASS;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		boolean a = false;
		BlockPos pos = playerIn.getPosition();

		if(!worldIn.isRemote) {
			
			
			pos = playerIn.getPosition();
			if(!playerIn.isSneaking()) pos = getTop(playerIn.getPosition(), worldIn, playerIn);
			if(playerIn.isSneaking()) pos = getDip(playerIn.getPosition(), worldIn, playerIn);
			double x = pos.getX();
			double y = pos.getY();
			double z = pos.getZ();
			
			playerIn.attemptTeleport(x + 0.5D, y, z + 0.5D);
			playerIn.serverPosX = (long) (x + 0.5D);
			playerIn.serverPosY = (long) y;
			playerIn.serverPosZ = (long) (z + 0.5D);
			playerIn.getHeldItem(handIn).damageItem(1, playerIn);
			ParticleUtil.mirror((WorldServer) worldIn, playerIn.getPosition(), EnumParticleTypes.END_ROD);
		}
		
		Main.proxy.playSoundBlock(playerIn.getPosition(), worldIn, SoundsHandler.MIRROR, 1.0F, 1.0F);
		
		
		return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	private void teleport(double x, double y, double z, EntityPlayer playerIn) {
		playerIn.attemptTeleport(x + 0.5D, y, z + 0.5D);
		playerIn.serverPosX = (long) (x + 0.5D);
		playerIn.serverPosY = (long) y;
		playerIn.serverPosZ = (long) (z + 0.5D);
	}
	
	
	private BlockPos getTop(BlockPos playerPos, World worldIn, EntityPlayer player) {

		
		double x = playerPos.getX();
		double y = playerPos.getY();
		double z = playerPos.getZ();
		
		Block getBlock = worldIn.getBlockState(playerPos).getBlock();
		
		for(double i = 0; i < 400; i++) {
			BlockPos newPos = new BlockPos(x, y + i, z);
			IBlockState state = worldIn.getBlockState(newPos);
			Block block = state.getBlock();
			
			if((water(worldIn, block) || air(worldIn, block)) && worldIn.canBlockSeeSky(newPos)) {
				BlockPos newPos2 = new BlockPos(x, y + i + 1.0D, z);
				IBlockState state2 = worldIn.getBlockState(newPos2);
				Block block2 = state2.getBlock();

				if((water(worldIn, block2) || air(worldIn, block)) && worldIn.canBlockSeeSky(newPos)) {
					BlockPos newPos3 = new BlockPos(x, y + i - 1.0D, z);
					IBlockState state3 = worldIn.getBlockState(newPos3);
					Block block3 = state3.getBlock();
					
					return newPos;
				}
			}
		}
		return playerPos;
		
	}
	
	private BlockPos getDip(BlockPos playerPos, World worldIn, EntityPlayer player) {
		double x = playerPos.getX();
		double y = playerPos.getY() - 1.0D;
		double z = playerPos.getZ();
		
		Block getBlock = worldIn.getBlockState(playerPos).getBlock();
		
		for(double i = 0; i < 300; i++) {
			BlockPos newPos = new BlockPos(x, y - i, z);
			IBlockState state = worldIn.getBlockState(newPos);
			Block block = state.getBlock();
			
			if(block.getDefaultState() == Blocks.AIR.getDefaultState()) {
				BlockPos newPos2 = new BlockPos(x, y - i - 1.0D, z);
				IBlockState state2 = worldIn.getBlockState(newPos2);
				Block block2 = state2.getBlock();

				if(block2.getDefaultState() == Blocks.AIR.getDefaultState()) {
					BlockPos newPos3 = new BlockPos(x, y - i - 2.0D, z);
					IBlockState state3 = worldIn.getBlockState(newPos3);
					Block block3 = state3.getBlock();
					
					if(block3.getDefaultState() != Blocks.LAVA && block3.getDefaultState() != Blocks.AIR) {
						return newPos3;
						
					}
					
				}
			}
		}
		return playerPos;
	}
	
	private boolean isWaterOrAir(World worldIn, Block block) {
		if(block.getDefaultState() == Blocks.AIR.getDefaultState() || block.getMaterial(block.getDefaultState()) == Material.WATER) return true;
		else return false;
	}
	
	private boolean air(World worldIn, Block block) {
		if(block.getDefaultState() == Blocks.AIR.getDefaultState()) return true;
		else return false;
	}
	
	private boolean water(World worldIn, Block block) {
		if(block.getDefaultState() == Blocks.WATER.getDefaultState() || block.getMaterial(block.getDefaultState()) == Material.WATER) return true;
		else return false;
	}
}
