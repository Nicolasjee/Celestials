package poseidon.mod.objects.block.customsponge;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.objects.block.general.BlockBase;
import poseidon.mod.util.ParticleUtil;

public class BlockBurningLavaSponge extends BlockBase {

	public BlockBurningLavaSponge(String name) {
		super(name, Material.SPONGE, 0.0F, 0.5F, 0.5F, 0);
	}
	
	@Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!entityIn.isImmuneToFire() && entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
        }
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		
		PropertyBool WET = PropertyBool.create("wet");
		
		Block block = worldIn.getBlockState(pos).getBlock();
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		World w = worldIn;
		BlockPos[] sur = getSurroundings(pos, worldIn);
		boolean check = isWater(worldIn, sur);
		
		if(w.provider.getDimension() == 0 &&  (block.getDefaultState() == BlockInit.BURNINGLAVASPONGE.getDefaultState())) {
			
			if(check) {
				Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.BLOCK_LAVA_EXTINGUISH, 1.0F, 1.0F);
				if(!worldIn.isRemote) ParticleUtil.sponge((WorldServer) worldIn, pos);
				w.setBlockState(pos, BlockInit.LAVASPONGE.getDefaultState());
			}
			
		}
		
		
	}
	
	
	private BlockPos[] getSurroundings(BlockPos pos, World world) {
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		return new BlockPos[] {new BlockPos(x + 1.0D, y, z),
							   new BlockPos(x + 1.0D, y, z + 1.0D),
							   new BlockPos(x, y, z + 1.0D),
							   new BlockPos(x - 1.0D, y, z),
							   new BlockPos(x - 1.0D, y, z - 1.0D),
							   new BlockPos(x - 1.0D, y, z + 1.0D),
							   new BlockPos(x, y, z - 1.0D),
							   new BlockPos(x + 1.0D, y, z - 1.0D),
							   new BlockPos(x, y + 1.0D, z),
							   new BlockPos(x, y - 1.0D, z)
							   };
	}
	
	private boolean isWater(World world, BlockPos[] pos) {
		int a = 0;
		for(int i = 0; i < pos.length; i++) {
			if(world.getBlockState(pos[i]) == Blocks.WATER.getDefaultState()) {
				a++;
			}
		}
		
		if(a >= 4) return true;
		else return false;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
	}
	
}
