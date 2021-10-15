package poseidon.mod.objects.block.netherreactor.nethercore.crates;

import java.util.Random;

import com.mojang.realmsclient.dto.RealmsServer.WorldType;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.objects.block.general.BlockBase;

public class NetherCrateBase extends BlockBase implements ITileEntityProvider {

	public NetherCrateBase(String name, Material material, float light, float hard, float res, int harvest) {
		super(name, material, light, hard, res, harvest);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCrate();
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityCrate();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(worldIn.getTileEntity(pos) instanceof TileEntityCrate && !worldIn.isRemote) {
			TileEntityCrate c = (TileEntityCrate) worldIn.getTileEntity(pos);
			String setString = getRandomType(c);
			while(setString == "") {
				setString = getRandomType(c);
			}
			if(c.setType) c.type = setString;
			if(c.locked) c.locked = false;
		}
		
		return true;
	}
	
	private String getRandomType(TileEntityCrate tile) {
		
		Random rn = new Random();
		int maximum = 750;
		int minimum = 0;
		int range = maximum - minimum;		
		int b = rn.nextInt(range) + minimum;

		if(b >= 0 && b < 50) return tile.ARMOR;
		if(b >= 50 && b < 100) return tile.TOOLS;
		if(b >= 100 && b < 200) return tile.FARM;
		if(b >= 200 && b < 300) return tile.TRANSPORTATION;
		if(b >= 300 && b < 400) return tile.CONSUMABLES;
		if(b >= 400 && b < 500) return tile.REDSTONE;
		if(b >= 500 && b < 550) return tile.LEGENDARY;
		if(b >= 550 && b < 620) return tile.RESOURCES;
		if(b >= 620 && b < 770) return tile.MATERIALS;
		
		
		return "";
	}
	
}
