package poseidon.mod.networking.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import poseidon.mod.Main;

// 
/**
 * @author jabelar
 *
 */
public class MessageToServer implements IMessage
{

    private String text;

    /**
     * Instantiates a new message to server.
     */
    public MessageToServer()
    {
        // need this constructor
    }

    /**
     * Instantiates a new message to server.
     *
     * @param parText
     *            the par text
     */
    public MessageToServer(String parText)
    {
        text = parText;
        // DEBUG
        System.out.println("MyMessage constructor");
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.minecraftforge.fml.common.network.simpleimpl.IMessage#fromBytes(io.netty.buffer.ByteBuf)
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
        // DEBUG
        System.out.println("fromBytes = " + text);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.minecraftforge.fml.common.network.simpleimpl.IMessage#toBytes(io.netty.buffer.ByteBuf)
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, text);
        // DEBUG
        System.out.println("toBytes = " + text);
    }

    public static class Handler implements IMessageHandler<MessageToServer, IMessage>
    {

        /*
         * (non-Javadoc)
         * 
         * @see net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler#onMessage(net.minecraftforge.fml.common.network.simpleimpl.IMessage,
         * net.minecraftforge.fml.common.network.simpleimpl.MessageContext)
         */
        @Override
        public IMessage onMessage(final MessageToServer message, MessageContext ctx)
        {
            // DEBUG
            System.out.println(String.format("Received %s from %s", message.text, Main.proxy.getPlayerEntityFromContext(ctx).getDisplayName()));
            // Know it will be on the server so make it thread-safe
            final EntityPlayerMP thePlayer = (EntityPlayerMP) Main.proxy.getPlayerEntityFromContext(ctx);

            
            thePlayer.getServer().addScheduledTask(
                    new Runnable() {
                        @Override
                        public void run()
                        {
                            return;
                        }
                    });
            return null; // no response in this case
        }
    }
}
