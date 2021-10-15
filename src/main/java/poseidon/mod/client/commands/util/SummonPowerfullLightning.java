package poseidon.mod.client.commands.util;

import java.util.List;

import net.minecraft.command.CommandEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SummonPowerfullLightning extends CommandEffect {

	public SummonPowerfullLightning() {
		
	}
	
	public static void summon(EntityPlayer player) {
		
		World worldIn = player.world;
		
		if(!worldIn.isRemote) {
			List<Entity> playerList = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> playerList2 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ + 20.0D));
			List<Entity> playerList3 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			List<Entity> playerList4 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY+ 20.0D, player.posZ - 20.0D));
			
			List<Entity> playerList5 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> playerList6 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ + 20.0D));
			List<Entity> playerList7 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX + 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			List<Entity> playerList8 = worldIn.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.posX, player.posY, player.posZ,player.posX - 20.0D, player.posY- 20.0D, player.posZ - 20.0D));
			
			for(int i = 0; i < playerList.size(); i++) {
				Entity entity = playerList.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList);
			}
			for(int i = 0; i < playerList2.size(); i++) {
				Entity entity = playerList2.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList2);
			}
			for(int i = 0; i < playerList3.size(); i++) {
				Entity entity = playerList3.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList3);
			}
			for(int i = 0; i < playerList4.size(); i++) {
				Entity entity = playerList4.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList4);
			}
			
			for(int i = 0; i < playerList5.size(); i++) {
				Entity entity = playerList5.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList5);
			}
			for(int i = 0; i < playerList6.size(); i++) {
				Entity entity = playerList6.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList6);
			}
			for(int i = 0; i < playerList7.size(); i++) {
				Entity entity = playerList7.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList7);
			}
			for(int i = 0; i < playerList8.size(); i++) {
				Entity entity = playerList8.get(i);
				BlockPos pos = entity.getPosition();
				Entity bolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
				player.world.addWeatherEffect(bolt);
				System.out.println(playerList8);
			}
		
		}
		
	}
	
}
