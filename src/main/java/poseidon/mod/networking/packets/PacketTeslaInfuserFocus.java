package poseidon.mod.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;

public class PacketTeslaInfuserFocus implements IMessage {

		private int newFocus;
	
		public PacketTeslaInfuserFocus() {}
		
	    public PacketTeslaInfuserFocus(int changeFocus)
	    {
	        //noinspection MethodCallSideOnly
	    	newFocus = changeFocus;
	    }

	    @Override
	    public void fromBytes(ByteBuf buf)
	    {
	    	newFocus = ByteBufUtils.readVarInt(buf, 5);
	    }

	    @Override
	    public void toBytes(ByteBuf buf)
	    {
	    	ByteBufUtils.writeVarInt(buf, newFocus, 5);
	    }

	    public static class Handler implements IMessageHandler<PacketTeslaInfuserFocus, IMessage>
	    {
	    	@Override
	        public IMessage onMessage(final PacketTeslaInfuserFocus message, MessageContext ctx) {
	            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
	            thePlayer.getServer().addScheduledTask(new Runnable() {
	            	@Override
	            	public void run() {
	            		ItemStack stack = thePlayer.getHeldItemMainhand();
	            		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("DataArray")) {
	            			NBTTagCompound nbt = stack.getTagCompound();
	            			int[] a = nbt.getIntArray("DataArray");
	            			nbt.removeTag("DataArray");
	            			nbt.setIntArray("DataArray", new int[] {a[0], a[1], a[2], a[3], message.newFocus, a[5]});
	            			stack.setTagCompound(nbt);
	            		}
	            	}
	            });
	            return null;
	        }

	    }
	
}





























