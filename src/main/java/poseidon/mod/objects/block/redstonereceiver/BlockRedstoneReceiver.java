package poseidon.mod.objects.block.redstonereceiver;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.expansiontable.TileEntityExpansionTable;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.objects.block.redstonereceiver.TileEntityRedstoneReceiver;
import poseidon.mod.util.Reference;

public class BlockRedstoneReceiver extends BlockBase implements ITileEntityProvider{

	
	public BlockRedstoneReceiver(String name) {
		super(name, Material.PISTON, 0.0F, 2.0F, 1.0F, 1);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityRedstoneReceiver) {
			
			TileEntityRedstoneReceiver t = (TileEntityRedstoneReceiver) worldIn.getTileEntity(pos);
			
			if(t.power > 0) {
				
				Main.proxy.dropItem(pos, worldIn, Items.REDSTONE, t.power);
				
			}
			
		}
		
	}
	

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(worldIn.getTileEntity(pos) != null) {
			if(worldIn.getTileEntity(pos) instanceof TileEntityRedstoneReceiver) {
				TileEntityRedstoneReceiver t = (TileEntityRedstoneReceiver) worldIn.getTileEntity(pos);


				//System.out.println("hitx: " + hitX + ",hity: " + hitY + ", hitz: " + hitZ);
				
				if(hitX > 0.35F && hitX < 0.65F
				&& hitY > 0.35F && hitY < 0.65F
				&& hitZ == 0.0F) {
					playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Power: " + TextFormatting.WHITE + t.power, new Object[0]), true);
					return true;
				}
				
				else if(hitX > 0.35F && hitX < 0.65F
				&& hitY > 0.35F && hitY < 0.65F
				&& hitZ == 1.0F) {
					playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Power: " + TextFormatting.WHITE + t.power, new Object[0]), true);
					return true;
				}
				
				else if(hitX == 1.0F
				&& hitY > 0.35F && hitY < 0.65F
				&& hitZ > 0.35F && hitZ < 0.65F) {
					playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Power: " + TextFormatting.WHITE + t.power, new Object[0]), true);
					return true;
				}
				
				else if(hitX == 0.0F
				&& hitY > 0.35F && hitY < 0.65F
				&& hitZ > 0.35F && hitZ < 0.65F) {
					playerIn.sendStatusMessage(new TextComponentTranslation(TextFormatting.GRAY + "Power: " + TextFormatting.WHITE + t.power, new Object[0]), true);
					return true;
				}
				
				else 
					 if (!worldIn.isRemote) {
						playerIn.openGui(Main.instance, Reference.GUI_REDSTONERECEIVER, worldIn, pos.getX(), pos.getY(), pos.getZ());
					}
				
			}
		}
		
		return false;
	}


	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRedstoneReceiver();
	}
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityRedstoneReceiver();
	}
	
	
}
