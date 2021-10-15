package poseidon.mod.world.gen;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import poseidon.mod.entity.entities.EntityDemon;
import poseidon.mod.util.interfaces.IStructure;

public class NetherEntityGen extends WorldGenerator implements IStructure {
	 Random r2 =new Random();
		
	  
	    int r;
		@Override
		public boolean generate(World w, Random rand, BlockPos position) {

			WorldServer worldserver = (WorldServer) w;
			MinecraftServer minecraftserver = w.getMinecraftServer();
			TemplateManager templatemanager = worldServer.getStructureTemplateManager();
			//Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MODID, "lucifer_house"));
			
			double x = position.getX(); double y = position.getY(); double z = position.getZ();
//			BlockPos pos = new BlockPos(x + 2.0D, y + 1.0D, z + 2.0D);
//			world.setBlockState(pos, Blocks.CHEST.getDefaultState());
//			TileEntity tileentity = world.getTileEntity(pos);
//			((TileEntityChest)tileentity).setLootTable(LootTableList.CHESTS_END_CITY_TREASURE, rand.nextLong());
//			
			
			EntityWitherSkeleton entity = new EntityWitherSkeleton(w); EntityWitherSkeleton entity1 = new EntityWitherSkeleton(w); EntityWitherSkeleton entity2 = new EntityWitherSkeleton(w); EntityWitherSkeleton entity3 = new EntityWitherSkeleton(w);
			EntityBlaze blaze = new EntityBlaze(w); EntityBlaze blaze1 = new EntityBlaze(w); EntityBlaze blaze2 = new EntityBlaze(w); EntityBlaze blaze3 = new EntityBlaze(w);
			EntityDemon z0 = new EntityDemon(w); EntityDemon z1 = new EntityDemon(w); EntityDemon z2 = new EntityDemon(w); EntityDemon z3 = new EntityDemon(w);
			EntityDemon z4 = new EntityDemon(w); EntityDemon z5 = new EntityDemon(w);EntityDemon z6 = new EntityDemon(w);EntityDemon z7 = new EntityDemon(w);
			EntityDemon z8 = new EntityDemon(w); EntityDemon z9 = new EntityDemon(w); EntityDemon z10 = new EntityDemon(w); EntityDemon z11 = new EntityDemon(w);
			
			entity.setLocationAndAngles(x, y, z + 2.0D, entity.rotationYaw, entity.rotationPitch); entity1.setLocationAndAngles(x, y, z - 2.0D, entity.rotationYaw, entity.rotationPitch);
			entity2.setLocationAndAngles(x - 2.0D, y, z, entity.rotationYaw, entity.rotationPitch); entity3.setLocationAndAngles(x + 2.0D, y, z, entity.rotationYaw, entity.rotationPitch);
			
			blaze.setLocationAndAngles(x + 2.0D, y + 2.0D, z + 2.0D, entity.rotationYaw, entity.rotationPitch); blaze1.setLocationAndAngles(x - 2.0D, y + 2.0D, z - 2.0D, entity.rotationYaw, entity.rotationPitch);
			blaze2.setLocationAndAngles(x - 2.0D, y + 2.0D, z + 2.0D, entity.rotationYaw, entity.rotationPitch); blaze3.setLocationAndAngles(x + 2.0D, y + 2.0D, z - 2.0D, entity.rotationYaw, entity.rotationPitch);
			
			z0.setLocationAndAngles(x + 2.0D, gH(x + 2.0D, z + 3.0D, w), z + 3.0D, z0.rotationYaw, z0.rotationPitch); z1.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z + 2.0D, w), z + 2.0D, z1.rotationYaw, z1.rotationPitch);
			z2.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z + 2.0D, w), z + 2.0D, z2.rotationYaw, z2.rotationPitch); z3.setLocationAndAngles(x - 2.0D, gH(x - 2.0D, z + 3.0D, w), z + 3.0D, z3.rotationYaw, z3.rotationPitch);
			z4.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z + 3.0D, w), z + 3.0D, z4.rotationYaw, z4.rotationPitch); z5.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z + 3.0D, w), z + 2.0D, z5.rotationYaw, z5.rotationPitch);
			z6.setLocationAndAngles(x - 2.0D, gH(x - 2.0D, z - 3.0D, w), z - 3.0D, z6.rotationYaw, z6.rotationPitch); z7.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z - 3.0D, w), z - 3.0D, z7.rotationYaw, z7.rotationPitch);
			z8.setLocationAndAngles(x - 3.0D, gH(x - 3.0D, z - 2.0D, w), z - 2.0D, z8.rotationYaw, z8.rotationPitch); z9.setLocationAndAngles(x + 2.0D, gH(x + 2.0D, z - 3.0D, w), z - 3.0D, z9.rotationYaw, z9.rotationPitch);
			z10.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z - 3.0D, w), z - 3.0D, z10.rotationYaw, z10.rotationPitch); z11.setLocationAndAngles(x + 3.0D, gH(x + 3.0D, z - 2.0D, w), z - 2.0D, z11.prevRotationYaw, z11.prevRotationPitch);
				
				
			w.spawnEntity(entity); w.spawnEntity(entity1); w.spawnEntity(entity2); w.spawnEntity(entity3);
			w.spawnEntity(blaze1); w.spawnEntity(blaze); w.spawnEntity(blaze3); w.spawnEntity(blaze2);
			w.spawnEntity(z0); w.spawnEntity(z1); w.spawnEntity(z2); w.spawnEntity(z3); w.spawnEntity(z4); w.spawnEntity(z5); w.spawnEntity(z6); w.spawnEntity(z7);
			w.spawnEntity(z8); w.spawnEntity(z9); w.spawnEntity(z10); w.spawnEntity(z11);
			
			((EntityLiving)entity).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);((EntityLiving)entity1).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity1)), (IEntityLivingData)null);
			((EntityLiving)entity).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity2)), (IEntityLivingData)null);((EntityLiving)entity).onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entity3)), (IEntityLivingData)null);
		
			return true;
			
	}
	
	private static double gH(double x, double z, World w) {
		return (double) w.getHeight((int)x, (int)z);
	}
}
