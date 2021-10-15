package poseidon.mod.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import poseidon.mod.Main;
import poseidon.mod.client.enchantments.EnchantmentAdvancedMining;
import poseidon.mod.client.enchantments.EnchantmentEnlightening;
import poseidon.mod.client.enchantments.EnchantmentExperience;
import poseidon.mod.client.enchantments.EnchantmentIllumination;
import poseidon.mod.client.enchantments.EnchantmentLavaWalker;
import poseidon.mod.client.enchantments.EnchantmentPerception;
import poseidon.mod.client.enchantments.EnchantmentUnbreakable;
import poseidon.mod.networking.MessageGeneral;
import poseidon.mod.objects.block.general.elytroncrop.ElytronCrop;
import poseidon.mod.objects.tools.Multi;
import poseidon.mod.util.Reference;
import poseidon.mod.util.Utilities;
import poseidon.mod.util.handlers.EventHelper;
import poseidon.mod.util.helpers.AdvancedMiningEventHelper;
import poseidon.mod.util.helpers.AdvancedMiningUtil;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class EnchantmentInit {

	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	
	public static final Enchantment UNBREAKABLE = new EnchantmentUnbreakable();
	public static final Enchantment LAVA_WALKER = new EnchantmentLavaWalker();
	public static final Enchantment ADVANCEDMINING = new EnchantmentAdvancedMining();
	public static final Enchantment EXPERIENCE_BOOST = new EnchantmentExperience();
	public static final Enchantment ENLIGHTEN = new EnchantmentEnlightening();
	public static final Enchantment ILLUMINATION = new EnchantmentIllumination();
	public static final Enchantment PERCEPTION = new EnchantmentPerception(); //glowing enemies / find diamonds
	
	//public static final Enchantment SLIME = new Slime();
	
	private static boolean chop;
	
	@SubscribeEvent
	public static void UnbreakableFunction(LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		int level = EnchantmentHelper.getMaxEnchantmentLevel(UNBREAKABLE, living);
		BlockPos pos = living.getPosition();
		World world = event.getEntity().world;
		EntityPlayer player;
		
		if(event.getEntityLiving() instanceof EntityLivingBase) {
			EntityLivingBase z = (EntityLivingBase) event.getEntityLiving();
			if(z.isPotionActive(PotionInit.BADJUJU)) {
				Main.network.sendToServer(new MessageGeneral(z.getEntityId()));
				z.attackEntityFrom(DamageSource.MAGIC, 2.0F);
			}
		}
		
		if(event.getEntityLiving() instanceof EntityPlayer) { player = (EntityPlayer) event.getEntityLiving();
		
			NBTTagList enchlist2 = player.inventory.armorItemInSlot(0).getEnchantmentTagList();
		    for(int i = 0; i < enchlist2.tagCount(); i++) {
		    	if (enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(LAVA_WALKER)) {
		    
		    		 if (living.onGround) {
		    			 EventHelper.setNormalLava(living, world, pos);
		    		 }
		        }
		    }
		    
			NBTTagList enchlist3 = player.inventory.armorItemInSlot(3).getEnchantmentTagList();
		    for(int i = 0; i < enchlist3.tagCount(); i++) {
		    	if (enchlist3.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(ENLIGHTEN)) {
		    
		    		 if (living.onGround && !world.isRemote) {
		    			 EventHelper.setLight(living, world, pos);
		    		 }
		        }
		    }
		    
		    
		    for(int a = 0; a < player.inventory.getSizeInventory(); a++) {
		    	
		    	ItemStack cuStack = player.inventory.getStackInSlot(a);
		    	NBTTagList enchlist = cuStack.getEnchantmentTagList();
			    for(int b = 0; b < enchlist.tagCount(); b++) {
			    	if (enchlist.getCompoundTagAt(b).getInteger("id") == Enchantment.getEnchantmentID(UNBREAKABLE)) {
			    		cuStack.setItemDamage(-1);
			    	}
			    }
		    }
		    
		    //second 
		    
		    
		}
	}
	
	@SubscribeEvent
	public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
		
		//System.out.println("light: " + event.getWorld().getBlockState(event.getPos()).getLightValue() + ", or?: " + event.getWorld().getBlockState(event.getPos()).getBlock().getLightValue(event.getWorld().getBlockState(event.getPos())));
		
		if(event.getEntityPlayer() != null) {
			
			EntityPlayer player = event.getEntityPlayer();
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			
			ItemStack stack = event.getItemStack();
			NBTTagList enchlist2 = stack.getEnchantmentTagList();
			
			boolean hasLooting = false;
			boolean shouldSmelt = false;
		    int lootingLvl = 0;
				
		    
				for(int i = 0; i < enchlist2.tagCount(); i++) {
					
					if (enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(ADVANCEDMINING)) {
						
						if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 4) shouldSmelt = true;
						
						if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 4) {
							
							float x = (float) player.getLookVec().x;
						    float y = (float) player.getLookVec().y;
						    float z = (float) player.getLookVec().z;
						    
				    		for(int k = 0; k < enchlist2.tagCount(); k++) {
				    			if(enchlist2.getCompoundTagAt(k).getInteger("id") == Enchantment.getEnchantmentID(Enchantments.FORTUNE)) {
						    		hasLooting = true;
						    		lootingLvl = enchlist2.getCompoundTagAt(i).getInteger("lvl");
						    	}
				    		}
						    
				    		
							if(player.isSneaking()) {
								stack.damageItem(3, player);
								AdvancedMiningEventHelper.removeBlocks(player.getHorizontalFacing().getFacingFromVector(x, y, z), world, pos, shouldSmelt, hasLooting, lootingLvl);
							}
						
							
							
						}
					}
			    }
				
				
		}
	}
	
	@SubscribeEvent
	public static void onEvent(BlockEvent.BreakEvent event) {
		
		EntityPlayer player = event.getPlayer();
		boolean hasLooting = false;
		boolean shouldSmelt = false;
		int lootingLvl = 0;
		
		if(!(player == null)) {
			
			ItemStack stack = player.getHeldItemMainhand();
			NBTTagList enchlist2 = stack.getEnchantmentTagList();
			
			if(stack.getItem() instanceof Multi) {
				
				if(Utilities.isShovelMaterial(event.getState())) {
					
			    	Random rn = new Random();
					int maximum = 100;
					int minimum = 1;
					int range = maximum - minimum + 1;		
					int rand = rn.nextInt(range) + minimum;
					
					if(rand < 55)  {
			    		int d = stack.getItemDamage();
			    		stack.setItemDamage(d - 1);
					}
					
				}
				
			}
			
		    for(int i = 0; i < enchlist2.tagCount(); i++) {
		    	
		    	if (enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(EXPERIENCE_BOOST)) {
		    		
		    		IBlockState state = event.getState();
		    		Block block = state.getBlock();
		    		int b = block.getExpDrop(state, event.getWorld(), event.getPos(), 0);
		    		
		    		
		    		if(state.getBlock() == Blocks.COAL_ORE) {
		    			System.out.println("coal");
		    			event.setExpToDrop(b*9);
		    		}
		    		if(state.getBlock() == Blocks.LAPIS_ORE) {
		    			event.setExpToDrop(b*9);
		    			System.out.println("lapis");
		    		}
		    		if(state.getBlock() == Blocks.REDSTONE_ORE) {
		    			event.setExpToDrop(b*9);
		    			System.out.println("redst");
		    		}
		    		if(state.getBlock() == Blocks.DIAMOND_ORE) {
		    			event.setExpToDrop(b*9);
		    			System.out.println("ddia");
		    		}
		    		if(state.getBlock() == Blocks.EMERALD_ORE) {
		    			event.setExpToDrop(b*9);
		    			System.out.println("em");
		    		}
		    		if(state.getBlock() == Blocks.QUARTZ_BLOCK) {
		    			event.setExpToDrop(b*12);
		    			System.out.println("q");
		    		}

		    		
		    	}
		    	
		    	
		    	
		    	//----
		    	
		    	if (enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(UNBREAKABLE)) {
		    		
		    		int d = stack.getItemDamage();
		    		stack.setItemDamage(d - 1);

		    	}
		    	
		    	if(enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(ADVANCEDMINING)) {
		    		Random rand = new Random();
					int num = 1 + rand.nextInt(100);
					int d = stack.getItemDamage();
					if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 1 && num < 5) stack.setItemDamage(d - 1);
					if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 2 && num < 15) stack.setItemDamage(d - 1);
					if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 3 && num < 25) stack.setItemDamage(d - 1);
					if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 4 && num < 35) stack.setItemDamage(d - 1);
		    	}
		    	
		    	if(enchlist2.getCompoundTagAt(i).getInteger("lvl") == 4 && enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(ADVANCEDMINING)) shouldSmelt = true;
		    	
		    	if(enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(ADVANCEDMINING) && enchlist2.getCompoundTagAt(i).getInteger("lvl") >= 3) {
		    		
		    		for(int k = 0; k < enchlist2.tagCount(); k++) {
		    			if(enchlist2.getCompoundTagAt(k).getInteger("id") == Enchantment.getEnchantmentID(Enchantments.FORTUNE)) {
				    		hasLooting = true;
				    		lootingLvl = enchlist2.getCompoundTagAt(i).getInteger("lvl");
				    	}
		    		}
		    		
		    	
		    		
					if(event.getState() == Blocks.COAL_ORE.getDefaultState()) 
						AdvancedMiningUtil.removeBlocks(event.getPos(), event.getWorld(), stack, player, hasLooting, shouldSmelt, lootingLvl, Blocks.COAL_ORE);
					
					if(event.getState() == Blocks.LIT_REDSTONE_ORE.getDefaultState()) 
						AdvancedMiningUtil.removeBlocks(event.getPos(), event.getWorld(), stack, player, hasLooting, shouldSmelt, lootingLvl, Blocks.REDSTONE_ORE);
					
					if(event.getState() == Blocks.IRON_ORE.getDefaultState()) 
						AdvancedMiningUtil.removeBlocks(event.getPos(), event.getWorld(), stack, player, hasLooting, shouldSmelt, lootingLvl, Blocks.IRON_ORE);
					
					if(event.getState() == Blocks.GOLD_ORE.getDefaultState()) 
						AdvancedMiningUtil.removeBlocks(event.getPos(), event.getWorld(), stack, player, hasLooting, shouldSmelt, lootingLvl, Blocks.GOLD_ORE);
					
					if(event.getState() == Blocks.QUARTZ_ORE.getDefaultState())
						AdvancedMiningUtil.removeBlocks(event.getPos(), event.getWorld(), stack, player, hasLooting, shouldSmelt, lootingLvl, Blocks.QUARTZ_ORE);
					
					if(AdvancedMiningUtil.isStone(event.getState())) {
						AdvancedMiningUtil.removeBlockStoneCall(event.getPos(), event.getWorld(), stack, player, hasLooting, shouldSmelt, lootingLvl, Blocks.STONE, event.getState()); // Stone will not drop. First check which one through worldIn.getBlockState(event.getPos());
					}
					
		    	}
		    	
		    
		    	
		    }
		    
		    if(stack.getItem() == ItemInit.OBSIDIAN_HAMMER) {
		    	if(event.getState() != Blocks.OBSIDIAN.getDefaultState()) {
		    		if(event.isCancelable()) event.setCanceled(true);
		    	}
		    }
		    
		    
		}
	}
	
	@SubscribeEvent
	public static void onBlockHarvested(HarvestDropsEvent event) {

		if(event.getHarvester() != null && event.getHarvester() instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) event.getHarvester();
			
			ItemStack stack = player.getHeldItemMainhand();
			NBTTagList enchlist2 = stack.getEnchantmentTagList();
		    for(int i = 0; i < enchlist2.tagCount(); i++) {

		    	if (enchlist2.getCompoundTagAt(i).getInteger("id") == Enchantment.getEnchantmentID(ADVANCEDMINING)) {
		    		
		    		int count = 1;
		    		if(enchlist2.getCompoundTagAt(i).getInteger("lvl") >= 3) count = AdvancedMiningUtil.getItemStackCount();
		    		boolean flag = AdvancedMiningUtil.isFurnaceResultOre(event.getState().getBlock());
		    		boolean isStone = AdvancedMiningUtil.isStone(event.getState());
		    		
					if(flag && enchlist2.getCompoundTagAt(i).getInteger("lvl") >= 2) {
						ItemStack stack2 = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(event.getState().getBlock()));
						stack2.setCount(count);
						if(stack2 != null) {
							event.getDrops().clear();
							event.getDrops().add(stack2.copy());
						}
					}
					
					if(isStone && enchlist2.getCompoundTagAt(i).getInteger("lvl") >= 1) {
						ItemStack stack2 = AdvancedMiningUtil.getStone(event.getState());
						if(stack2 != null) {
							event.getDrops().clear();
							event.getDrops().add(stack2.copy());
						}
					}
					
		    	}
		    }
		}
		
		if(event.getHarvester() != null) {	
			EntityPlayer player = event.getHarvester();
			ItemStack held = player.getHeldItemMainhand();
			IBlockState state = event.getState();	
			
			
		} 
		
	    if(event.getState() == BlockInit.WET_MODSPONGE.getDefaultState() && event.getHarvester() instanceof EntityPlayer) {
	    	BlockPos pos = event.getPos();
	    	EntityPlayer player = (EntityPlayer) event.getHarvester();
	    	ItemStack stack = player.getHeldItemMainhand();
	    	if(stack.getItem() != ItemInit.OBSIDIAN_HAMMER) {
	    		if(!event.getWorld().isRemote) {
	    			int c = 0;
	    			List<BlockPos> b = Utilities.getSpongeBlocks(event.getPos());
	    			
	    			for(int i = 0; i < b.size(); i++) {
	    				if(event.getWorld().getBlockState(b.get(i)) == BlockInit.MOD_AIR.getDefaultState()) {
	    					event.getWorld().setBlockState(b.get(i), Blocks.WATER.getDefaultState());
	    					c++;
	    				}
	    			}
	    			
	    			if(c == 0) {
						event.getDrops().clear();
						event.getDrops().add(new ItemStack(Item.getItemFromBlock(BlockInit.WET_MODSPONGE)));
	    				} else if(c > 0) {
	    				event.getDrops().clear();
	    				event.getDrops().add(new ItemStack(Item.getItemFromBlock(BlockInit.MODSPONGE)));
	    			}
	    		}
	    	}
	    }
	    
	    if(event.getState() == BlockInit.BURNINGLAVASPONGE.getDefaultState() && event.getHarvester() instanceof EntityPlayer) {
	    	BlockPos pos = event.getPos();
	    	EntityPlayer player = (EntityPlayer) event.getHarvester();
	    	ItemStack stack = player.getHeldItemMainhand();
	    	if(stack.getItem() != ItemInit.OBSIDIAN_HAMMER) {
	    		if(!event.getWorld().isRemote) {
	    			
	    			List<BlockPos> b =  Utilities.getSpongeBlocks(event.getPos());
	    			int c = 0;
	    			for(int i = 0; i < b.size(); i++) {
	    				if(event.getWorld().getBlockState(b.get(i)) == BlockInit.MOD_AIR.getDefaultState()) {
	    					event.getWorld().setBlockState(b.get(i), Blocks.LAVA.getDefaultState());
	    					c++;
	    				}
	    			}
	    			if(c == 0) {
						event.getDrops().clear();
						event.getDrops().add(new ItemStack(Item.getItemFromBlock(BlockInit.BURNINGLAVASPONGE)));
	    				} else if(c > 0) {
	    				event.getDrops().clear();
	    				event.getDrops().add(new ItemStack(Item.getItemFromBlock(BlockInit.LAVASPONGE)));
	    			}
	    		}
	    	}
	    }
	    
	    if(event.getState() == Blocks.MAGMA.getDefaultState() && event.getHarvester() instanceof EntityPlayer) {
	    	if(event.getHarvester().getHeldItemMainhand().getItem() == ItemInit.OBSIDIAN_HAMMER) {
	    		
	    		int c = Utilities.getRandom(2, 1);
	    		
	    		event.getDrops().clear();
	    		event.getDrops().add(new ItemStack(ItemInit.HOT_COALS, c));
	    		
	    	}
	    }
	    
	    
	}

}
