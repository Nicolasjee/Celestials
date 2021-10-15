package poseidon.mod.objects.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomFireball extends EntityLargeFireball {
	
	private int explosionPower;

    public CustomFireball(World worldIn)
    {
        super(worldIn);
    }

    @SideOnly(Side.CLIENT)
    public CustomFireball(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
    }
    
//    public CustomFireball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, int exploso) {
////    	this.explosionPower = exploso;
//    	super(worldIn, shooter, accelX, accelY, accelZ);
//    }

    public CustomFireball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, int ex)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.explosionPower = ex;
        
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }
            
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
            this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.explosionPower, flag, flag);
            this.setDead();
        }
    }

    public static void registerFixesLargeFireball(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "Fireball");
    }

//    /**
//     * (abstract) Protected helper method to write subclass entity data to NBT.
//     */
//    public void writeEntityToNBT(NBTTagCompound compound)
//    {
//        super.writeEntityToNBT(compound);
//        compound.setInteger("ExplosionPower", this.explosionPower);
//    }
//
//    /**
//     * (abstract) Protected helper method to read subclass entity data from NBT.
//     */
//    public void readEntityFromNBT(NBTTagCompound compound)
//    {
//        super.readEntityFromNBT(compound);
//
//        if (compound.hasKey("ExplosionPower", 99))
//        {
//            this.explosionPower = compound.getInteger("ExplosionPower");
//        }
//    }
	
}