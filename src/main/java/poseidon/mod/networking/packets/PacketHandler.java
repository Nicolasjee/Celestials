package poseidon.mod.networking.packets;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import static net.minecraftforge.fml.relauncher.Side.SERVER;

public class PacketHandler {
	
	    public static SimpleNetworkWrapper INSTANCE = null;
	    private static int packetId = 0;

	    public PacketHandler()
	    {
	    }

	    private static int nextID()
	    {
	        return packetId++;
	    }

	    public static void registerMessages(String channelName)
	    {
	        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
	        registerMessages();
	    }

	    private static void registerMessages()
	    {
	    }
	
}
