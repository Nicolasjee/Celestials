package poseidon.mod.util.handlers.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import poseidon.mod.Main;
import poseidon.mod.client.keybinds.Keybinds;
import poseidon.mod.init.ItemInit;
import poseidon.mod.networking.MessageChangeNBT;
import poseidon.mod.networking.MessageForceKeybind;
import poseidon.mod.networking.packets.PacketTeslaInfuserFocus;
import poseidon.mod.objects.tools.teslainfuser.TeslaProperties;
import poseidon.mod.util.Reference;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.handlers.EventHelper;

@EventBusSubscriber
public class KeyInputHandler implements TeslaProperties {

//	int focus;
//	int hasLevel;

	@SubscribeEvent
	public static void onKeyInput(InputEvent.KeyInputEvent event) {

		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;		

		if (Keybinds.elytraBoost.isPressed()) {


			if (!player.isElytraFlying() && (player.inventory.hasItemStack(new ItemStack(ItemInit.WARRANT)))) {
				
				
					
					
					double camX = player.getLookVec().x;
					double camY = player.getLookVec().y;
					double camZ = player.getLookVec().z;

					double velocityAddedX = camX * 4.0F;
					double velocityAddedY = camY * 3.0F;
					double velocityAddedZ = camZ * 4.0F;

					double currentVelocityX = player.motionX + velocityAddedX;
					double currentVelocityY = player.motionY + velocityAddedY;
					double currentVelocityZ = player.motionZ + velocityAddedZ;

					player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);
				
			}
				

//			if(player.getHeldItemMainhand().getItem() == ItemInit.TESLA_INFUSER) {
//				ItemStack stack = player.getHeldItemMainhand();
//				
//				if(stack.hasTagCompound()) {
//					
//					NBTTagCompound nbt = stack.getTagCompound();
//					int[] data = nbt.getIntArray("DataArray");
//					int focus = TeslaProperties.focus(data);
//					int dur = TeslaProperties.dur(data);
//					int cost = TeslaProperties.getCost(focus, stack);
//					
//					String ability = TeslaProperties.getAbilityOfFocus(stack);
//					
//					int a1 = data[0];
//					int a2 = data[1];
//					int a3 = data[2];
//					int a4 = data[3];
//					
//					System.out.println("Focus: " + focus + ", dur: " + dur + ", cost: " + cost);
//					
//					if(!(dur > cost)) 
//						stack.getTagCompound().setIntArray("DataArray", new int[] {a1, a2, a3, a4, focus, dur - cost});
//					else return;
//					
//					
//				}
				
			//}
					
			if(player.isCreative()) {
				double camX = player.getLookVec().x;
				double camY = player.getLookVec().y;
				double camZ = player.getLookVec().z;
		
				double velocityAddedX = camX * 4.0F;
				double velocityAddedY = camY * 2.0F;
				double velocityAddedZ = camZ * 4.0F;
		
				double currentVelocityX = player.motionX + velocityAddedX;
				double currentVelocityY = player.motionY + velocityAddedY;
				double currentVelocityZ = player.motionZ + velocityAddedZ;
		
				player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);
			}
		}
			

		
		
		
		if(Keybinds.changeAbility.isPressed()) {
			
			if(EventHelper.isMain(ItemInit.TESLA_INFUSER, player)) {
				
				ItemStack stack = player.getHeldItemMainhand();
				
				boolean hasPower0 = (TeslaProperties.getAbilityOfIndex(0, stack) != EMPTY);
				boolean hasPower1 = (TeslaProperties.getAbilityOfIndex(1, stack) != EMPTY);
				boolean hasPower2 = (TeslaProperties.getAbilityOfIndex(2, stack) != EMPTY);
				boolean hasPower3 = (TeslaProperties.getAbilityOfIndex(3, stack) != EMPTY);
				
				if(stack.hasTagCompound()) {
					
					int[] data = stack.getTagCompound().getIntArray("DataArray");
					int nextFocus = getNextFocus(hasPower0, hasPower1, hasPower2, hasPower3, TeslaProperties.focus(data));
					
					changeFocus(nextFocus);
					player.sendStatusMessage(new TextComponentTranslation("Power set to " + TextFormatting.GREEN + TeslaProperties.getAbilityOfIndex(nextFocus, stack), new Object[0]), true);
				
				}	
			}
//			if(EventHelper.isMain(ItemInit.WAYPOINT, player)) {
//				ItemStack stack = player.getHeldItemMainhand();
//				
//				if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates") && !player.isSneaking()) {
//					int[] c = stack.getTagCompound().getIntArray("Coordinates");
//					int focus = c[c.length - 1];
//					if(focus <= 5) focus++;
//					if(focus == 6) focus = 0;
//					if(focus < 0 || focus > 5) focus = 0;
//					Main.network.sendToServer(new MessageChangeNBT(1));
//					player.sendStatusMessage(new TextComponentTranslation("Waypoint set to: " + TextFormatting.GREEN + (focus+1), stack, new Object[0]), true);
//				}
//				
//				if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Coordinates") && player.isSneaking()) {
//					Main.network.sendToServer(new MessageChangeNBT(2));
//				}
//				
//			}

			
		}
		
		
			if(Keybinds.choke.isPressed()) {
				
				RayTraceResult mov = Utilities.getMouseOverExtended(360.0F);
		      	
		       	if(mov != null) {
		       		if(mov.entityHit != null && mov.entityHit.hurtResistantTime == 0) {
		       			if(mov.entityHit != player) {
		       				Main.network.sendToServer(new MessageForceKeybind(mov.entityHit.getEntityId()));
		       			}
		       		}
		       	}
				
			}
		
			
		if(Keybinds.elytraFree.isPressed() && player.isElytraFlying()) {
			
			
			double camX = player.getLookVec().x;
			double camY = player.getLookVec().y;
			double camZ = player.getLookVec().z;

			int modifier = Reference.elytraBoostModifier;
			
			double velocityAddedX = camX * (4.0F / modifier);
			double velocityAddedY = camY * (3.0F / modifier);
			double velocityAddedZ = camZ * (4.0F / modifier);

			double currentVelocityX = player.motionX + velocityAddedX;
			double currentVelocityY = player.motionY + velocityAddedY;
			double currentVelocityZ = player.motionZ + velocityAddedZ;

			player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);
			
		}

			
	}
	
	private static int getNextFocus(boolean a, boolean b, boolean c, boolean d, int focus) {
		
		if(focus == 0 && b) return 1;
		else if(focus == 0 && !b) {
			if(c) return 2;
			else if(!c) {
				if(d) return 3;
				else return 0;
			}
		}
		
		if(focus == 1 && c) return 2;
		else if(focus == 1 && !c) {
			if(d) return 3;
			else if(!d) {
				if(a) return 0;
				else return 1;
			}
		}
		
		if(focus == 2 && d) return 3;
		else if(focus == 2 && !d) {
			if(a) return 0;
			else if(!a) {
				if(b) return 1;
				else return 2;
			}
		}
		
		if(focus == 3 && a) return 0;
		else if(focus == 3 && !a) {
			if(b) return 1;
			else if(!b) {
				if(c) return 2;
				else return 3;
			}
		}
		
		return 0;
	}

	private static void changeFocus(int i) {
		System.out.println("Attempted change of focus to " + i);
		Main.network.sendToServer(new PacketTeslaInfuserFocus(i));
	}
	
	private void execute() {
		//Main.network.sendToServer(new PacketVelocityChange(velocityCost));
	}
		
		
	

}
