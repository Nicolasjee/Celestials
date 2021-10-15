package poseidon.mod.objects.block.netherreactor.nethercore;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockHidden;
import poseidon.mod.objects.block.netherreactor.nethercore.handlers.TileEntityNetherWart;

public class BlockNetherWart extends BlockHidden implements ITileEntityProvider {

	public BlockNetherWart(String name, Material mat, float light, float hard, float res, int tool) {
		super(name, mat, light, hard, res, tool);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNetherWart();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack held = playerIn.getHeldItem(hand);
		
		if(held.getItem() == Items.NETHER_WART) {
			
			playSound(state, worldIn, pos);
			worldIn.setBlockState(pos, getNewState(state));
			
			return true;
			
			} else {
				
			return false;
		}
	}
	
	private void playSound(IBlockState state, World worldIn, BlockPos pos) {
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK1) Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.0F, 0.25F);
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK2) Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1.0F, 0.25F);
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK3) Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1.0F, 0.25F);
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK4) Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1.0F, 0.25F);
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK5) Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1.0F, 0.25F);
	}
	
	private IBlockState getNewState(IBlockState state) {
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK1) return Blocks.NETHER_WART_BLOCK.getDefaultState();
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK2) return BlockInit.NETHER_WART_BLOCK1.getDefaultState();
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK3) return BlockInit.NETHER_WART_BLOCK2.getDefaultState();
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK4) return BlockInit.NETHER_WART_BLOCK3.getDefaultState();
		if(state.getBlock() == BlockInit.NETHER_WART_BLOCK5) return BlockInit.NETHER_WART_BLOCK4.getDefaultState();
		else return Blocks.NETHER_WART_BLOCK.getDefaultState();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityNetherWart();
	}
	
	
	
}
