package poseidon.mod.objects.block.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.block.general.tileHelpers.TileEntityShield;
import poseidon.mod.objects.block.netherreactor.nethercore.helpers.ReactorStageTime;
import poseidon.mod.util.ParticleUtil;
import poseidon.mod.util.interfaces.IHasModel;

public class BlockShield extends Block implements IHasModel, ITileEntityProvider {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockShield(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.ARISTOISITEMS);
		
		setLightLevel(0.0F);
		setHardness(1500.0F);
		setResistance(50.0F);
		
		setHarvestLevel("pickaxe", 2);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		for(int i = 0; i < 16; i++) {
			if(worldIn.getBlockState(pos.up(i)).getBlock() == BlockInit.SHIELDAIR.getDefaultState()) {
				worldIn.setBlockState(pos.up(i), Blocks.AIR.getDefaultState());
			}
		}
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(worldIn.isRemote) return;
		Random rand = new Random();
		
		EnumFacing facing = worldIn.getBlockState(pos).getValue(FACING);
		
	    boolean open = false;
	    TileEntity te = worldIn.getTileEntity(pos);
	    TileEntityShield s = null;
		
		if(te instanceof TileEntityShield) {
			s = (TileEntityShield) worldIn.getTileEntity(pos);
			open = s.open || s.subOpen;
			//System.out.println("s.open: " + s.open + ", sub: " + s.subOpen);
		}

	    boolean kC = (hasMatchingKeycard(entityIn) && hasMatchingKeyCode(entityIn, pos, worldIn));
				
		double x = entityIn.motionX;
		double z = entityIn.motionZ;
		double y = 0.2F;
		
		if(open) return;
		
		//Shield is not open && player has no card
		if(!open) {
			//System.out.println("henlo");
			if(facing == EnumFacing.SOUTH && !kC) entityIn.setVelocity(-x, y, 2.0F);
			if(facing == EnumFacing.NORTH &&!kC) entityIn.setVelocity(-x, y, -2.0F);
			if(facing == EnumFacing.EAST && !kC) entityIn.setVelocity(2.0F, y, -z);
			if(facing == EnumFacing.WEST && !kC) entityIn.setVelocity(-2.0F, y, -z);
		}
		
		
		if(!open && kC && !worldIn.isRemote) {
			
			//DISABLE DOOR
			//setExitParticles(worldIn, pos, worldIn.getBlockState(pos), entityIn);
			
			//getting necessary vars to communicate to the tileentity
			BlockPos parent = getParent(worldIn, pos);
			EnumFacing f = worldIn.getBlockState(parent).getValue(FACING);
			List<BlockPos> list = getParents(worldIn, parent, facing);
			BlockPos middle = getMiddle(list);
			
			//The tileentity needs these. Now we need to activate the tileentity
			double[] particlePositionsFrom = getPositions(worldIn, parent, f, 1);
			double[] particlePositionsTo = getPositions(worldIn, parent, f, 2);
			
			//Checking if the tileentity has been open / subopen
			//			  neighbours are open / subOpen
			
			//parents filtered
			if(s.open || s.subOpen) return;
			
			//neighbours filtered
			for(int i = 0; i < list.size(); i++) {
				if(worldIn.getTileEntity(list.get(i)) instanceof TileEntityShield) {
					TileEntityShield tempS = (TileEntityShield) worldIn.getTileEntity(list.get(i));
					if(tempS.open || tempS.subOpen) return;
				}
			}
			
			
			s.s = particlePositionsFrom;
			s.t = particlePositionsTo;
			s.countDown = 300;
			s.open = true;
			
			for(int i = 0; i < list.size(); i++) {
				//																	  getting rid of parent
				if(worldIn.getTileEntity(list.get(i)) instanceof TileEntityShield && list.get(i) != middle) {
					TileEntityShield tempS = (TileEntityShield) worldIn.getTileEntity(list.get(i));
					tempS.countDown = 300;
					tempS.subOpen = true;
				}
			}
			
				
			
		}
		
	}
	
	private boolean hasMatchingKeyCode(Entity entity, BlockPos pos, World world) {
		
		if(entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack stack = player.getHeldItemMainhand();
			TileEntity te = world.getTileEntity(pos);
			
			if(te instanceof TileEntityShield && stack.getItem() == ItemInit.KEYCARD && stack.hasTagCompound()) {
				
				TileEntityShield shield = (TileEntityShield) te;
				NBTTagCompound nbt = stack.getTagCompound();
				
				if(nbt.hasKey("Codes") && shield.hasCodeLinked) {
					
					//System.out.println("PSM: ShieldHas: " + shield.hasCodeLinked + ", keycode: " + shield.linkedCode + ", keycard: " + nbt.getInteger("Keycode"));
					if(shield.linkedCode == nbt.getIntArray("Codes")[0]) return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	

	
	private boolean hasMatchingKeycard(Entity entity) {
		if(entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) entity;
			ItemStack stack = player.getHeldItemMainhand();
			
			if(stack.hasTagCompound()) {
				//System.out.println("Haskeycard");
				return true;
			}
			
		}
		
		return false;
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityShield();
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		
		IBlockState north = worldIn.getBlockState(pos.north());
		IBlockState south = worldIn.getBlockState(pos.south());
		IBlockState west = worldIn.getBlockState(pos.west());
		IBlockState east = worldIn.getBlockState(pos.east());
		EnumFacing face = (EnumFacing) state.getValue(FACING);
		
		if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
		else if(face == EnumFacing.SOUTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.NORTH;
		else if(face == EnumFacing.WEST && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.EAST;
		else if(face == EnumFacing.EAST && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.WEST;
		
		worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(active) worldIn.setBlockState(pos, BlockInit.SHIELDBLOCK.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		else worldIn.setBlockState(pos, BlockInit.SHIELDBLOCK.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		
		if(tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
		
	}

	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack stack = playerIn.getHeldItemMainhand();
		ItemStack off = playerIn.getHeldItemOffhand();
		
		if(stack.getItem() == ItemInit.KEYCARD) {
			
			TileEntity tile = worldIn.getTileEntity(pos);
			
			if(stack.hasTagCompound() && tile instanceof TileEntityShield) {
				
				NBTTagCompound nbt = stack.getTagCompound();
				TileEntityShield shield = (TileEntityShield) tile;
				
				if(!shield.hasCodeLinked && nbt.hasKey("Codes")) {
					System.out.println("Linked: " + nbt.getIntArray("Codes")[0]);
					shield.linkedCode = nbt.getIntArray("Codes")[0];
					shield.hasCodeLinked = true;
					return true;
				}
				
				if(shield.hasCodeLinked) {
					Main.proxy.playSoundBlock(pos, worldIn, SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
					playerIn.getHeldItemMainhand().shrink(1);
					if(!worldIn.isRemote) InventoryHelper.spawnItemStack(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(ItemInit.KEYCARD));
				}
				
				
			}
			
		}
		
		if(stack.getItem() instanceof ItemPickaxe && off.getItem() == ItemInit.KEYCARD) {
			
			if(off.hasTagCompound() && off.getTagCompound().hasKey("Codes")) {
				
				NBTTagCompound nbt = off.getTagCompound();
				
				TileEntity tile = worldIn.getTileEntity(pos);
				
				if(off.hasTagCompound() && tile instanceof TileEntityShield) {
				
					TileEntityShield shield = (TileEntityShield) tile;
					
					if(nbt.getIntArray("Codes")[0] == shield.linkedCode && shield.hasCodeLinked) {
						worldIn.destroyBlock(pos, true);
					}
				
				}
				
			}
			
		}
		
		return true;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}


	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityShield();
	}
	
	
	private void setExitParticles(World world, BlockPos pos, IBlockState state, Entity e) {
		
		BlockPos parent = getParent(world, pos);
		EnumFacing facing = world.getBlockState(parent).getValue(FACING);
		List<BlockPos> list = getParents(world, parent, facing);
		
		BlockPos middle = getMiddle(list);
		
		double[] particlePositionsFrom = getPositions(world, parent, facing, 1);
		double[] particlePositionsTo = getPositions(world, parent, facing, 2);
		
		connect(particlePositionsFrom, particlePositionsTo, (WorldServer)world);
		
		
		for(int i = 0; i < list.size(); i++) {
			ParticleUtil.highlightBlock((WorldServer)world, list.get(i), 0.25D, EnumParticleTypes.PORTAL);
			//if(!(world.getBlockState(list.get(i)) == BlockInit.SHIELDBLOCK.getDefaultState())) list.remove(i);
		}
		
	}
	
	private BlockPos getMiddle(List<BlockPos> list) {
		
		int xMiddle=0;
		int zMiddle=0;
		int size = list.size();		
		int y = 0;
		
		for(int i = 0; i < list.size(); i++) {
			BlockPos pos = list.get(i);
			
			xMiddle += pos.getX();
			zMiddle += pos.getZ();
			y = pos.getY();
		}
		
		return new BlockPos((xMiddle)/size, y, (zMiddle)/size);
		
	}
	
	private static void connect(double[] f, double[] t, WorldServer server) {
		
		for(int i = 1; i < 4; i++) {
			
			int tick = 0;
			
			double dX = -x(i, f) + x(i, t);
			double dZ = -z(i, f) + z(i, t);
			double dY = -y(i, f) + y(i, t);
			
			double xStep = dX / ReactorStageTime.connectionParticles;
			double zStep = dZ / ReactorStageTime.connectionParticles;
			double yStep = dY / ReactorStageTime.connectionParticles;
			
			while(tick < ReactorStageTime.connectionParticles) {
				server.spawnParticle(EnumParticleTypes.PORTAL, true, x(i, f) + xStep * tick, y(i, f) + yStep * tick, z(i, f) + zStep * tick, 1, 0.0D, 0.0D, 0.0D, 0);
				tick++;
			}
		}
		
		
	}
	
	private double[] getPositions(World world, BlockPos parent, EnumFacing f, int c) {
		
		double x = parent.getX();
		double y = parent.getY();
		double z = parent.getZ();
		/*¨
		 *    P1    P2
		 *    
		 *    
		 *    P3    P4
		 *    
		 *    double[] P1x P1y P1z, P2x P2y P2z, P3.....
		 */
		if(f == EnumFacing.NORTH || f == EnumFacing.SOUTH) {
			if(c == 1) return new double[] {x - 1.0D, y + 5.0D, z + 0.5D, x + 2.0D, y + 5.0D, z + 0.5D,
					 		     x - 1.0D, y + 1.0D, z + 0.5D};
			
			if(c == 2) return new double[] {x + 2.0D, y + 5.0D, z + 0.5D, x + 2.0D, y + 1.0D, z + 0.5D,
		 		     x - 1.0D, y + 5.0D, z + 0.5D};
		}
		
		if(f == EnumFacing.WEST || f == EnumFacing.EAST) {
			if(c == 1) return new double[] {x + 0.5D, y + 5.0D, z + 2.0D, x + 0.5D, y + 5.0D, z - 1.0D,
					x + 0.5D, y + 5.0D, z + 2.0D,};

			if(c == 2) return new double[] {x + 0.5D, y + 1.0D, z + 2.0D, x + 0.5D, y + 1.0D, z - 1.0D,
					x + 0.5D, y + 5.0D, z - 1.0D,};
		}
		
		
		System.out.println("facing not recognized: " + f + ", possible that c can be wrong too: " + c);
		return new double[] {};
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
	
	
	private BlockPos getParent(World world, BlockPos pos) {
		for(int i = 1; i < 6; i++) {
			//System.out.println("Block: " + world.getBlockState(pos.down(i)));
			if(world.getBlockState(pos.down(i)).getBlock() == BlockInit.SHIELDBLOCK) {
				return pos.down(i);
			}
		}
		
		return pos;
	}
	
	public static double x(int num, double[] t) {
		if(num == 1) return t[0];
		if(num == 2) return t[3];
		if(num == 3) return t[6];
		if(num == 4) return t[9];
		if(num == 5) return t[12];
		if(num == 6) return t[15];
		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return t[0];
		}
	}
	
	public static double y(int num, double[] t) {
		if(num == 1) return t[1];
		if(num == 2) return t[4];
		if(num == 3) return t[7];
		if(num == 4) return t[10];
		if(num == 5) return t[13];

		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return t[1];
		}
	}

	public static double z(int num, double[] z) {
		if(num == 1) return z[2];
		if(num == 2) return z[5];
		if(num == 3) return z[8];
		if(num == 4) return z[11];
		if(num == 5) return z[14];
		else {
			System.out.println("Num was not applicable:" + num + " returned first element" );
			return z[0];
		}
	}
	
	
}
