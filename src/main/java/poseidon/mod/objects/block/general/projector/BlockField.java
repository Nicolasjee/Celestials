package poseidon.mod.objects.block.general.projector;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockField extends Block implements IHasModel {

	public BlockField(String name, Material material, float light, float hard, float res, int harvest) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setLightLevel(light);
		setHardness(hard);
		setResistance(res);
		
		setBlockUnbreakable();
		setHarvestLevel("pickaxe", harvest);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	//
	@Override
	public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}
	
	@Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	//
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityField();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	
}
