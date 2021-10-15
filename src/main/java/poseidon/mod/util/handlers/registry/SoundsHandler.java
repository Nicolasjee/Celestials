package poseidon.mod.util.handlers.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import poseidon.mod.util.Reference;

public class SoundsHandler {
	
	public static SoundEvent STASIS, CHESTSOLD, DANGER, MARAUDER, TELEPORT, CHECKPOINT, MIRROR;
	public static SoundEvent LIGHTSABER_OPEN, LIGHTSABER_CLOSE, LIGHTSABER_HIT1, LIGHTSABER_BUZZ;
	public static SoundEvent DEMONDEAD, WILLHOT, WILLAH, REAPERKILL, RUMBLE, SUBIMPACT, LOCK, EARTH, LOCKED, CHARGE, LAZER, OPENING;
	public static SoundEvent J1, J2, J3, J4, MYSTERIOUSSOUND3;
	
	public static void registerSounds() {
		STASIS = registerSound("item.effects.stasis");
		MARAUDER = registerSound("item.effects.marauder");
		CHESTSOLD = registerSound("item.effects.chestsold");
		DANGER = registerSound("item.effects.danger");
		TELEPORT = registerSound("item.effects.teleport");
		CHECKPOINT = registerSound("item.effects.checkpoint");
		MIRROR = registerSound("item.effects.mirror");
		LIGHTSABER_OPEN = registerSound("item.effects.lightsaber_open");
		LIGHTSABER_CLOSE = registerSound("item.effects.lightsaber_close");
		LIGHTSABER_HIT1 = registerSound("item.effects.lightsaber_hit1");
		DEMONDEAD = registerSound("item.effects.demondead");
		WILLHOT = registerSound("item.effects.willhot");
		WILLAH = registerSound("item.effects.willah");
		REAPERKILL = registerSound("item.effects.reaperkill");
		LIGHTSABER_BUZZ = registerSound("item.effects.lightsaberbuzz");
		SUBIMPACT = registerSound("item.effects.subimpact");
		RUMBLE = registerSound("item.effects.rumble");
		LOCK = registerSound("item.effects.unlock");
		EARTH = registerSound("item.effects.earth");
		LOCKED = registerSound("item.effects.locked");
		CHARGE = registerSound("item.effects.charge");
		LAZER = registerSound("item.effects.lazer");
		J1 = registerSound("item.effects.hamidlayah");
		J2 = registerSound("item.effects.aberyousee");
		J3 = registerSound("item.effects.euhhaha");
		J4 = registerSound("item.effects.ouhoo");
		MYSTERIOUSSOUND3 = registerSound("item.effects.mysterioussound3");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
	
	
}
