package poseidon.mod.objects.block.netherreactor.nethercore.handlers;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import poseidon.mod.init.BlockInit;

public class TileEntitySpawnerMobsNether extends TileEntity implements ITickable {

	int sum = 0;
	public int spawnRate = 6;
	int tick = 0;
	public int rangeX = 3;
	public int rangeZ = 3;
	public int delay = 50;
	public String[] spawns = new String[] {};
	
	@Override
	public void update() {

		if(timeOK() && !world.isRemote && sum < spawnRate && spawns.length == 4) {
			
			int check = getNum();
			
			switch(check) {
				case 3: 
					spawnEntity(spawns [2]);	
					break;
				case 1: 
					spawnEntity(spawns[0]);
					break;
				case 4: 
					spawnEntity(spawns[3]);
					break;
				case 2: 
					spawnEntity(spawns[1]);
					break;
			}
			

			sum++;
			
		}
		
		if(sum == spawnRate) this.world.setBlockState(this.pos, BlockInit.UNBREAKABLE_NETHERRACK.getDefaultState());
		
		tick++;
	}
	

	private void spawnEntity(String kind) {
		BlockPos spawn = new BlockPos(0,0,0);

		while(spawn.getY() == 0 && spawn.getZ() == 0 && spawn.getX() == 0) {
			spawn = getNextPos();
			if(isSpawnValid(spawn)) {
				spawnEntitiesWithString(kind, spawn);
				} else {
				spawn = new BlockPos(0,0,0);
			}
		}
		
	}
	
	private boolean timeOK() {
		if(tick % delay == 0) return true;
		else return false;
	}
	
	private void spawnZombie(BlockPos spawn, WorldServer world) {
		EntityHusk zombie = new EntityHusk(world);
		double x = spawn.getX() + 0.5D; double y = spawn.getY(); double z = spawn.getZ() + 0.5D;
		zombie.setLocationAndAngles(x, y, z + 2.0D, zombie.rotationYaw, zombie.rotationPitch);
		zombie.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
		world.spawnEntity(zombie);
	}
	
	private void spawnSpider(BlockPos spawn, WorldServer world) {
		EntityWitherSkeleton zombie = new EntityWitherSkeleton(world);
		double x = spawn.getX() + 0.5D; double y = spawn.getY(); double z = spawn.getZ() + 0.5D;
		zombie.setLocationAndAngles(x, y, z + 2.0D, zombie.rotationYaw, zombie.rotationPitch);
		zombie.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
		world.spawnEntity(zombie);
	}
	
	private void spawnPig(BlockPos spawn, WorldServer world) {
		EntityPigZombie zombie = new EntityPigZombie(world);
		double x = spawn.getX() + 0.5D; double y = spawn.getY(); double z = spawn.getZ() + 0.5D;
		zombie.setLocationAndAngles(x, y, z + 2.0D, zombie.rotationYaw, zombie.rotationPitch);
		zombie.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));
		world.spawnEntity(zombie);
	}
	
	private void spawnBlaze(BlockPos spawn, WorldServer world) {
		EntityBlaze zombie = new EntityBlaze(world);
		double x = spawn.getX() + 0.5D; double y = spawn.getY(); double z = spawn.getZ() + 0.5D;
		zombie.setLocationAndAngles(x, y, z + 2.0D, zombie.rotationYaw, zombie.rotationPitch);
		world.spawnEntity(zombie);
	}
	
	private void spawnCaveSpider(BlockPos spawn, WorldServer world) {
		EntityCaveSpider zombie = new EntityCaveSpider(world);
		double x = spawn.getX() + 0.5D; double y = spawn.getY(); double z = spawn.getZ() + 0.5D;
		zombie.setLocationAndAngles(x, y, z + 2.0D, zombie.rotationYaw, zombie.rotationPitch);
		world.spawnEntity(zombie);
	}
	
	private void spawnCreeper(BlockPos spawn, WorldServer world) {
		EntityCreeper zombie = new EntityCreeper(world);
		double x = spawn.getX() + 0.5D; double y = spawn.getY(); double z = spawn.getZ() + 0.5D;
		zombie.setLocationAndAngles(x, y, z + 2.0D, zombie.rotationYaw, zombie.rotationPitch);
		world.spawnEntity(zombie);
	}
	
	private void spawnEntitiesWithString(String s, BlockPos spawn) {
		if(s == "zombie") spawnZombie(spawn, (WorldServer) world);
		if(s == "skely") spawnSpider(spawn, (WorldServer) world);
		if(s == "pig") spawnPig(spawn, (WorldServer) world);
		if(s == "blaze") spawnBlaze(spawn, (WorldServer) world);
		if(s == "creeper") spawnCreeper(spawn, (WorldServer)world);
		if(s == "cavespider") spawnCaveSpider(spawn, (WorldServer)world);
	}
	
	private BlockPos getNextPos() {
		int x = getX() + this.pos.getX();
		int z = getZ() + this.pos.getZ();
		int y = (int) (this.pos.getY() - 3);
		
		while(!isSpawnValid(new BlockPos(x,y,z))) {
			y += 1;
		}
		
		return new BlockPos(x, y, z);
	}
	
	private int getNum() {
		Random rn = new Random();
		int maximum = 100;
		int minimum = 0;
		int range = maximum - minimum + 1;		
		int b = rn.nextInt(range) + minimum;	
		
		if(b < 40) return 1;
		if(b < 60 && b >= 40) return 2;
		if(b < 70 && b >= 60) return 3;
		if(b <= 100 && b >= 70) return 4;
		else return 1;
	}
	
	private int getX() {
		Random rn = new Random();
		int maximum = rangeX;
		int minimum = -rangeX;
		int range = maximum - minimum + 1;		
		return rn.nextInt(range) + minimum;	
	}

	private int getZ() {
		Random rn = new Random();
		int maximum = rangeZ;
		int minimum = -rangeZ;
		int range = maximum - minimum + 1;		
		return rn.nextInt(range) + minimum;
	}
	
	private int getY() {
		Random rn = new Random();
		int maximum = 3;
		int minimum = -3;
		int range = maximum - minimum + 1;		
		return rn.nextInt(range) + minimum;
	}
	
	private boolean isSpawnValid(BlockPos p) {
		if(world.getBlockState(p.down()).getBlock() != Blocks.AIR && world.getBlockState(p).getBlock() == Blocks.AIR && world.getBlockState(p.up()).getBlock() == Blocks.AIR ) 
			return true;
			else return false;
	}
	

}
