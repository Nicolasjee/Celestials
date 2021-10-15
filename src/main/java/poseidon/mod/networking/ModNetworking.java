package poseidon.mod.networking;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import poseidon.mod.networking.messages.MessageToClient;
import poseidon.mod.networking.messages.MessageToServer;
import poseidon.mod.util.Reference;

public class ModNetworking {

	public static final String NETWORK_CHANNEL_NAME = Reference.MODID;
	public static SimpleNetworkWrapper network;

	public static void registerSimpleNetworking()
	{
		// DEBUG
		System.out.println("Registering simple networking");
		network = NetworkRegistry.INSTANCE.newSimpleChannel(NETWORK_CHANNEL_NAME);

		int packetId = 0;
		// register messages from client to server
		network.registerMessage(MessageToServer.Handler.class, MessageToServer.class, packetId++, Side.SERVER);
		// register messages from server to client
		network.registerMessage(MessageToClient.Handler.class, MessageToClient.class, packetId++, Side.CLIENT);
		network.registerMessage(MessageExtendedReachAttack.Handler.class, MessageExtendedReachAttack.class, packetId++, Side.SERVER);
		network.registerMessage(MessageParticle.Handler.class, MessageParticle.class, packetId++, Side.SERVER);
		
	}

	
}
