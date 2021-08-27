package net.fabricmc.example;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Random;

public class HouseBlockEntity extends BlockEntity {
    HashMap<BlockPos, BlockState> map = new HashMap<>();
    public HouseBlockEntity(BlockPos pos, BlockState state) {
        super(ExampleMod.houseBlockEntityBlockEntityType, pos, state);
        for(BlockPos pos1 : HouseBlock.map.keySet()) {
            map.put(pos1, HouseBlock.map.get(pos1));
        }

    }
    public static <T extends BlockEntity> void tick(World world, BlockPos blockPos, BlockState state, T t) {
        if(world.isClient())
            return;
        ((HouseBlockEntity)t).tick(world, blockPos, state);
    }

    private void tick(World world, BlockPos blockPos, BlockState state) {
        for(int i = 0; i < 3; i++) {
            Object[] positions = map.keySet().toArray();
            if (positions.length > 1) {
                Random random = new Random();
                if (positions[0] instanceof BlockPos) {
                    BlockPos pos = (BlockPos) positions[random.nextInt(positions.length - 1)];
                    world.setBlockState(pos.add(blockPos), map.get(pos));
                    world.playSound(null, pos.add(blockPos), map.get(pos).getBlock().getSoundGroup(map.get(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1f, 1f);
                    world.setBlockState(new BlockPos(1, 4, 2).add(blockPos), map.get(new BlockPos(1, 4, 2)));
                    world.updateNeighbors(pos.add(blockPos), map.get(pos).getBlock());
                    map.remove(pos);
                }
            }
        }
    }

}
