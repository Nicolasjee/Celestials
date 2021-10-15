package poseidon.mod.util.helpers.tracehelper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TraceSpawner {

	public static void execute(EntityPlayer player, World world) {
		
		List<Entity> nearbyEntities = getBox(world, player);
		List<BlockPos> traceEnds = getEyeHeightPositions(world, player, nearbyEntities);
		
		BlockPos pos = player.getPosition();
		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
		double e = (double) player.getEyeHeight();
		if(!world.isRemote) drawLines(traceEnds, nearbyEntities, new BlockPos(x,y + e,z), (WorldServer)world, player);
		
	}
	
	public static List<Entity> getBox(World worldIn, EntityPlayer player) {
		
		List<Entity> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));

		List<Entity> list1 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
		List<Entity> list2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
		List<Entity> list3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
		List<Entity> list4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
		
		List<Entity> list5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
		List<Entity> list6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
		List<Entity> list7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
		List<Entity> list8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
		
		List<Entity> general = new ArrayList<Entity>();
		general.addAll(list1); general.addAll(list2); general.addAll(list3); general.addAll(list4); general.addAll(list5); general.addAll(list6); general.addAll(list7); general.addAll(list8);
		
		return general;
	}
	
	public static List<BlockPos> getEyeHeightPositions(World world, EntityPlayer player, List<Entity> nearby) {
		
		List<BlockPos> list = new ArrayList<BlockPos>();
		
		for(int i = 0; i < nearby.size(); i++) {
			
			Entity entity = nearby.get(i);
			list.add(new BlockPos(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ));
			
		}
		
		
		return list;
		
	}
	
	public static void executeSpecialAttackEffect(EntityPlayer player, Entity entity) {
		
		
		
	}
	
	public static void drawLines(List<BlockPos> list, List<Entity> list2, BlockPos drawFrom, WorldServer server, EntityPlayer player) {
		
		for(int i = 0; i < list.size(); i++) {
			
			BlockPos drawTo = list.get(i);
			
			boolean isXfurther = (drawTo.getX() > drawFrom.getX());
			boolean isYhigher = (drawTo.getY() > drawFrom.getY());
			boolean isZfurther = (drawTo.getZ() > drawFrom.getZ());
			
			boolean xSame = (drawTo.getX() == drawFrom.getX());
			boolean ySame = (drawTo.getY() == drawFrom.getY());
			boolean zSame = (drawTo.getZ() == drawFrom.getZ());
			
			if(!(list2.get(i) instanceof EntityItem)) executeDraw(server, isXfurther, isYhigher, isZfurther, xSame, ySame, zSame, player, list2.get(i));
			
		}
		
	}
	
	public static void executeDraw(WorldServer server, boolean xF, boolean yH, boolean zF, boolean xS, boolean yS, boolean zS, EntityPlayer player, Entity entity) {
		
		double x = player.posX; double y = player.posY + player.getEyeHeight() - 0.4D; double z = player.posZ;
		double xi = entity.posX; double yi = entity.posY + entity.getEyeHeight(); double zi = entity.posZ;
		
		int amount = 1000;
		
		double xDist = Math.abs(x - xi);
		double yDist = Math.abs(y - yi);
		double zDist = Math.abs(z - zi);
		
		double xStep = xDist / amount;
		double yStep = yDist / amount;
		double zStep = zDist / amount;
		
		double i = 0.0D;
		
		while(i < amount) {
			
			server.spawnParticle(EnumParticleTypes.END_ROD, true, x + x(xF, xStep) * i, y + y(yH, yStep) * i, z + z(zF, zStep) * i, 1, 0.0D, 0.0D, 0.0D, 0, 0);
			i++;
		}
		
	}
	
	public static double x(boolean xF, double step) {
		if(xF) return step;
		else if(!xF) return -step;
		else {
			System.out.println("Oopsie");
			return 0.1D;
		}
	}
	
	public static double y(boolean xF, double step) {
		if(xF) return step;
		else if(!xF) return -step;
		else {
			System.out.println("Oopsie");
			return 0.1D;
		}
	}
	
	public static double z(boolean xF, double step) {
		if(xF) return step;
		else if(!xF) return -step;
		else {
			System.out.println("Oopsie");
			return 0.1D;
		}
	}
	
}
