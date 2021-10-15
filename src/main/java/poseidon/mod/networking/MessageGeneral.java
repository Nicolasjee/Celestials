package poseidon.mod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.init.PotionInit;

public class MessageGeneral implements IMessage {

	private int entityId;

    public MessageGeneral() {}

    public MessageGeneral(int parEntityId) {
        entityId = parEntityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityId = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, entityId, 4);
    }

    public static class Handler implements IMessageHandler<MessageGeneral, IMessage> {
        @Override
        public IMessage onMessage(final MessageGeneral message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		Entity theEntity = thePlayer.world.getEntityByID(message.entityId);
            		boolean kS = thePlayer.getHeldItemMainhand().getItem() == ItemInit.KILL_STICK;
            		
            		if(thePlayer.getHeldItemMainhand().getItem() == ItemInit.TESLA_INFUSER) MessageDirector.tesla(thePlayer, theEntity);
            		if(thePlayer.getHeldItemMainhand().getItem() == ItemInit.KILL_STICK) MessageDirector.kill(thePlayer, theEntity);
            		if(!thePlayer.world.isRemote && thePlayer.getHeldItemMainhand().getItem() == ItemInit.DIAMOND_MULTITOOL) MessageDirector.smartKill(thePlayer, theEntity, (WorldServer)(thePlayer.world));
            		
            		//POTION DAMAGE
            		if(theEntity instanceof EntityLivingBase && !kS) {
            			EntityLivingBase living = (EntityLivingBase) theEntity;
            			System.out.println("called");
            			if(living instanceof EntityPlayer) {
            				if(living.isPotionActive(PotionInit.BADJUJU)) living.setHealth(living.getHealth() + 3.0F);
            				return;
            			}
            			

            			
            			if(living instanceof EntityMob && !(living instanceof EntityPlayer)) {
            				if(living.isPotionActive(PotionInit.BADJUJU)) living.attackEntityFrom(DamageSource.MAGIC, 3.0F);
            				return;
            			}
            			
            		}
            		
            		if(!kS && thePlayer.getHeldItemMainhand().getItem() == ItemInit.TESLA_INFUSER) {
            			if(theEntity instanceof EntityLivingBase) {
            				((EntityLivingBase) theEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1000, 1000));
            			}
            		}
            		

            		return;
            	}
            });
            return null;
        }
    }
	
}
