package poseidon.mod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.init.BlockInit;
import poseidon.mod.init.ItemInit;
import poseidon.mod.objects.items.wand.ModAbilities;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;

public class MessageChoke implements IMessage, TeslaProperties {

	private int entityId;

    public MessageChoke() {}

    public MessageChoke(int parEntityId) {
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

    public static class Handler implements IMessageHandler<MessageChoke, IMessage> {
        @Override
        public IMessage onMessage(final MessageChoke message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		Entity entity = thePlayer.world.getEntityByID(message.entityId);
            		if (thePlayer.getHeldItemMainhand() == null) { return; }
            		
            		ItemStack stack = thePlayer.getHeldItemMainhand();
            		
            		
            		if(stack.getItem() == ItemInit.ICY_SNOWBALL) {
            		
        	            entity.setVelocity(-thePlayer.getLookVec().x*10, 1, -thePlayer.getLookVec().z*10);
        	            
        	            if(entity instanceof EntityLivingBase && 100000000 == message.entityId) {

        	            	EntityLivingBase b = (EntityLivingBase) entity;
        	            	b.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 100));
        	            	
            			}
            		}
        	            
            		return;
            	}
            });
            return null;
        }
    }
	
}
