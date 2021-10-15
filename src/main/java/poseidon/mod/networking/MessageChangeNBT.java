package poseidon.mod.networking;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;

public class MessageChangeNBT implements IMessage {

	private int entityId;

    public MessageChangeNBT() {}

    public MessageChangeNBT(int parEntityId) {
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

    public static class Handler implements IMessageHandler<MessageChangeNBT, IMessage> {
        @Override
        public IMessage onMessage(final MessageChangeNBT message, MessageContext ctx) {
            final EntityPlayerMP playerIn = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);
            playerIn.getServer().addScheduledTask(new Runnable() {
            	@Override
            	public void run() {
            		
            		ItemStack stack = playerIn.getHeldItemMainhand();
            		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates") && message.entityId == 1) {
						NBTTagCompound nbt = stack.getTagCompound();
						int[] c = nbt.getIntArray("Coordinates");
						
						int amount = getAmountFocus(c);
						
						int focus = c[c.length - 1];
						if(focus <= 5) focus++;
						if(focus == 6) focus = 0;
						if(focus > amount) focus = amount;
						if(focus < 0 || focus > 5) focus = 0;
						System.out.println("Focus set to " + focus);
						int count = 0;
						//	 									1                 2           3          4         5           6          7            8             9           10             11       12           13          14            15
						nbt.setIntArray("Coordinates", new int[] {c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++], c[count++] ,c[count++],
								c[count++], c[count++], c[count++], focus});
						
            		}
            		
            		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates") && message.entityId == 2) {
            			
            			NBTTagCompound nbt = stack.getTagCompound();
						int[] c = nbt.getIntArray("Coordinates");
						
						int focus = c[c.length - 1];
						int count = 0;
						
						List<Integer> temp = new ArrayList<Integer>();
						for(int i = 0; i < c.length; i++) {
							temp.add(c[i]);
						}
						
						int[] arr = new int[temp.size()];
						int checkFocus = focus*3;
						
						System.out.println("Focus: " + focus + ", Checkfocus: " + checkFocus);
						
						for(int i = 0; i < temp.size(); i++) {
							if(i == checkFocus || i == checkFocus + 1 || i == checkFocus + 2) arr[i] = 0;
							if(!(i == checkFocus)) arr[i] = temp.get(i);
						}
						
						System.out.println("arr:" + arr);
						System.out.println("prearr:" + c);
						NBTTagCompound nbtSet = new NBTTagCompound();
						nbtSet.setIntArray("Coordinates", arr);
						stack.setTagCompound(nbtSet);
						nbt.setIntArray("Coordinates", arr);
						
						System.out.println("Code executed");
            		}
            		
            	}
            	
            });
            return null;
        }
    }
    
    private static int getAmountFocus(int[] c) {
    	int b = 0;
    	for(int i = 0; i < c.length; i++) {
			if(c[i] != 0 && c[i+1] != 0 && c[i+2] != 0) {
				b++;
			}
    	}
    	return b;
    }
	
}
