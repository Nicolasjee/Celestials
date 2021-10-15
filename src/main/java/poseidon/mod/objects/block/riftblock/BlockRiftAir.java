package poseidon.mod.objects.block.riftblock;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.client.commands.util.Teleport;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockRiftAir extends Block implements IHasModel {

	public BlockRiftAir(String name) {
		super(Material.AIR);
		setUnlocalizedName(name);
		setRegistryName(name);

		setLightLevel(9.0F);
		this.setTickRandomly(true);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		
		//WandAbility.earth3(worldIn, pos);
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		//WandAbility.earth3(worldIn, pos);
		return true;
	}


	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean isCollidable() {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos col, IBlockState state, Entity entityIn) {
		boolean parent = false;
		BlockPos pos = null;
		//Check if no parent:
		for(int i = 1; i < 6; i++) {
			if(worldIn.getBlockState(col.down(i)).getBlock() == BlockInit.RIFT_BLOCK) {
				parent = true;
				pos = col.down(i);
			}
		}
		
		if(!parent) worldIn.setBlockState(col, Blocks.AIR.getDefaultState());
		
		if(pos != null && worldIn.getTileEntity(pos) instanceof TileEntityRiftTeleporter) {
			TileEntityRiftTeleporter rift = (TileEntityRiftTeleporter) worldIn.getTileEntity(pos);
			if(rift.init && !worldIn.isRemote && entityIn instanceof EntityPlayer && rift.isPowered) {
				
				if(rift.dimension == entityIn.dimension) {
					((EntityPlayer)entityIn).setPosition(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					((EntityPlayer)entityIn).attemptTeleport(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					ParticleUtil.riftOpening((WorldServer)worldIn, new BlockPos(rift.x, rift.y + 1.0D, rift.z), EnumParticleTypes.DRAGON_BREATH);
					((EntityPlayer)entityIn).setPositionAndUpdate(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					return;
				}
				
				if(rift.dimension != entityIn.dimension) {
					EntityPlayer player = (EntityPlayer)entityIn;
					EntityPlayerMP mp = (EntityPlayerMP) player;
					MinecraftServer server = player.getEntityWorld().getMinecraftServer();
					WorldServer worldServer = server.getWorld(rift.dimension);
					
					if(worldServer == null || server == null) {
						return;
					}
					
					((EntityPlayer)entityIn).setPosition(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					((EntityPlayer)entityIn).attemptTeleport(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
					ParticleUtil.riftOpening((WorldServer)worldIn, new BlockPos(rift.x, rift.y + 1.0D, rift.z), EnumParticleTypes.DRAGON_BREATH);
					
					worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(mp, rift.dimension, new Teleport(worldServer, rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D));
					player.setPositionAndUpdate(rift.x + 0.5D, rift.y + 1.0D, rift.z + 0.5D);
				}
			}
		}
		
	}
	
	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
}
