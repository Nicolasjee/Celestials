package poseidon.mod.objects.block.customsponge;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.Utilities;

public class WetModSponge extends BlockBase {
	
	public WetModSponge(String name) {
		super(name, Material.SPONGE, 0.0F, 0.5F, 0.5F, 0);
	}
	
	//Dry In Nether
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		
		PropertyBool WET = PropertyBool.create("wet");
		
		Block block = worldIn.getBlockState(pos).getBlock();
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		World w = worldIn;
		if(w.provider.getDimension() == -1 &&  (block.getDefaultState() == BlockInit.WET_MODSPONGE.getDefaultState())) {
			
			
			Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.BLOCK_LAVA_EXTINGUISH, 1.0F, 1.0F);
			if(!worldIn.isRemote) ParticleUtil.sponge((WorldServer) worldIn, pos);
			w.setBlockState(pos, BlockInit.MODSPONGE.getDefaultState());
		}
		
		
	}
	
	/*
	@Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, BlockInit.WET_MODSPONGE.getDefaultState(), 10.0F, entityIn)) {
        	
        	if(fallDistance > 2.0F) {
        		
        		if(!worldIn.isRemote) {
        			
        			worldIn.destroyBlock(pos, false);
        			EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, new ItemStack(Item.getItemFromBlock(BlockInit.MODSPONGE)));
        			worldIn.spawnEntity(item);
        			
        		}
        		
        	}
        	
        }
    }
	
	//Wet sponge right clicked: put water in air
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		List<BlockPos> list = new ArrayList<BlockPos>();
		for(int k = -3; k < 4; k++) {
			for(int i = -3; i < 3; i++) {	
				for(int j = -3; j < 3; j++) {	
					list.add(new BlockPos(x + (double) i, y + (double) k, z + (double) j));
					if(worldIn.getBlockState(new BlockPos(x + (double) i, y + (double) k, z + (double) j)) == Blocks.AIR.getDefaultState()) {
						worldIn.setBlockState(new BlockPos(x + (double) i, y + (double) k, z + (double) j), Blocks.WATER.getDefaultState());
					}
				}
			}
		}
		
		return true;
	}*/
	
	public static List<BlockPos> bubble(World world, BlockPos pos) {
		return Utilities.getSpongeBlocks(pos);
	}
}
