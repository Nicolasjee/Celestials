package poseidon.mod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.Reference;

public class MessageParticle implements IMessage {

	private int entityId;

    public MessageParticle(double d, double e, double f) {}

    public MessageParticle(int parEntityId) {
        entityId = parEntityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, entityId, 4);
    }

    public static class Handler implements IMessageHandler<MessageParticle, IMessage> {
        @Override
        public IMessage onMessage(final MessageParticle message, MessageContext ctx) {
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            thePlayer.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		
            			
            		

            		return;
            	}
            });
            return null;
        }
    }
	
}
