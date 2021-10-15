package poseidon.mod.objects.block.aiolonsynth;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.general.BlockHidden;
import poseidon.mod.objects.block.redstonereceiver.TileEntityRedstoneReceiver;

public class BlockAiolonSynth extends BlockHidden {
	
	public BlockAiolonSynth(String name) {
		super(name, Material.ROCK, 0.0F, 3.0F, 2.0F, 1);
	}
	

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		if(worldIn.getTileEntity(pos) instanceof TileEntityAiolonSynth) {
			
			TileEntityAiolonSynth t = (TileEntityAiolonSynth) worldIn.getTileEntity(pos);
			
			if(t.power > 0) {
				
				Main.proxy.dropItem(pos, worldIn, Items.REDSTONE, t.power);
				
			}
			
		}
		
	}
	

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		

			
			TileEntity s = worldIn.getTileEntity(pos);
			
			if(s instanceof TileEntityAiolonSynth) {
				
				TileEntityAiolonSynth t = (TileEntityAiolonSynth) s;
				
				if(blockAbove(worldIn, pos)) t.active = !t.active;
				
				if(t.active) playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.GREEN + "Activated", new Object[0]), true);
				if(!t.active) playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.RED + "Deactivated", new Object[0]), true);
				
			}
			
		
		
		return true;
	}
	
	
	
	
	private boolean blockAbove(World world, BlockPos pos) {
		//System.out.println("s, " + world.getBlockState(pos.up()) + ", " + world.getTileEntity(pos.up()));
		if(world.getTileEntity(pos.up()) instanceof TileEntityRedstoneReceiver) {
			//System.out.println("Above block is a redstone receiver");
			return true;
			} else {
			return false;
		}
	}
	
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityAiolonSynth();
	}
	

	
	
}
