package poseidon.mod.world.village.travellingmerchant;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import poseidon.mod.world.village.VillageHouseDrug;

public class VillageHouseMerchantCreationHandler implements VillagerRegistry.IVillageCreationHandler
{
    
    /* (non-Javadoc)
     * @see net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler#getVillagePieceWeight(java.util.Random, int)
     */
    @Override
    public PieceWeight getVillagePieceWeight(Random parRandom, int parType)
    {
        // DEBUG
        System.out.println("Getting village MERCHANT cloud piece weight");
        
        return new PieceWeight(getComponentClass(), 5, 3);
    }

    /* (non-Javadoc)
     * @see net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler#getComponentClass()
     */
    @Override
    public Class<? extends Village> getComponentClass()
    {
        return VillageHouseMerchant.class;
    }

    /* (non-Javadoc)
     * @see net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler#buildComponent(net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight, net.minecraft.world.gen.structure.StructureVillagePieces.Start, java.util.List, java.util.Random, int, int, int, net.minecraft.util.EnumFacing, int)
     */
    @Override
    public Village buildComponent(
            PieceWeight parPieceWeight, 
            Start parStart,
            List<StructureComponent> parPiecesList, 
            Random parRand, 
            int parMinX, int parMinY, int parMinZ,
            EnumFacing parFacing, 
            int parType
            )
    {
        // DEBUG
        System.out.println("Village House MERCHANT buildComponent() at "+parMinX+", "+parMinY+", "+parMinZ);
        
        StructureBoundingBox structBB = StructureBoundingBox.getComponentToAddBoundingBox(parMinX, parMinY, parMinZ, 0, 0, 0, 9, 7, 12, parFacing);
        return new VillageHouseMerchant(parStart, parType, parRand, structBB, parFacing);
    }

}