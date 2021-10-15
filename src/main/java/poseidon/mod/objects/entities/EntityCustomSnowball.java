package poseidon.mod.objects.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.networking.MessageChoke;

public class EntityCustomSnowball extends EntitySnowball {
	
	public int bounces = 0;
	
	 public EntityCustomSnowball(World worldIn)
	    {
	        super(worldIn);
	    }

	    public EntityCustomSnowball(World worldIn, EntityLivingBase throwerIn)
	    {
	        super(worldIn, throwerIn);
	    }

	    public EntityCustomSnowball(World worldIn, double x, double y, double z)
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
	        if (result.entityHit != null) {
	            if(result.entityHit instanceof EntityLivingBase) {
	            	System.out.println("Sheep");
	            	Main.network.sendToServer(new MessageChoke(result.entityHit.getEntityId()));
	            }

	            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)1.0F);
	        }
	        
	        if(result.getBlockPos() != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
	        	if(!world.isRemote) world.setBlockState(result.getBlockPos().up(), BlockInit.LIGHT1.getDefaultState());
	        }

	        if (!this.world.isRemote) {
	    
	            this.world.setEntityState(this, (byte)3);
	            this.setDead();
	        }
	    }
}
