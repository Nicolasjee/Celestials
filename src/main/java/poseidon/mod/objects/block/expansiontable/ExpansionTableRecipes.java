package poseidon.mod.objects.block.expansiontable;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import poseidon.mod.init.ItemInit;

public class ExpansionTableRecipes {
	
	private static final ExpansionTableRecipes INSTANCE = new ExpansionTableRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static ExpansionTableRecipes getInstance(){
		return INSTANCE;
	}
	
	private ExpansionTableRecipes() {



	//	addExpansionTableRecipe(new ItemStack(ItemInit.KNOWLEDGE_BOOK), new ItemStack(Items.PAPER), new ItemStack(Items.));

		
		//-----------------------------------------------------------------------------------------------------------------------------------
		

		

		

	}
	
	
	
	public void addExpansionTableRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if(getExpansionTableResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getExpansionTableResult(ItemStack input1, ItemStack input2) {
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
			
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())  {
					
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2){
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() {
		return this.smeltingList;
	}
	
	public float getExpansionTableExperience(ItemStack stack){
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 5.0F;
	}
	

}
