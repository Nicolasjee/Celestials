package poseidon.mod.objects.items.wand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WandData {

	NBTTagCompound nbt;
	public static final String Wind = "Boost Elytra - Super Jump";	    // Wind
	public static final String Earth = "Push Enemies - Launch ~";	        // Earth
	public static final String Sky = "Lightning - Multiple ~"; // Sky
	public static final String Fire = "Launch Fire - Fireball";			// Fire
	public static final String Inferno = "Place Fire - Set Fire to Enemies";
	public static final String Nether = "Explosion - TNT"; // Nether ( + TNT)
	public static final String Ender = "Teleport - Waypoint";   // Ender
	public static final String Heal = "Heal - ";
	
	static String Wi1 = "Boost Elytra";
	static String Wi2 = "Super Jump";
	static String Ea1 = "Push Enemies";
	static String Ea2 = "Launch Enemies";
	static String Sk1 = "Lightning";
	static String Sk2 = "Multiple Lightnings";
	static String Fi1 = "Launch Fire";
	static String Fi2 = "Fireball";
	static String In1 = "Place Fire";
	static String In2 = "Set Fire to Enemies";
	static String Ne1 = "Explosion";
	static String Ne2 = "TNT";
	static String En1 = "Teleport";
	static String En2 = "Waypoint";
	
	
	public static ItemStack stack;
	
	public static ArrayList<String> setArray() {
		ArrayList<String> abilities = new ArrayList<String>();
		abilities.add(Wind);
		abilities.add(Earth);
		abilities.add(Sky);
		abilities.add(Fire);
		abilities.add(Inferno);
		abilities.add(Ender);
		return abilities;
	}
	
	
	
	
	
	public static ItemStack transfer(ItemStack stack, ItemStack tran) {
		
		
		NBTTagCompound nbt = new NBTTagCompound();
		
		int dura1 = stack.getTagCompound().getInteger("Spell1B");
		int dura2 = stack.getTagCompound().getInteger("Spell2B");
		int dura3 = stack.getTagCompound().getInteger("Spell3B");
		
		//Activation
		int spell1 = stack.getTagCompound().getInteger("Spell1");
		int spell2 = stack.getTagCompound().getInteger("Spell2");
		int spell3 = stack.getTagCompound().getInteger("Spell3");
		
		//Which spell
		String a1 = stack.getTagCompound().getString("Spell1A");
		String a2 = stack.getTagCompound().getString("Spell2A");
		String a3 = stack.getTagCompound().getString("Spell3A");
		
		int fo = stack.getTagCompound().getInteger("Focus");
		int val = stack.getTagCompound().getInteger("Value");
		int flo = stack.getTagCompound().getInteger("LFocus");
		
		double x = stack.getTagCompound().getDouble("X");
		double y = stack.getTagCompound().getDouble("Y");
		double z = stack.getTagCompound().getDouble("Z");
		
		//Durability
		nbt.setInteger("Spell1B", dura1);
		nbt.setInteger("Spell2B", dura2);
		nbt.setInteger("Spell3B", dura3);
		
		//Activation
		nbt.setInteger("Spell1", spell1);
		nbt.setInteger("Spell2", spell2);
		nbt.setInteger("Spell3", spell3);
		
		//Which spell
		nbt.setString("Spell1A", a1);
		nbt.setString("Spell2A", a2);
		nbt.setString("Spell3A", a3);
		
		nbt.setInteger("Focus", fo);
		nbt.setInteger("Value", val);
		nbt.setInteger("LFocus", flo);
		
		nbt.setDouble("X", x);
		nbt.setDouble("Y", y);
		nbt.setDouble("Z", z);
		tran.setTagCompound(nbt);
		
		return tran;
	}
	
	public static void execute(ItemStack input1, ItemStack input2, ItemStack result, ItemStack output) {
		
		//Input 1: stick
		//Input 2: token
		
		
		
		
	}
	
	public static String getAbility(ItemStack stack, int n) {
		String string = null;
		if(n == 1) {
			string = stack.getTagCompound().getString("Spell1A");
			return string;
		}
		if(n == 2) {
			string = stack.getTagCompound().getString("Spell2A");
			return string;
		}
		if(n ==3) {
			string = stack.getTagCompound().getString("Spell3A");
			return string;
		}
		return string;
	}
	
	public static Integer getLevel(ItemStack stack, int n) {
		int i = 0;
		if(n == 1) {
			i = stack.getTagCompound().getInteger("Spell1B");
		}
		if(n == 2) {
			i = stack.getTagCompound().getInteger("Spell2B");
		}
		if(n == 3) {
			i = stack.getTagCompound().getInteger("Spell3B");
		}
		return i;
	}
	
	public static int getFocus(ItemStack stack) {
		int n = stack.getTagCompound().getInteger("Focus");
		
		return n;
	}
	
	public static int getLFocus(ItemStack stack) {
		int n = stack.getTagCompound().getInteger("LFocus");
		return n;
	}
	
	public static int getNumber() {
		Random rn = new Random();
		int maximum = 110;
		int minimum = 1;
		int range = maximum - minimum + 1;
		int randomNum =  rn.nextInt(range) + minimum;
		return randomNum;
	}
	
	public static String getDisplay(int i) {
		String string2 = null;
		
		if(i <= 70) {
			string2 = "Earth";
		}
		if(i >= 71 && i <= 140) {
			string2 = "Ender";
		}
		if(i >= 141 && i <= 210) {
			string2 ="Fire";
		}
		if(i >= 211 && i <= 280) {
			string2 = "Inferno";
		}
		if(i >= 281 && i <= 350) {
			string2 = "Nether";
		}
		if( i >= 351 && i <= 420) {
			string2 = "Sky";
		}
		if(i >= 421 && i <= 470) {
			string2 = "Wind";
		}
		if(i >= 471) {
			string2 = "Wind";
		}
		return string2;
	}

	public static String getRandomAbility(int i) {
		String string = null;
		
		if(i <= 70) {
			string = WandData.Earth;
		}
		if(i >= 71 && i < 140) {
			string = WandData.Ender;
		}
		if(i >= 141 && i < 210) {
			string = WandData.Fire;
		}
		if(i >= 211 && i < 280) {
			string = WandData.Inferno;
		}
		if(i >= 281 && i < 350) {
			string = WandData.Nether;
		}
		if( i >= 351 && i < 420) {
			string = WandData.Sky;
		}
		if(i >= 421 && i < 470) {
			string = WandData.Wind;
		}
		if(i >= 471) {
			string = WandData.Wind;
		}
	
		
		
		
		return string;
	}
	
	
}
























