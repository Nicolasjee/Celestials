package poseidon.mod.objects.items.wand;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import poseidon.mod.Main;
import poseidon.mod.init.ItemInit;
import poseidon.mod.util.interfaces.IHasModel;

public class Wand extends Item implements IHasModel {

	int spell1;
	int spell2;
	int spell3;
	int focus;
	int Lfocus;
	String spell1a;
	String spell2a;
	String spell3a;
	int spell1b;
	int spell2b;
	int spell3b;
	double x, y,z;
	NBTTagCompound nbt;
	int i = 0;
	
	public Wand(String name, int size) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(size);
		setCreativeTab(Main.ARISTOISITEMS);
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(!stack.hasTagCompound()) {
			setNBT(stack);
			} else {
			NBTTagCompound tag = stack.getTagCompound();
			stack.setTagCompound(tag);
		}
		
		if(stack.getTagCompound().getInteger("Spell1") == 0) {
			
		}
		
		
		
		if(stack.getTagCompound().getInteger("Spell1") + stack.getTagCompound().getInteger("Spell2") + stack.getTagCompound().getInteger("Spell3") == 1) {
			stack.getTagCompound().setInteger("Focus", 1);
		}
		

		
	}
	
	private void setNBT(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			
			NBTTagCompound nbt = new NBTTagCompound();
			
			//Level
			nbt.setInteger("Spell1B", 1);
			nbt.setInteger("Spell2B", 1);
			nbt.setInteger("Spell3B", 1);
			
			//Activation
			nbt.setInteger("Spell1", 0);
			nbt.setInteger("Spell2", 0);
			nbt.setInteger("Spell3", 0);
			
			//Which spell
			nbt.setString("Spell1A", "");
			nbt.setString("Spell2A", "");
			nbt.setString("Spell3A", "");
			
			nbt.setInteger("Focus", 1);
			nbt.setInteger("LFocus", 1);
			nbt.setInteger("Value", 3);
			
			nbt.setDouble("X", 0);
			nbt.setDouble("Y", 0);
			nbt.setDouble("Z", 0);
			stack.setTagCompound(nbt);
		}
	}
	
	private void getNBT(ItemStack stack) {
		
		if(stack.hasTagCompound()) {
			
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
			
			double xi = stack.getTagCompound().getDouble("X");
			double yi = stack.getTagCompound().getDouble("Y");
			double zi = stack.getTagCompound().getDouble("Z");
			
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
			
			nbt.setDouble("X", xi);
			nbt.setDouble("Y", yi);
			nbt.setDouble("Z", zi);
			
			stack.setTagCompound(nbt);
		

		} //Gets The compound which holds keys!
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		NBTTagCompound nbt = playerIn.getHeldItem(handIn).getTagCompound();
		getVariables(playerIn.getHeldItem(handIn).getTagCompound());
		int focus = nbt.getInteger("Focus");
		
		System.out.println(nbt.getString("Spell1A") + " - Int: " + nbt.getInteger("Spell1") + " - Focus: " + nbt.getInteger("Focus"));
		
		
		if(spell1 + spell2 + spell3 == 0) {
			return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
			} else if(spell1 + spell2 + spell3 != 0 || spell1 + spell2 + spell3 > 0 || spell1 + spell2 + spell3 <= 3) {
			if(focus == 1) {
				
				if(spell1a == WandData.Wind) { //Sky
					if(Lfocus == 1) {
						ModAbilities.wind(0, playerIn, worldIn);
						} else if(Lfocus == 2){
						ModAbilities.wind2(0, playerIn, worldIn);
					}
				
					} else if(spell1a == WandData.Earth) {
						if(Lfocus == 1) {
							//ModAbilities.earth(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.earth2(playerIn, worldIn);
						}
					
					} else if(spell1a == WandData.Sky) {
						if(Lfocus == 1) {
							ModAbilities.sky(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.sky2(0, playerIn, worldIn);
						}
					
					} else if(spell1a == WandData.Fire) {
						if(Lfocus == 1) {
							ModAbilities.fire(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.fire2(0, playerIn, worldIn);
						}
					
					} else if(spell1a == WandData.Inferno) {
						if(Lfocus == 1) {
							ModAbilities.inferno(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.inferno2(0, playerIn, worldIn);
						}
					
					} else if(spell1a == WandData.Nether) {
						if(Lfocus == 1) {
							ModAbilities.nether(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.nether2(0, playerIn, worldIn);
						}
					} else if(spell1a == WandData.Ender) {
						if(Lfocus == 1) {
							ModAbilities.ender(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.ender2(0, playerIn, worldIn, x, y, z);
						}
					
				}
				
			} else if(focus == 2) {

				if(spell2a == WandData.Wind) { //Sky
					if(Lfocus == 1) {
						ModAbilities.wind(0, playerIn, worldIn);
						} else if(Lfocus == 2){
						ModAbilities.wind2(0, playerIn, worldIn);
					}
				
					} else if(spell2a == WandData.Earth) {
						if(Lfocus == 1) {
							//ModAbilities.earth(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.earth2(playerIn, worldIn);
						}
					
					} else if(spell2a == WandData.Sky) {
						if(Lfocus == 1) {
							ModAbilities.sky(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.sky2(0, playerIn, worldIn);
						}
					
					} else if(spell2a == WandData.Fire) {
						if(Lfocus == 1) {
							ModAbilities.fire(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.fire2(0, playerIn, worldIn);
						}
					
					} else if(spell2a == WandData.Inferno) {
						if(Lfocus == 1) {
							ModAbilities.inferno(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.inferno2(0, playerIn, worldIn);
						}
					
					} else if(spell2a == WandData.Nether) {
						if(Lfocus == 1) {
							ModAbilities.nether(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.nether(0, playerIn, worldIn);
						}
					} else if(spell2a == WandData.Ender) {
						if(Lfocus == 1) {
							ModAbilities.ender(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.ender2(0, playerIn, worldIn, x, y, z);
						}
					
				}
				
			} else 
			
				if(spell3a == WandData.Wind) { //Sky
					if(Lfocus == 1) {
						ModAbilities.wind(0, playerIn, worldIn);
						} else if(Lfocus == 2){
						ModAbilities.wind2(0, playerIn, worldIn);
					}
				
					} else if(spell3a == WandData.Earth) {
						if(Lfocus == 1) {
							//ModAbilities.earth(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.earth2(playerIn, worldIn);
						}
					
					} else if(spell3a == WandData.Sky) {
						if(Lfocus == 1) {
							ModAbilities.sky(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.sky2(0, playerIn, worldIn);
						}
					
					} else if(spell3a == WandData.Fire) {
						if(Lfocus == 1) {
							ModAbilities.fire(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.fire2(0, playerIn, worldIn);
						}
					
					} else if(spell3a == WandData.Inferno) {
						if(Lfocus == 1) {
							ModAbilities.inferno(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.inferno2(0, playerIn, worldIn);
						}
					
					} else if(spell3a == WandData.Nether) {
						if(Lfocus == 1) {
							ModAbilities.nether(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.nether(0, playerIn, worldIn);
						}
					} else if(spell3a == WandData.Ender) {
						if(Lfocus == 1) {
							ModAbilities.ender(0, playerIn, worldIn);
							} else if(Lfocus == 2){
							ModAbilities.ender2(0, playerIn, worldIn, x, y, z);
						}
					
				}
		}
		
		
		ItemStack copy = playerIn.getHeldItem(handIn).copy();
		ItemStack cu = playerIn.getHeldItem(handIn);
		int i = playerIn.inventory.getSlotFor(cu);
		//playerIn.inventory.removeStackFromSlot(i);
		
		playerIn.replaceItemInInventory(i, copy);
		
		return new ActionResult(EnumActionResult.SUCCESS, copy);
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {

		ItemStack stack = player.getHeldItemMainhand();
		
		if(stack.hasTagCompound()) {
			if(stack.getTagCompound().getString("Spell1A") == WandData.Ender || stack.getTagCompound().getString("Spell2A") == WandData.Ender || stack.getTagCompound().getString("Spell3A") == WandData.Ender) {
				if(stack.getTagCompound().getInteger("Spell1B") > 1 || stack.getTagCompound().getInteger("Spell2B") > 1 || stack.getTagCompound().getInteger("Spell3B") > 1) {
					stack.getTagCompound().setDouble("X", hitX);
					stack.getTagCompound().setDouble("Y", hitY);
					stack.getTagCompound().setDouble("Z", hitZ);
				}
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	private void getVariables(NBTTagCompound nbt) {
		focus = nbt.getInteger("Focus");
		Lfocus = nbt.getInteger("LFocus");
		
		spell1 = nbt.getInteger("Spell1");
		spell2 = nbt.getInteger("Spell2");
		spell3 = nbt.getInteger("Spell3");
		
		spell1b = nbt.getInteger("Spell1B");
		spell2b = nbt.getInteger("Spell2B");
		spell3b = nbt.getInteger("Spell3B");
		
		spell1a = nbt.getString("Spell1A");
		spell2a = nbt.getString("Spell2A");
		spell3a = nbt.getString("Spell3A");
		
		x = nbt.getDouble("X");
		y = nbt.getDouble("Y");
		z = nbt.getDouble("Z");
		
	}
	
	
	
	

	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> addlore, ITooltipFlag flagIn) {
 

		
		String Wind = WandData.Wind;
		String Sky = WandData.Sky;
		String Fire = WandData.Fire;
		String Inferno = WandData.Inferno;
		String Nether = WandData.Nether;
		String Ender = WandData.Ender;
		String Earth = WandData.Earth;
		
		  String Wi1 = "Boost Elytra";
		  String Wi2 = "Super Jump";
		  String Ea1 = "Push Enemies";
		  String Ea2 = "Launch Enemies";
		  String Sk1 = "Lightning";
		  String Sk2 = "Multiple Lightnings";
		  String Fi1 = "Launch Fire";
		  String Fi2 = "Fireball";
		  String In1 = "Place Fire";
		  String In2 = "Set Fire to Enemies";
		  String Ne1 = "Explosion";
		  String Ne2 = "TNT";
		  String En1 = "Teleport";
		  String En2 = "Waypoint";


		
		if(stack.hasTagCompound()) {
			if(stack.getTagCompound().hasKey("Spell1") && stack.getTagCompound().hasKey("Spell2") && stack.getTagCompound().hasKey("Spell3") && stack.getTagCompound().hasKey("Spell1B") && stack.getTagCompound().hasKey("Spell2B") && stack.getTagCompound().hasKey("Spell3B") && stack.getTagCompound().hasKey("Spell1A") && stack.getTagCompound().hasKey("Spell2A") && stack.getTagCompound().hasKey("Spell3A") && stack.getTagCompound().hasKey("Focus") && stack.getTagCompound().hasKey("LFocus")) {


				
			if(stack.getTagCompound().getInteger("Spell1") == 1) {
				if(stack.getTagCompound().getInteger("Focus") == 1) {
					if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell1B") == 1) {
						if(stack.getTagCompound().getString("Spell1A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Wi2);
						if(stack.getTagCompound().getString("Spell1A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Ea2);
						if(stack.getTagCompound().getString("Spell1A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Sk2);
						if(stack.getTagCompound().getString("Spell1A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Fi2);
						if(stack.getTagCompound().getString("Spell1A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.GRAY + " - " + TextFormatting.RED + In2);
						if(stack.getTagCompound().getString("Spell1A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Ne2);
						if(stack.getTagCompound().getString("Spell1A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.GRAY + " - " + TextFormatting.RED + En2);
					} else if(stack.getTagCompound().getInteger("LFocus") == 2 && stack.getTagCompound().getInteger("Spell1B") == 2) {
						if(stack.getTagCompound().getString("Spell1A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Wi2);
						if(stack.getTagCompound().getString("Spell1A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Ea2);
						if(stack.getTagCompound().getString("Spell1A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Sk2);
						if(stack.getTagCompound().getString("Spell1A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Fi2);
						if(stack.getTagCompound().getString("Spell1A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + In1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + In2);
						if(stack.getTagCompound().getString("Spell1A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Ne2);
						if(stack.getTagCompound().getString("Spell1A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + En1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + En2);
					} else if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell1B") == 2) {
						if(stack.getTagCompound().getString("Spell1A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Wi2);
						if(stack.getTagCompound().getString("Spell1A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Ea2);
						if(stack.getTagCompound().getString("Spell1A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Sk2);
						if(stack.getTagCompound().getString("Spell1A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Fi2);
						if(stack.getTagCompound().getString("Spell1A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + In2);
						if(stack.getTagCompound().getString("Spell1A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Ne2);
						if(stack.getTagCompound().getString("Spell1A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + En2);
					} // lfocus && spell1b end
				} // focus end
			
			if(stack.getTagCompound().getInteger("Focus") == 2) {
				if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell1B") == 1) {
					if(stack.getTagCompound().getString("Spell1A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Wi2);
					if(stack.getTagCompound().getString("Spell1A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Ea2);
					if(stack.getTagCompound().getString("Spell1A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Sk2);
					if(stack.getTagCompound().getString("Spell1A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Fi2);
					if(stack.getTagCompound().getString("Spell1A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + In2);
					if(stack.getTagCompound().getString("Spell1A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Ne2);
					if(stack.getTagCompound().getString("Spell1A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + En2);
				} else if(stack.getTagCompound().getInteger("LFocus") == 2 && stack.getTagCompound().getInteger("Spell1B") == 2) {
					if(stack.getTagCompound().getString("Spell1A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Wi2);
					if(stack.getTagCompound().getString("Spell1A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Ea2);
					if(stack.getTagCompound().getString("Spell1A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Sk2);
					if(stack.getTagCompound().getString("Spell1A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Fi2);
					if(stack.getTagCompound().getString("Spell1A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + In2);
					if(stack.getTagCompound().getString("Spell1A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Ne2);
					if(stack.getTagCompound().getString("Spell1A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + En2);
				} else if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell1B") == 2) {
					if(stack.getTagCompound().getString("Spell1A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Wi2);
					if(stack.getTagCompound().getString("Spell1A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Ea2);
					if(stack.getTagCompound().getString("Spell1A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Sk2);
					if(stack.getTagCompound().getString("Spell1A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Fi2);
					if(stack.getTagCompound().getString("Spell1A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + In2);
					if(stack.getTagCompound().getString("Spell1A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Ne2);
					if(stack.getTagCompound().getString("Spell1A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + En2);
				} //lfocus end
			}//focus end
		} // spell 1 == 1 end
		
			
			if(stack.getTagCompound().getInteger("Spell2") == 1) {
				if(stack.getTagCompound().getInteger("Focus") == 2) {
					if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell2B") == 1) {
						if(stack.getTagCompound().getString("Spell2A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Wi2);
						if(stack.getTagCompound().getString("Spell2A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Ea2);
						if(stack.getTagCompound().getString("Spell2A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Sk2);
						if(stack.getTagCompound().getString("Spell2A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Fi2);
						if(stack.getTagCompound().getString("Spell2A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.GRAY + " - " + TextFormatting.RED + In2);
						if(stack.getTagCompound().getString("Spell2A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Ne2);
						if(stack.getTagCompound().getString("Spell2A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.GRAY + " - " + TextFormatting.RED + En2);
					} else if(stack.getTagCompound().getInteger("LFocus") == 2 && stack.getTagCompound().getInteger("Spell2B") == 2) {
						if(stack.getTagCompound().getString("Spell2A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Wi2);
						if(stack.getTagCompound().getString("Spell2A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Ea2);
						if(stack.getTagCompound().getString("Spell2A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Sk2);
						if(stack.getTagCompound().getString("Spell2A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Fi2);
						if(stack.getTagCompound().getString("Spell2A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + In1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + In2);
						if(stack.getTagCompound().getString("Spell2A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Ne2);
						if(stack.getTagCompound().getString("Spell2A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + En1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + En2);
					} else if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell2B") == 2) {
						if(stack.getTagCompound().getString("Spell2A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Wi2);
						if(stack.getTagCompound().getString("Spell2A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Ea2);
						if(stack.getTagCompound().getString("Spell2A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Sk2);
						if(stack.getTagCompound().getString("Spell2A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Fi2);
						if(stack.getTagCompound().getString("Spell2A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + In2);
						if(stack.getTagCompound().getString("Spell2A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Ne2);
						if(stack.getTagCompound().getString("Spell2A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + En2);
					} // lfocus && spell1b end
				} // focus end
			
			if(stack.getTagCompound().getInteger("Focus") != 2) {
				if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell2B") == 1) {
					if(stack.getTagCompound().getString("Spell2A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Wi2);
					if(stack.getTagCompound().getString("Spell2A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Ea2);
					if(stack.getTagCompound().getString("Spell2A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Sk2);
					if(stack.getTagCompound().getString("Spell2A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Fi2);
					if(stack.getTagCompound().getString("Spell2A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + In2);
					if(stack.getTagCompound().getString("Spell2A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Ne2);
					if(stack.getTagCompound().getString("Spell2A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + En2);
				} else if(stack.getTagCompound().getInteger("LFocus") == 2 && stack.getTagCompound().getInteger("Spell2B") == 2) {
					if(stack.getTagCompound().getString("Spell2A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Wi2);
					if(stack.getTagCompound().getString("Spell2A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Ea2);
					if(stack.getTagCompound().getString("Spell2A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Sk2);
					if(stack.getTagCompound().getString("Spell2A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Fi2);
					if(stack.getTagCompound().getString("Spell2A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + In2);
					if(stack.getTagCompound().getString("Spell2A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Ne2);
					if(stack.getTagCompound().getString("Spell2A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + En2);
				} else if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell2B") == 2) {
					if(stack.getTagCompound().getString("Spell2A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Wi2);
					if(stack.getTagCompound().getString("Spell2A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Ea2);
					if(stack.getTagCompound().getString("Spell2A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Sk2);
					if(stack.getTagCompound().getString("Spell2A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Fi2);
					if(stack.getTagCompound().getString("Spell2A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + In2);
					if(stack.getTagCompound().getString("Spell2A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Ne2);
					if(stack.getTagCompound().getString("Spell2A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + En2);
				} //lfocus end
			}//focus end
		} // spell 1 == 1 end
			
			if(stack.getTagCompound().getInteger("Spell3") == 1) {
				if(stack.getTagCompound().getInteger("Focus") == 3) {
					if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell3B") == 1) {
						if(stack.getTagCompound().getString("Spell3A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Wi2);
						if(stack.getTagCompound().getString("Spell3A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Ea2);
						if(stack.getTagCompound().getString("Spell3A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Sk2);
						if(stack.getTagCompound().getString("Spell3A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Fi1);
						if(stack.getTagCompound().getString("Spell3A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.GRAY + " - " + TextFormatting.RED + In1);
						if(stack.getTagCompound().getString("Spell3A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.RED + Ne1);
						if(stack.getTagCompound().getString("Spell3A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.GRAY + " - " + TextFormatting.RED + En1);
					} else if(stack.getTagCompound().getInteger("LFocus") == 2 && stack.getTagCompound().getInteger("Spell3B") == 2) {
						if(stack.getTagCompound().getString("Spell3A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Wi2);
						if(stack.getTagCompound().getString("Spell3A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Ea2);
						if(stack.getTagCompound().getString("Spell3A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Sk2);
						if(stack.getTagCompound().getString("Spell3A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Fi1);
						if(stack.getTagCompound().getString("Spell3A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + In1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + In1);
						if(stack.getTagCompound().getString("Spell3A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + Ne1);
						if(stack.getTagCompound().getString("Spell3A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.WHITE + En1 + TextFormatting.GRAY + " - " + TextFormatting.GREEN + En1);
					} else if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell3B") == 2) {
						if(stack.getTagCompound().getString("Spell3A") == Wind) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Wi2);
						if(stack.getTagCompound().getString("Spell3A") == Earth) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Ea2);
						if(stack.getTagCompound().getString("Spell3A") == Sky) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Sk2);
						if(stack.getTagCompound().getString("Spell3A") == Fire) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Fi1);
						if(stack.getTagCompound().getString("Spell3A") == Inferno) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + In1);
						if(stack.getTagCompound().getString("Spell3A") == Nether) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + Ne1);
						if(stack.getTagCompound().getString("Spell3A") == Ender) addlore.add("" + TextFormatting.GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.GRAY + " - " + TextFormatting.WHITE + En1);
					} // lfocus && spell1b end
				} // focus end
			
			if(stack.getTagCompound().getInteger("Focus") < 3) {
				if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell3B") == 1) {
					if(stack.getTagCompound().getString("Spell3A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Wi2);
					if(stack.getTagCompound().getString("Spell3A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Ea2);
					if(stack.getTagCompound().getString("Spell3A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Sk2);
					if(stack.getTagCompound().getString("Spell3A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Fi1);
					if(stack.getTagCompound().getString("Spell3A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + In1);
					if(stack.getTagCompound().getString("Spell3A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + Ne1);
					if(stack.getTagCompound().getString("Spell3A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.RED + En1);
				} else if(stack.getTagCompound().getInteger("LFocus") == 2 && stack.getTagCompound().getInteger("Spell3B") == 2) {
					if(stack.getTagCompound().getString("Spell3A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Wi2);
					if(stack.getTagCompound().getString("Spell3A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Ea2);
					if(stack.getTagCompound().getString("Spell3A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Sk2);
					if(stack.getTagCompound().getString("Spell3A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Fi1);
					if(stack.getTagCompound().getString("Spell3A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + In1);
					if(stack.getTagCompound().getString("Spell3A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + Ne1);
					if(stack.getTagCompound().getString("Spell3A") == Ender) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.WHITE + En1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.GREEN + En1);
				} else if(stack.getTagCompound().getInteger("LFocus") == 1 && stack.getTagCompound().getInteger("Spell3B") == 2) {
					if(stack.getTagCompound().getString("Spell3A") == Wind) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Wi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Wi2);
					if(stack.getTagCompound().getString("Spell3A") == Earth) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ea1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Ea2);
					if(stack.getTagCompound().getString("Spell3A") == Sky) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Sk1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Sk2);
					if(stack.getTagCompound().getString("Spell3A") == Fire) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Fi1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Fi1);
					if(stack.getTagCompound().getString("Spell3A") == Inferno) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + In1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + In1);
					if(stack.getTagCompound().getString("Spell3A") == Nether) addlore.add("" + TextFormatting.DARK_GRAY + "Spell: " + TextFormatting.GREEN + Ne1 + TextFormatting.DARK_GRAY + " - " + TextFormatting.WHITE + Ne1);
				} //lfocus end
			}//focus end
		} // spell 1 == 1 end
		
		
		} // stack has tagcompound end
		}
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
