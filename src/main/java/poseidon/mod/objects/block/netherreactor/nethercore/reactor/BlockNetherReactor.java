package poseidon.mod.objects.block.netherreactor.nethercore.reactor;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.util.Reference;

public class BlockNetherReactor extends BlockBase implements ITileEntityProvider {

	public BlockNetherReactor(String name, Material material, float light, float hard, float res, int harvest) {
		super(name, material, light, hard, res, harvest);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNetherReactor();
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityNetherReactor();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack main = playerIn.getHeldItemMainhand();
		
		if(playerIn.isSneaking()) {
			setStructure(worldIn, pos);
			
			if(!worldIn.isRemote && worldIn.getTileEntity(pos) instanceof TileEntityNetherReactor) {
				TileEntityNetherReactor s = (TileEntityNetherReactor) worldIn.getTileEntity(pos);
				s.setInventorySlotContents(0, new ItemStack(Items.BLAZE_ROD));
				s.setInventorySlotContents(1, new ItemStack(Items.MAGMA_CREAM));
				s.setInventorySlotContents(2, new ItemStack(Items.GHAST_TEAR));
				s.setInventorySlotContents(3, new ItemStack(Items.GLOWSTONE_DUST));
				s.setInventorySlotContents(4, new ItemStack(Items.ENDER_EYE));
			}
			
			return true;
		}
		
		if(!worldIn.isRemote) {
			playerIn.openGui(Main.instance, Reference.GUINETHER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	private void setStructure(World world, BlockPos pos) {
		
		BlockPos pillar1 = pos.down().north().east();
		BlockPos pillar2 = pillar1.west(2);
		BlockPos pillar3 = pillar2.south(2);
		BlockPos pillar4 = pillar3.east(2);
		
		BlockPos[] nether = new BlockPos[] {pillar1.up(), pillar1, pillar1.up(2),
				                            pillar2.up(), pillar2, pillar2.up(2),
											pillar3.up(), pillar3, pillar3.up(2),
											pillar4.up(), pillar4, pillar4.up(2) };
		
		BlockPos[] cobble = new BlockPos[] {pos.down(), pos.down().north(), pos.down().south(), pos.down().east(), pos.down().west(),
											pos.up(), pos.up().north(), pos.up().south(), pos.up().east(), pos.up().west()};
		
		for(int i = 0; i < nether.length; i++) {
			world.setBlockState(nether[i], BlockInit.NETHER_STONE.getDefaultState());
		}
		
		for(int i = 0; i < cobble.length; i++) {
			world.setBlockState(cobble[i], Blocks.COBBLESTONE.getDefaultState());
		}
		
	}
	
}
