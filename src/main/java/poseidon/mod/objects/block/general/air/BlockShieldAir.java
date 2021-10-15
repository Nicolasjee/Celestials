package poseidon.mod.objects.block.general.air;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.BlockHidden;
import poseidon.mod.objects.block.general.BlockShield;
import poseidon.mod.objects.block.general.tileHelpers.TileEntityShield;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.ReactorStageTime;
import poseidon.mod.util.ParticleUtil;

public class BlockShieldAir extends BlockHidden {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockShieldAir(String name) {
		super(name, Material.ROCK, 9.0F, 0.0F, 0.0F, 0);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		BlockPos parent = getParent(worldIn, pos);
		TileEntityShield t = null;
		boolean open = false;
		boolean subOpen = false;
		if(worldIn.isRemote) return;
		//Fail safe
		if(parent == pos) {
			removeBlock(worldIn, pos);
			return;
		}
		
		if(worldIn.getTileEntity(parent) instanceof TileEntityShield) {
			t = (TileEntityShield) worldIn.getTileEntity(parent);
			open = t.open;
			subOpen = t.subOpen;
		}
		
		if(!(open || subOpen) && entityIn instanceof EntityArrow) {
			entityIn.setVelocity(0, 0, 0);
			return;
		}
		
		//Block
		if(!(open || subOpen)) {
			if(worldIn.getBlockState(parent).getBlock() instanceof BlockShield && !hasKeycard(entityIn, worldIn, pos) && !worldIn.isRemote) {
				EnumFacing facing = worldIn.getBlockState(parent).getValue(FACING);
				setWall(worldIn, pos, facing);
	
				velocityFacing(entityIn, facing);
				
			}
		}
		
		if(!(open || subOpen)) {
			if(worldIn.getBlockState(parent).getBlock() instanceof BlockShield && hasKeycard(entityIn, worldIn, pos) && !worldIn.isRemote) {
				EnumFacing facing = worldIn.getBlockState(parent).getValue(FACING);
				
	
					
				List<BlockPos> wall = getWall(worldIn, pos, facing);
				//setExitParticles(worldIn, pos, state, entityIn);	
				
				for(int i = 0; i < wall.size(); i++) {
						
					IBlockState s = worldIn.getBlockState(wall.get(i));
					if(field(s, wall.get(i))) {
						worldIn.setBlockState(wall.get(i), Blocks.AIR.getDefaultState());
					}
						
				}
			}
		}
		
		//Success
		if((open || subOpen)) {
			if(worldIn.getBlockState(parent).getBlock() instanceof BlockShield && !worldIn.isRemote) {
				
				EnumFacing facing = worldIn.getBlockState(parent).getValue(FACING);
				
	
					
				List<BlockPos> wall = getWall(worldIn, pos, facing);
				//setExitParticles(worldIn, pos, state, entityIn);	
				
				for(int i = 0; i < wall.size(); i++) {
						
					IBlockState s = worldIn.getBlockState(wall.get(i));
					if(field(s, wall.get(i))) {
						worldIn.setBlockState(wall.get(i), Blocks.AIR.getDefaultState());
					}
						
				}
			}
		}
		
	}
	
	
	private void velocityFacing(Entity entityIn, EnumFacing f) {
		if(f == EnumFacing.WEST) entityIn.setVelocity(-2.00F, entityIn.motionY*1.02F, -entityIn.motionZ*1.04F);
		if(f == EnumFacing.EAST) entityIn.setVelocity(2.00F, entityIn.motionY*1.02F, -entityIn.motionZ*1.04F);
		if(f == EnumFacing.SOUTH) entityIn.setVelocity(-entityIn.motionX*1.04F, entityIn.motionY*1.02F, 2.0F);
		if(f == EnumFacing.NORTH) entityIn.setVelocity(-entityIn.motionX*1.04F, entityIn.motionY*1.02F, -2.0F);
	}
	
	private boolean field(IBlockState s, BlockPos pos) {
		if(s.getBlock() == BlockInit.FIELD1) return true;
		else if(s.getBlock() == BlockInit.FIELD2) return true;
		else if(s.getBlock() == BlockInit.FIELD3) return true;
		else if(s.getBlock() == BlockInit.FIELD4) return true;
		else return false;
	}
	
	private boolean isWall(World world, BlockPos pos, EnumFacing facing) {
		
		List<BlockPos> list = getWall(world, pos, facing);
		
		
		return true;
		
	}
	
	private void changeVelocity(Entity entityIn, BlockPos pos, World worldIn) {
		
		if(!(worldIn.getBlockState(pos.down(1)).getBlock() == BlockInit.SHIELDBLOCK ||
				worldIn.getBlockState(pos.down(2)).getBlock() == BlockInit.SHIELDBLOCK)) {
			
			float x = (float) entityIn.getLookVec().x;
		    float y = (float) entityIn.getLookVec().y;
		    float z = (float) entityIn.getLookVec().z;
			
		    EnumFacing f = entityIn.getHorizontalFacing();
		    
		    BlockPos parent = getParent(worldIn, pos);
		    
		    if(worldIn.getBlockState(parent).getBlock() instanceof BlockShield) {
				
				EnumFacing facing = worldIn.getBlockState(parent).getValue(FACING);
				//System.out.println("facing: " + facing + ", f: " + f + ", bool: " + f + " != " + facing.getOpposite() + ", -> " + (f == facing.getOpposite()));
				if(f == facing.getOpposite()) entityIn.setVelocity(-entityIn.motionX, entityIn.motionY, -entityIn.motionZ);
				
		    }
		   
		    
		}
		
	}
	
	private List<BlockPos> getParents(World world, BlockPos pos, EnumFacing facing) {
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		list.add(pos);
		
		if(facing == EnumFacing.EAST) {
			list.add(pos.north());
			list.add(pos.south());
		}
		
		if(facing == EnumFacing.NORTH) {
			list.add(pos.east());
			list.add(pos.west());
		}
		
		if(facing == EnumFacing.WEST) {
			list.add(pos.north());
			list.add(pos.south());
		}
		
		if(facing == EnumFacing.SOUTH) {
			list.add(pos.east());
			list.add(pos.west());
		}
	

		return list;
		
	}
	
	private List<BlockPos> getWall(World world, BlockPos pos, EnumFacing facing) {
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		if(facing == EnumFacing.EAST) {
			list.add(pos.west());
			list.add(pos.west());
			list.add(pos.west().south());
			list.addAll(getUpAndDown(list));
		}
		
		if(facing == EnumFacing.NORTH) {
			list.add(pos.south());
			list.add(pos.south().east());
			list.add(pos.south().west());
			list.addAll(getUpAndDown(list));
		}
		
		if(facing == EnumFacing.WEST) {
			list.add(pos.east());
			list.add(pos.east().north());
			list.add(pos.east().south());
			list.addAll(getUpAndDown(list));
		}
		
		if(facing == EnumFacing.SOUTH) {
			list.add(pos.north());
			list.add(pos.north().west());
			list.add(pos.north().east());
			list.addAll(getUpAndDown(list));
		}

		return list;
	}
	
	private List<BlockPos> getUpAndDown(List<BlockPos> pos) {
		List<BlockPos> list = new ArrayList<BlockPos>();
		for(int i = 0; i < pos.size(); i++) {
			BlockPos x = pos.get(i);
			list.add(x.up());
			list.add(x.down());
		}
		return list;
	}
	
	private void setWall(World world, BlockPos pos, EnumFacing facing) {
		
		if(facing == EnumFacing.EAST) {
			world.setBlockState(pos.west(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.west().north(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.west().south(), BlockInit.FIELD4.getDefaultState());
		}
		
		if(facing == EnumFacing.NORTH) {
			world.setBlockState(pos.south(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.south().east(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.south().west(), BlockInit.FIELD4.getDefaultState());
		}
		
		if(facing == EnumFacing.WEST) {
			world.setBlockState(pos.east(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.east().north(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.east().south(), BlockInit.FIELD4.getDefaultState());
		}
		
		if(facing == EnumFacing.SOUTH) {
			world.setBlockState(pos.north(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.north().west(), BlockInit.FIELD4.getDefaultState());
			world.setBlockState(pos.north().east(), BlockInit.FIELD4.getDefaultState());
		}
		
	}
	
	private BlockPos getParent(World world, BlockPos pos) {
		for(int i = 1; i < 6; i++) {
			//System.out.println("Block: " + world.getBlockState(pos.down(i)));
			if(world.getBlockState(pos.down(i)).getBlock() == BlockInit.SHIELDBLOCK) {
				return pos.down(i);
			}
		}
		
		return pos;
	}
	
	private boolean hasKeycard(Entity e, World world, BlockPos pos) {
		
		BlockPos parent = getParent(world, pos);
		
		if(e instanceof EntityPlayer && parent != pos) {
			
			EntityPlayer player = (EntityPlayer) e;
			ItemStack stack = player.getHeldItemMainhand();
			TileEntity tile = world.getTileEntity(parent);
			
			if(tile instanceof TileEntityShield && stack.hasTagCompound() && stack.getItem() == ItemInit.KEYCARD) {
				
				TileEntityShield shield = (TileEntityShield) tile;
				NBTTagCompound nbt = stack.getTagCompound();
				
				if(nbt.hasKey("Codes") && shield.hasCodeLinked) {
					
					if(nbt.getIntArray("Codes")[0] == shield.linkedCode) return true;
					else return false;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	
	private void removeBlock(World world, BlockPos pos) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
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
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
	
	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		System.out.println("Hit?");
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		System.out.println("Click?");
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
