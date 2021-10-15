package poseidon.mod.objects.block.expansiontable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import poseidon.mod.util.Reference;

public class GuiExpansionTable extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/expansion_table.png");
	private final InventoryPlayer player;
	private final TileEntityExpansionTable table;
	
	public GuiExpansionTable(InventoryPlayer player, TileEntityExpansionTable tile) {
		super(new ContainerExpansionTable(player, tile));
		this.player = player;
		this.table = tile;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	    this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.table.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 5, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 90 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F,  1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityExpansionTable.isBurning(table)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 54 + 12 - k, 176, 12 - k, 13, k + 1);
		}
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 4, this.guiTop + 36, 176, 14, l + 1, 16);
	}
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.table.getField(1);
		if(i == 0) i = 200;
		return this.table.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.table.getField(2);
		int j = this.table.getField(3);
		return j != 0 && i != 0 ? i *pixels / j : 0;
	}
	
	
	
}
