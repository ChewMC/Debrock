package pw.chew.debrock.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockDebrock extends Block {
    public BlockDebrock() {
        super(Material.ROCK);
        this.setSoundType(SoundType.STONE);
    }

    public void onBlockAdded(World world, BlockPos pos, @Nonnull IBlockState state) {
        world.scheduleUpdate(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), this, this.tickRate(world));
    }

    public void randomDisplayTick(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Random random) {
        for (int i = 0; i < 4; i++) {
            double xCoord = (float) pos.getX() + random.nextFloat();
            double yCoord = (float) pos.getY() + random.nextFloat();
            double zCoord = (float) pos.getZ() + random.nextFloat();
            double xSpeed = ((double) random.nextFloat() - 0.5) * 0.5;
            double ySpeed = ((double) random.nextFloat() - 0.5) * 0.5;
            double zSpeed = ((double) random.nextFloat() - 0.5) * 0.5;
            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
        }
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    public int quantityDropped(@Nonnull Random random) {
        return 0;
    }

    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random random, int fortune) {
        return (new ItemStack(Blocks.AIR)).getItem();
    }
}
