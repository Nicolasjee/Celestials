package poseidon.mod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.util.interfaces.IExtendedReach;

public class MessageExtendedReachAttack implements IMessage {

	private int entityId;

    public MessageExtendedReachAttack() {}

    public MessageExtendedReachAttack(int parEntityId) {
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

    public static class Handler implements IMessageHandler<MessageExtendedReachAttack, IMessage> {
        @Override
        public IMessage onMessage(final MessageExtendedReachAttack message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		Entity theEntity = thePlayer.world.getEntityByID(message.entityId);
            		if (thePlayer.getHeldItemMainhand() == null) { return; }
            		if (thePlayer.getHeldItemMainhand().getItem() instanceof IExtendedReach) {

            			IExtendedReach theExtendedReachWeapon = (IExtendedReach) thePlayer.getHeldItemMainhand().getItem();
            			double distanceSq = thePlayer.getDistanceSq(theEntity);
            			double reachSq = theExtendedReachWeapon.getReach() * theExtendedReachWeapon.getReach();
            			if (reachSq >= distanceSq) {
            				thePlayer.attackTargetEntityWithCurrentItem(theEntity);
            			}
            		}
            		return;
            	}
            });
            return null;
        }
    }
	
}
