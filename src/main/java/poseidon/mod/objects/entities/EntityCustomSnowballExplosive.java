package poseidon.mod.objects.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.networking.MessageChoke;

public class EntityCustomSnowballExplosive extends EntitySnowball {
	
	public int bounces = 0;
	
	 public EntityCustomSnowballExplosive(World worldIn)
	    {
	        super(worldIn);
	    }

	    public EntityCustomSnowballExplosive(World worldIn, EntityLivingBase throwerIn)
	    {
	        super(worldIn, throwerIn);
	    }

	    public EntityCustomSnowballExplosive(World worldIn, double x, double y, double z)
	    {
	        super(worldIn, x, y, z);
	    }

	    public static void registerFixesSnowball(DataFixer fixer)
	    {
	        EntityThrowable.registerFixesThrowable(fixer, "Snowball");
	    }

	    /**
	     * Handler for {@link World#setEntityState}
	     */
	    @SideOnly(Side.CLIENT)
	    public void handleStatusUpdate(byte id)
	    {
	        if (id == 3)
	        {
	            for (int i = 0; i < 8; ++i)
	            {
	                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }

	    /**
	     * Called when this EntityThrowable hits a block or entity.
	     */
	    
	    protected void onImpact(RayTraceResult result) {

	        if (result.getBlockPos() != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
	        	if(!this.world.isRemote) {
	        		BlockPos pos = result.getBlockPos();
	        		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
	        		this.world.createExplosion(null, x, y, z, 9.9F, true);
	        		this.setDead();
	        	}
	        }
	        
	        if(result.entityHit != null) {
	        	if(!this.world.isRemote) {
	        		BlockPos pos = result.entityHit.getPosition();
	        		double x = pos.getX(); double y = pos.getY(); double z = pos.getZ();
	        		this.world.createExplosion(null, x, y, z, 9.9F, true);
	        		this.setDead();
	        	}
	        }

	        
	        
	        if (!this.world.isRemote) {
	    
	            this.world.setEntityState(this, (byte)3);
	            this.setDead();
	        }
	    }
}
