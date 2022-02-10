package pw.chew.debrock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import pw.chew.debrock.blocks.BlockDebrock;

import java.util.Random;

public class DebrockInstance {
    // The debrock block itself
    public static BlockDebrock block = (BlockDebrock) (new BlockDebrock())
        // Make it instantly break
        .setHardness(0.0F)
        .setResistance(0.0F)
        // No light level
        .setLightLevel(0.0F)
        .setLightOpacity(0)
        // Other stuff
        .setTranslationKey("debrock")
        .setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public static Item item;
    public static Object instance;

    /**
     * Sets up everything
     */
    public void preInit() {
        block.setRegistryName("debrock");
        ForgeRegistries.BLOCKS.register(block);
        item = (new ItemBlock(block)).setRegistryName(block.getRegistryName());
        ForgeRegistries.ITEMS.register(item);
    }

    /**
     * Renders the item on the client
     *
     * @param event The event
     */
    public void load(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("debrock:debrock", "inventory"));
        }
    }

    /**
     * The actual generation of the Debrock
     * @param world The world
     * @param rand The random
     * @param chunkX The chunk X
     * @param chunkZ The chunk Z
     */
    public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for (int i = 0; i < 10; ++i) {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(5) + 1;
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(block.getDefaultState(), 16)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
        }
    }

    // Set the harvest level
    static {
        block.setHarvestLevel("pickaxe", 4);
    }
}
