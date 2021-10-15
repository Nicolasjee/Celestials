package poseidon.mod.objects.block.general.chest.sand;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.util.Reference;

public class GuiSandChest extends GuiContainer {

	private static final ResourceLocation GUI_CHEST = new ResourceLocation(Reference.MODID + ":textures/gui/sand_chest.png");
	private final InventoryPlayer playerInv;
	private final TileEntitySandChest te;
	
	public GuiSandChest(InventoryPlayer playerInventory, TileEntitySandChest chestINventory, EntityPlayer plyaer) {
		super(new ContainerSandChest(playerInventory, chestINventory, plyaer));
		this.playerInv = playerInventory;
		this.te = chestINventory;
		
		this.xSize = 179;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 92, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_CHEST);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	
	
}
