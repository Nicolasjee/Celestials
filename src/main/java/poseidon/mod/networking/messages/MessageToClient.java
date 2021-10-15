package poseidon.mod.networking.messages;

import io.netty.buffer.ByteBuf;
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
public class MessageToClient implements IMessage
{

    private String text;

    /**
     * Instantiates a new message to client.
     */
    public MessageToClient()
    {
        // need this constructor
    }

    /**
     * Instantiates a new message to client.
     *
     * @param parText
     *            the par text
     */
    public MessageToClient(String parText)
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

    public static class Handler implements IMessageHandler<MessageToClient, IMessage>
    {

        /*
         * (non-Javadoc)
         * 
         * @see net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler#onMessage(net.minecraftforge.fml.common.network.simpleimpl.IMessage,
         * net.minecraftforge.fml.common.network.simpleimpl.MessageContext)
         */
        @Override
        public IMessage onMessage(MessageToClient message, MessageContext ctx)
        {
            System.out.println(String.format("Received %s from %s", message.text, Main.proxy.getPlayerEntityFromContext(ctx).getDisplayName()));
            return null; // no response in this case
        }
    }
}