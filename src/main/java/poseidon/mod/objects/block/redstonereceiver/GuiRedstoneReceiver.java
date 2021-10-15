package poseidon.mod.objects.block.redstonereceiver;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.util.Reference;
import poseidon.mod.objects.block.redstonereceiver.TileEntityRedstoneReceiver;
import poseidon.mod.objects.block.redstonereceiver.ContainerRedstoneReceiver;

public class GuiRedstoneReceiver extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/redstone_receiver.png");
	private final InventoryPlayer player;
	private final TileEntityRedstoneReceiver te;
	
	public GuiRedstoneReceiver(InventoryPlayer player, TileEntityRedstoneReceiver tile) {
		super(new ContainerRedstoneReceiver(player, tile));
		this.player = player;
		this.te = tile;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	    this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 5, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 90 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F,  1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		this.fontRenderer.drawString("Power: ", 214, 60, 16777215);
		this.fontRenderer.drawString(Integer.toString(te.power), 214 + this.fontRenderer.getStringWidth("Power: "), 60, 16731213);
		
	}
	
	
	
	
}
