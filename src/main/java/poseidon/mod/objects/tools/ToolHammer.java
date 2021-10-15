package poseidon.mod.objects.tools;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.test.ParticleSnake;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.helpers.diamondfind.Utiliser;
import poseidon.mod.util.helpers.diamondfinder.DiamondFinder;

public class ToolHammer extends ToolPickaxe {

	int tick = 0;
	
	public ToolHammer(String name, ToolMaterial mat) {
		super(name,mat);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return false;
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return false;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) entityIn;
			double x = p.posX;
			double y = p.posY;
			double z = p.posZ;
			float yaw = p.rotationYaw;
			float pit = p.rotationPitch;
			
			Vec3d vec = p.getLookVec();
			
			double xv = vec.x + x;
			double yv = vec.y + y + p.getEyeHeight();
			double zv = vec.z + z;

			float deltaZ = MathHelper.cos((float)(-yaw * 180.0/Math.PI - Math.PI)*2.0F);
			float deltaX = MathHelper.sin((float)(-yaw * 180.0/Math.PI - Math.PI)*2.0F);
			
			//worldIn.spawnParticle(EnumParticleTypes.END_ROD, xv, yv, zv, 0d, 0d, 0d);
			//worldIn.spawnParticle(EnumParticleTypes.FLAME, x + deltaX, y, z + deltaZ, 0d, 0d, 0d);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand h) {
		
//		for(double i = -5.0D; i < 5.0D; i += 0.1D) {
//			if(!worldIn.isRemote) ParticleUtil.slope((WorldServer)worldIn, playerIn.getPosition().north(10), i, 2);
//		}
		
//		BlockPos pos = p.getPosition();
//		double x = p.posX;
//		double y = p.posY;
//		double z = p.posZ;
//		
		double a = 3.0D;
		double b = 2.0D;
	
		double pi = PI;
		double step = pi/360;
		
		float yaw = p.rotationYaw;
		float pit = p.rotationPitch;

		
		//if(!w.isRemote) DStrangeEffect.shield(w, x, y, z, p, (1/4d));
		if(!w.isRemote) {
			//ParticleSnake.init(p.getPosition());

		}
		//Vectory.drawInPlane(p,w);

	
		
		return super.onItemRightClick(w, p, h);
	}
	
	private void diamond(World world, EntityPlayer player) {
		
		BlockPos pos = player.getPosition();
		Iterable<BlockPos> list = pos.getAllInBox(pos.north(10).east(10).up(6), pos.south(10).west(10).down(6));

		for(BlockPos check : list) {
			if(world.getBlockState(check) == Blocks.DIAMOND_ORE.getDefaultState()) {
				DiamondFinder.execute(world, player, check);
				Utiliser util = new Utiliser(player.getPosition(), check);

			}
		}
		
		
	}
	

	
	
	private void circle(EntityPlayer p, World w) {
		BlockPos pos = p.getPosition();
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ() + 5.0D;
		
		double yStep = 0.01D;
		double iStep = (2*PI)/360;
				
		for(double i = 0d; i < 2*PI; i += iStep) {
					
		  w.spawnParticle(EnumParticleTypes.END_ROD, 
		                  cos(i)+x, 
		                  y +i,              
		                  sin(i)+z, 
		                  0d, 0d, 0d);

		}

	}
	
	private void helix(EntityPlayer p, WorldServer w) {
		BlockPos pos = p.getPosition();
		double x = pos.getX();
		double y = pos.getY();
		double z = pos.getZ() + 5.0D;
		
		double yStep = 0.01D;
		double iStep = (2*PI)/360;
		double c = 0;
				System.out.println("Log");
		for(double i = 0d; i < 2*PI; i += iStep) {
					
		  w.spawnParticle(EnumParticleTypes.END_ROD, true,
		                  cos(i)+x, 
		                  y +i,              
		                  sin(i)+z, 
		                  0d, 0d, 0d);
		  
		  w.spawnParticle(EnumParticleTypes.END_ROD, true,
                  cos(i + PI)+x, 
                  y +i,              
                  sin(i + PI)+z, 
                  0d, 0d, 0d);
		  
		  
		 
			 if(i % (iStep*20) == 0) {
				 
				 double dX = x+cos(i) - (x + cos(i+PI));
				 double dZ = z+sin(i) - (z + sin(i+PI));
				 int l = 100;
				 double xStep = dX / l;
				 double zStep = dZ / l;
				 
				 for(int n = 0; n < l; n++) {
					 
					 w.spawnParticle(EnumParticleTypes.END_ROD, true, x+cos(i+PI)+xStep*n, y+i, z+sin(i+PI)+zStep*n, 0d, 0d, 0d);
					 
				 }
				 
			 }
			 c++;
		  
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(world.getBlockState(pos) == Blocks.OBSIDIAN.getDefaultState()) {
			world.destroyBlock(pos, true);
			player.getHeldItemMainhand().damageItem(1, player);
		}
		

		if(world.getBlockState(pos) == BlockInit.WET_MODSPONGE.getDefaultState()) {
			world.destroyBlock(pos, true);
			player.getHeldItemMainhand().damageItem(1, player);
		}
		
		if(world.getBlockState(pos) == BlockInit.BURNINGLAVASPONGE.getDefaultState()) {
			world.destroyBlock(pos, true);
			player.getHeldItemMainhand().damageItem(1, player);
		}
		
		if(world.getBlockState(pos) == Blocks.MAGMA.getDefaultState()) {
			world.destroyBlock(pos, false);
			if(!world.isRemote) {
				int c = Utilities.getRandom(2, 1);
				EntityItem item = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, new ItemStack(ItemInit.HOT_COALS, c));
				world.spawnEntity(item);
				player.getHeldItemMainhand().damageItem(1, player);
			}
		}
		
		return EnumActionResult.SUCCESS;
		
	}
	
	private double c(double a) {
		while(a > 1.0D) {
			a /= 10d;
		}
		while(a < -1.0D) {
			a /= 10d;
		}
		return a;
	}
	
}
