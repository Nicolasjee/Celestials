package poseidon.mod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class MessageHeal3 implements IMessage, TeslaProperties {

	private int entityId;

    public MessageHeal3() {}

    public MessageHeal3(int parEntityId) {
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

    public static class Handler implements IMessageHandler<MessageHeal3, IMessage> {
        @Override
        public IMessage onMessage(final MessageHeal3 message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		Entity entity = thePlayer.world.getEntityByID(message.entityId);
            		if (thePlayer.getHeldItemMainhand() == null) { return; }

            			if(entity instanceof EntityLivingBase) {
            				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1000, 100));
            			}
            			
            			if(entity instanceof EntityArrow) {
    	                	EntityArrow a = (EntityArrow) entity;
    	                	a.setVelocity(0,0,0);
            			}
        	                
            			
            		
            		return;
            	}
            });
            return null;
        }
    }
	
}
