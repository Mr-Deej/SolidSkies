package co.matrixdevelopment.solidskies.world;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class SSWorld extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        chunkData.setRegion(0, 0, 0, 16, 16, 255, Material.AIR);
        if (x == 0 && z == 0) {
            chunkData.setBlock(8, 20, 8, Material.BEDROCK);
        }
        return chunkData;
    }

}