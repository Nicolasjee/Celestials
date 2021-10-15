package poseidon.mod.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * blue_dye - Poseidon
 * Created using Tabula 7.0.1
 */
public class ModelBlueDye extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape3_1;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape7_1;
    public ModelRenderer shape9;
    public ModelRenderer shape9_1;
    public ModelRenderer shape11;
    public ModelRenderer shape11_1;

    public ModelBlueDye() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape9 = new ModelRenderer(this, 19, 25);
        this.shape9.setRotationPoint(5.0F, 17.0F, -1.0F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.shape7 = new ModelRenderer(this, 13, 17);
        this.shape7.setRotationPoint(5.0F, 16.0F, -1.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(shape7, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape3_1 = new ModelRenderer(this, 5, 17);
        this.shape3_1.setRotationPoint(-4.0F, 23.0F, -1.0F);
        this.shape3_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 12);
        this.shape2.setRotationPoint(-3.0F, 22.0F, -1.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape2, 0.006108652381980153F, 0.0F, 0.0F);
        this.shape11_1 = new ModelRenderer(this, 0, 28);
        this.shape11_1.setRotationPoint(3.0F, 14.0F, -1.0F);
        this.shape11_1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(shape11_1, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 16);
        this.shape1.mirror = true;
        this.shape1.setRotationPoint(2.0F, 22.0F, -1.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape7_1 = new ModelRenderer(this, 19, 17);
        this.shape7_1.setRotationPoint(-4.0F, 16.0F, -1.0F);
        this.shape7_1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.setRotateAngle(shape7_1, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape3 = new ModelRenderer(this, 5, 12);
        this.shape3.setRotationPoint(3.0F, 23.0F, -1.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 45, 0);
        this.shape6.setRotationPoint(4.0F, 15.0F, -1.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 1, 7, 8, 0.0F);
        this.setRotateAngle(shape6, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape11 = new ModelRenderer(this, 0, 23);
        this.shape11.setRotationPoint(0.0F, 14.0F, -1.0F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(shape11, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape9_1 = new ModelRenderer(this, 13, 24);
        this.shape9_1.setRotationPoint(-6.0F, 17.0F, -1.0F);
        this.shape9_1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 15, 5);
        this.shape5.setRotationPoint(2.0F, 22.0F, -1.0F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(shape5, 0.0F, -1.5707963267948966F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape9.render(f5);
        this.shape7.render(f5);
        this.shape3_1.render(f5);
        this.shape2.render(f5);
        this.shape11_1.render(f5);
        this.shape1.render(f5);
        this.shape7_1.render(f5);
        this.shape3.render(f5);
        this.shape6.render(f5);
        this.shape11.render(f5);
        this.shape9_1.render(f5);
        this.shape5.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
