package poseidon.mod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IExtendedReach;

public class MessageForceKeybind implements IMessage {

	private int entityId;

    public MessageForceKeybind() {}

    public MessageForceKeybind(int parEntityId) {
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

    public static class Handler implements IMessageHandler<MessageForceKeybind, IMessage> {
        @Override
        public IMessage onMessage(final MessageForceKeybind message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		Entity entity = thePlayer.world.getEntityByID(message.entityId);
            			double distanceSq = thePlayer.getDistanceSq(entity);

            				double d0 = (thePlayer.getEntityBoundingBox().minX + thePlayer.getEntityBoundingBox().maxX) / 2.0D;
            			    double d1 = (thePlayer.getEntityBoundingBox().minZ + thePlayer.getEntityBoundingBox().maxZ) / 2.0D;
            				double d2 = entity.posX - d0;
        	                double d3 = entity.posZ - d1;
        	                double d4 = d2 * d2 + d3 * d3 / 10;
        	                entity.addVelocity(d2 / d4 * 10.0D, 0.30000000298023224D, d3 / d4 * 10.0D);
            			
            		}
            	
            });
            return null;
        }
    }
	
}
