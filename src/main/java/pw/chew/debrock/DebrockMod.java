package pw.chew.debrock;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;

@Mod(
    modid = "debrock",
    version = "2.0.0",
    acceptedMinecraftVersions = "[1.12.2]"
)
public class DebrockMod implements IWorldGenerator {
    public static final String MODID = "debrock";
    public static final String VERSION = "1.0.0";

    @Instance("debrock")
    public static DebrockMod mod;

    DebrockInstance instance = new DebrockInstance();

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        chunkX *= 16;
        chunkZ *= 16;
        if (world.provider.getDimension() == 0) {
            this.instance.generateSurface(world, random, chunkX, chunkZ);
        }
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(this, 5);
        this.instance.load(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            OBJLoader.INSTANCE.addDomain("debrock");
        }

        DebrockInstance.instance = mod;
        this.instance.preInit();
    }
}
