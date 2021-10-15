package poseidon.mod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import poseidon.mod.Main;
import poseidon.mod.entity.CustomEndRod;
import poseidon.mod.networking.MessageChangeNBT;
import poseidon.mod.networking.MessageChoke;
import poseidon.mod.networking.MessageExtendedReachAttack;
import poseidon.mod.networking.MessageParticle;
import poseidon.mod.networking.MessageForceKeybind;
import poseidon.mod.networking.MessageGeneral;
import poseidon.mod.networking.messages.MessageToServer;
import poseidon.mod.networking.packets.PacketTeslaInfuserFocus;
import poseidon.mod.objects.items.RecipeHandler;

public class CommonProxy {

	
	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	//public void registerModel(Item item, int metadata) {}

	public void preInit(FMLPreInitializationEvent event) {
        registerSimpleNetworking();
        //CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), ManaBar.class);


	}
	
	public void init(FMLInitializationEvent event) {
		RecipeHandler.registerCrafting();
		RecipeHandler.registerSmelting();

	}

	public void postInit(FMLPostInitializationEvent event) {
		
	}

	
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
    	return (ctx.side.isClient() ? Minecraft.getMinecraft().player : ctx.getServerHandler().player);
    }
    
	protected void registerSimpleNetworking() {
		Main.network = NetworkRegistry.INSTANCE.newSimpleChannel(Main.NETWORK_CHANNEL_NAME);

		int packetId = 0;
		// register messages from client to server
        Main.network.registerMessage(MessageToServer.Handler.class, MessageToServer.class, packetId++, Side.SERVER);
		Main.network.registerMessage(MessageExtendedReachAttack.Handler.class, 
		      MessageExtendedReachAttack.class, packetId++, Side.SERVER);
		Main.network.registerMessage(MessageParticle.Handler.class, 
			      MessageParticle.class, packetId++, Side.SERVER);
		Main.network.registerMessage(MessageGeneral.Handler.class, 
			      MessageGeneral.class, packetId++, Side.SERVER);
		Main.network.registerMessage(MessageChoke.Handler.class, MessageChoke.class, packetId++, Side.SERVER);
		Main.network.registerMessage(PacketTeslaInfuserFocus.Handler.class, PacketTeslaInfuserFocus.class, packetId++, Side.SERVER);
		Main.network.registerMessage(MessageForceKeybind.Handler.class, MessageForceKeybind.class, packetId++, Side.SERVER);
		Main.network.registerMessage(MessageChangeNBT.Handler.class, MessageChangeNBT.class, packetId++, Side.SERVER);
	}
	
    public void playSoundBlock(BlockPos pos, World world, SoundEvent event, float pitch, float vol) {}
    
    public void stasis(BlockPos pos, World w) {}
    
	public void dropItem(BlockPos pos, World world, Item item, int amount) {}
	
	public void particle(World world, CustomEndRod rod) {}
	
	public void spawnExp(World w, BlockPos p) {}

	
}
