package net.fabricmc.example;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static net.minecraft.block.HorizontalConnectingBlock.*;
import static net.minecraft.block.LeavesBlock.PERSISTENT;
import static net.minecraft.block.StairsBlock.*;

public class HouseBlock extends BlockWithEntity {
    public HouseBlock(Settings settings) {
        super(settings);
    }
    static HashMap<BlockPos, BlockState> map = new HashMap<>();

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }



    //START blockStates
    @NotNull
    public static BlockState plank = Blocks.OAK_PLANKS.getDefaultState();
    @NotNull
    public static BlockState glass_south = Blocks.GLASS_PANE.getDefaultState().with(NORTH, Boolean.TRUE).with(SOUTH, Boolean.TRUE);
    @NotNull
    public static BlockState glass_east = Blocks.GLASS_PANE.getDefaultState().with(EAST, Boolean.TRUE).with(WEST, Boolean.TRUE);
    @NotNull
    public static BlockState wood = Blocks.OAK_WOOD.getDefaultState();
    @NotNull
    public static BlockState ssstairs = Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.SOUTH);
    @NotNull
    public static BlockState nsstairs = Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.NORTH);
    @NotNull
    public static BlockState wsstairs = Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.WEST);
    @NotNull
    public static BlockState esstairs = Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST);
    @NotNull
    public static BlockState lsslab = Blocks.SPRUCE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM);
    @NotNull
    public static BlockState usslab = Blocks.SPRUCE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP);
    @NotNull
    public static BlockState sostairs = Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH);
    @NotNull
    public static BlockState nostairs = Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.NORTH);
    @NotNull
    public static BlockState wostairs = Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.WEST);
    @NotNull
    public static BlockState eostairs = Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.EAST);
    @NotNull
    public static BlockState loslab = Blocks.OAK_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM);
    @NotNull
    public static BlockState uoslab = Blocks.OAK_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP);

    static {
        map = new HashMap<>();
        map.put(new BlockPos(0, 0, 3), Blocks.SPRUCE_FENCE.getDefaultState());
        map.put(new BlockPos(0, 1, 3), Blocks.SPRUCE_FENCE.getDefaultState());
        map.put(new BlockPos(0, 2, 3), Blocks.SPRUCE_FENCE.getDefaultState());
        map.put(new BlockPos(0, 3, 3), Blocks.SPRUCE_FENCE.getDefaultState());

        map.put(new BlockPos(0, 0, 7), Blocks.SPRUCE_FENCE.getDefaultState());
        map.put(new BlockPos(0, 1, 7), Blocks.SPRUCE_FENCE.getDefaultState());
        map.put(new BlockPos(0, 2, 7), Blocks.SPRUCE_FENCE.getDefaultState());
        map.put(new BlockPos(0, 3, 7), Blocks.SPRUCE_FENCE.getDefaultState());


        //side(right from bottom
        map.put(new BlockPos(3, 0, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(4, 0, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(5, 0, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(6, 0, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(7, 0, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(8, 0, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(9, 0, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(10, 0, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(11, 0, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(12, 0, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(13, 0, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(14, 0, 0), Blocks.OAK_PLANKS.getDefaultState());



        map.put(new BlockPos(3, 1, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(4, 1, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(5, 1, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(6, 1, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(7, 1, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(8, 1, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(9, 1, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(10, 1, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(11, 1, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(12, 1, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(13, 1, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(14, 1, 0), Blocks.OAK_PLANKS.getDefaultState());


        map.put(new BlockPos(3, 2, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(4, 2, 0), glass_east);
        map.put(new BlockPos(5, 2, 0), glass_east);
        map.put(new BlockPos(6, 2, 0), glass_east);

        map.put(new BlockPos(7, 2, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(7, 2, -1), Blocks.WALL_TORCH.getDefaultState().with(FACING, Direction.NORTH));
        map.put(new BlockPos(8, 2, 0), glass_east);
        map.put(new BlockPos(9, 2, 0), glass_east);
        map.put(new BlockPos(10, 2, 0), glass_east);

        map.put(new BlockPos(11, 2, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(11, 2, -1), Blocks.WALL_TORCH.getDefaultState().with(FACING, Direction.NORTH));
        map.put(new BlockPos(12, 2, 0), glass_east);
        map.put(new BlockPos(13, 2, 0), glass_east);
        map.put(new BlockPos(14, 2, 0), glass_east);


        map.put(new BlockPos(3, 3, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(4, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(5, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(6, 3, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(7, 3, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(8, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(9, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(10, 3, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(11, 3, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(12, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(13, 3, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(14, 3, 0), Blocks.OAK_PLANKS.getDefaultState());


        map.put(new BlockPos(3, 4, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(4, 4, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(5, 4, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(6, 4, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(7, 4, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(8, 4, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(9, 4, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(10, 4, 0), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(11, 4, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(12, 4, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(13, 4, 0), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(14, 4, 0), Blocks.OAK_PLANKS.getDefaultState());

        //side(left from bottom)

        map.put(new BlockPos(7, 0, 10), wood);
        map.put(new BlockPos(4, 0, 10), plank);
        map.put(new BlockPos(5, 0, 10), plank);
        map.put(new BlockPos(6, 0, 10), plank);

        map.put(new BlockPos(11, 0, 10), wood);
        map.put(new BlockPos(10, 0, 10), plank);
        map.put(new BlockPos(9, 0, 10), plank);
        map.put(new BlockPos(8, 0, 10), plank);

        map.put(new BlockPos(12, 0, 10), plank);
        map.put(new BlockPos(13, 0, 10), plank);
        map.put(new BlockPos(14, 0, 10), plank);

        map.put(new BlockPos(15, 1, 10), wood);
        map.put(new BlockPos(14, 1, 10), plank);
        map.put(new BlockPos(13, 1, 10), plank);
        map.put(new BlockPos(12, 1, 10), plank);

        map.put(new BlockPos(7, 1, 10), wood);
        map.put(new BlockPos(4, 1, 10), plank);
        map.put(new BlockPos(5, 1, 10), plank);
        map.put(new BlockPos(6, 1, 10), plank);

        map.put(new BlockPos(11, 1, 10), wood);
        map.put(new BlockPos(10, 1, 10), plank);
        map.put(new BlockPos(9, 1,10), plank);
        map.put(new BlockPos(8, 1, 10), plank);


        map.put(new BlockPos(15, 2, 10), wood);
        map.put(new BlockPos(14, 2, 10), glass_east);
        map.put(new BlockPos(13, 2, 10), glass_east);
        map.put(new BlockPos(12, 2, 10), glass_east);

        map.put(new BlockPos(7, 2, 10), wood);
        map.put(new BlockPos(7, 2, 11), Blocks.WALL_TORCH.getDefaultState().with(FACING, Direction.SOUTH));
        map.put(new BlockPos(4, 2, 10), glass_east);
        map.put(new BlockPos(5, 2, 10), glass_east);
        map.put(new BlockPos(6, 2, 10), glass_east);

        map.put(new BlockPos(11, 2, 10), wood);
        map.put(new BlockPos(11, 2, 11), Blocks.WALL_TORCH.getDefaultState().with(FACING, Direction.SOUTH));
        map.put(new BlockPos(10, 2, 10), glass_east);
        map.put(new BlockPos(9, 2,10), glass_east);
        map.put(new BlockPos(8, 2, 10), glass_east);


        map.put(new BlockPos(15, 3, 10), wood);
        map.put(new BlockPos(14, 3, 10), plank);
        map.put(new BlockPos(13, 3, 10), plank);
        map.put(new BlockPos(12, 3, 10), plank);

        map.put(new BlockPos(7, 3, 10), wood);
        map.put(new BlockPos(4, 3, 10), plank);
        map.put(new BlockPos(5, 3, 10), plank);
        map.put(new BlockPos(6, 3, 10), plank);

        map.put(new BlockPos(11, 3, 10), wood);
        map.put(new BlockPos(10, 3, 10), plank);
        map.put(new BlockPos(9, 3,10), plank);
        map.put(new BlockPos(8, 3, 10), plank);

        map.put(new BlockPos(15, 4, 10), wood);
        map.put(new BlockPos(14, 4, 10), plank);
        map.put(new BlockPos(13, 4, 10), plank);
        map.put(new BlockPos(12, 4, 10), plank);

        map.put(new BlockPos(7, 4, 10), wood);
        map.put(new BlockPos(4, 4, 10), plank);
        map.put(new BlockPos(5, 4, 10), plank);
        map.put(new BlockPos(6, 4, 10), plank);

        map.put(new BlockPos(11, 4, 10), wood);
        map.put(new BlockPos(10, 4, 10), plank);
        map.put(new BlockPos(9, 4,10), plank);
        map.put(new BlockPos(8, 4, 10), plank);






        //front
        map.put(new BlockPos(3, 0, 10), wood);
        map.put(new BlockPos(3, 0, 9), plank);
        map.put(new BlockPos(3, 0, 8), plank);
        map.put(new BlockPos(3, 0, 7), plank);
        map.put(new BlockPos(3, 0, 6), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(3, 0, 5), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(2, 0, 5), plank);
        map.put(new BlockPos(2, 0, 6), Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.NORTH));
        map.put(new BlockPos(2, 0, 4), Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));
        map.put(new BlockPos(2, 0, 3), Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));//   WHAT THE ACCTUAL FING HELL MOJANG!?!?!?!?!?!?!?!?!?!?!? WHY IS THAT SO HARD TO FIGURE OUT LMFAO?!?!?!?!?!?!?!?!?!?!?!??!? REEEEEEEEEEEE::: gamma later: wow theres more bullshit than I thought
        map.put(new BlockPos(2, 0, 2), Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
        map.put(new BlockPos(2,0,1), Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
        map.put(new BlockPos(2, 0, 7), Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
        map.put(new BlockPos(2, 0, 8), Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
        map.put(new BlockPos(2, 0, 9), Blocks.SPRUCE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
        map.put(new BlockPos(1, 0, 5), Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.EAST));
        map.put(new BlockPos(1, 0, 4), Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(SHAPE, StairShape.OUTER_RIGHT));
        map.put(new BlockPos(1, 0, 6), Blocks.OAK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(SHAPE, StairShape.OUTER_LEFT));

        map.put(new BlockPos(3, 0, 4), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(3, 0, 3), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 0, 2), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 0, 1), Blocks.OAK_PLANKS.getDefaultState());

        map.put(new BlockPos(3, 1, 4), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(3, 1, 3), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 1, 2), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 1, 1), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 1, 10), wood);
        map.put(new BlockPos(3, 1, 9), plank);
        map.put(new BlockPos(3, 1, 8), plank);
        map.put(new BlockPos(3, 1, 7), plank);
        map.put(new BlockPos(3, 1, 6), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(3, 1, 5), Blocks.DARK_OAK_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.LOWER));
        map.put(new BlockPos(3, 2, 5), Blocks.DARK_OAK_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER));
        map.put(new BlockPos(2, 2, 6), Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST));
        map.put(new BlockPos(2, 2, 4), Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST));
        map.put(new BlockPos(3, 3, 5), wood);
        map.put(new BlockPos(3, 4, 5), wood);

        map.put(new BlockPos(2, 1, 3), Blocks.OAK_LEAVES.getDefaultState().with(PERSISTENT, Boolean.TRUE));
        map.put(new BlockPos(2, 1, 2), Blocks.OAK_LEAVES.getDefaultState().with(PERSISTENT, Boolean.TRUE));
        map.put(new BlockPos(2,1,1), Blocks.OAK_LEAVES.getDefaultState().with(PERSISTENT, Boolean.TRUE));
        map.put(new BlockPos(2, 1, 7), Blocks.OAK_LEAVES.getDefaultState().with(PERSISTENT, Boolean.TRUE));
        map.put(new BlockPos(2, 1, 8), Blocks.OAK_LEAVES.getDefaultState().with(PERSISTENT, Boolean.TRUE));
        map.put(new BlockPos(2, 1, 9), Blocks.OAK_LEAVES.getDefaultState().with(PERSISTENT, Boolean.TRUE));



        map.put(new BlockPos(3, 2, 4), wood);
        map.put(new BlockPos(3, 2, 3), glass_south);
        map.put(new BlockPos(3, 2, 2), glass_south);
        map.put(new BlockPos(3, 2, 1), glass_south);
        map.put(new BlockPos(3, 2, 10), wood);
        map.put(new BlockPos(3, 2, 9), glass_south);
        map.put(new BlockPos(3, 2, 8), glass_south);
        map.put(new BlockPos(3, 2, 7), glass_south);
        map.put(new BlockPos(3, 2, 6), wood);

        map.put(new BlockPos(3, 3, 4), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(3, 3, 3), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 3, 2), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 3, 1), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 3, 10), wood);
        map.put(new BlockPos(3, 3, 9), plank);
        map.put(new BlockPos(3, 3, 8), plank);
        map.put(new BlockPos(3, 3, 7), plank);
        map.put(new BlockPos(3, 3, 6), Blocks.OAK_WOOD.getDefaultState());

        map.put(new BlockPos(3, 4, 4), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(3, 4, 3), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 4, 2), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 4, 1), Blocks.OAK_PLANKS.getDefaultState());
        map.put(new BlockPos(3, 4, 10), wood);
        map.put(new BlockPos(3, 4, 9), plank);
        map.put(new BlockPos(3, 4, 8), plank);
        map.put(new BlockPos(3, 4, 7), plank);
        map.put(new BlockPos(3, 4, 6), Blocks.OAK_WOOD.getDefaultState());

        //back
        map.put(new BlockPos(15, 0, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(15, 0, 1), plank);
        map.put(new BlockPos(15, 0, 2), plank);
        map.put(new BlockPos(15, 0, 3), wood);
        map.put(new BlockPos(15, 0, 4), plank);
        map.put(new BlockPos(15, 0, 5), plank);
        map.put(new BlockPos(15, 0, 6), plank);
        map.put(new BlockPos(15, 0, 7), wood);
        map.put(new BlockPos(15, 0, 8), plank);
        map.put(new BlockPos(15, 0, 9), plank);
        map.put(new BlockPos(15, 0, 10), wood);

        map.put(new BlockPos(15, 1, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(15, 1, 1), plank);
        map.put(new BlockPos(15, 1, 2), plank);
        map.put(new BlockPos(15, 1, 3), wood);
        map.put(new BlockPos(15, 1, 4), plank);
        map.put(new BlockPos(15, 1, 5), plank);
        map.put(new BlockPos(15, 1, 6), plank);
        map.put(new BlockPos(15, 1, 7), wood);
        map.put(new BlockPos(15, 1, 8), plank);
        map.put(new BlockPos(15, 1, 9), plank);
        map.put(new BlockPos(15, 1, 10), wood);

        map.put(new BlockPos(15, 2, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(15, 2, 1), glass_south);
        map.put(new BlockPos(15, 2, 2), glass_south);
        map.put(new BlockPos(15, 2, 3), wood);
        map.put(new BlockPos(16, 2, 3), Blocks.WALL_TORCH.getDefaultState().with(FACING, Direction.EAST));
        map.put(new BlockPos(15, 2, 4), glass_south);
        map.put(new BlockPos(15, 2, 5), glass_south);
        map.put(new BlockPos(15, 2, 6), glass_south);
        map.put(new BlockPos(15, 2, 7), wood);
        map.put(new BlockPos(16, 2, 7), Blocks.WALL_TORCH.getDefaultState().with(FACING, Direction.EAST));
        map.put(new BlockPos(15, 2, 8), glass_south);
        map.put(new BlockPos(15, 2, 9), glass_south);
        map.put(new BlockPos(15, 2, 10), wood);

        map.put(new BlockPos(15, 3, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(15, 3, 1), plank);
        map.put(new BlockPos(15, 3, 2), plank);
        map.put(new BlockPos(15, 3, 3), wood);
        map.put(new BlockPos(15, 3, 4), plank);
        map.put(new BlockPos(15, 3, 5), plank);
        map.put(new BlockPos(15, 3, 6), plank);
        map.put(new BlockPos(15, 3, 7), wood);
        map.put(new BlockPos(15, 3, 8), plank);
        map.put(new BlockPos(15, 3, 9), plank);
        map.put(new BlockPos(15, 3, 10), wood);

        map.put(new BlockPos(15, 4, 0), Blocks.OAK_WOOD.getDefaultState());
        map.put(new BlockPos(15, 4, 1), plank);
        map.put(new BlockPos(15, 4, 2), plank);
        map.put(new BlockPos(15, 4, 3), wood);
        map.put(new BlockPos(15, 4, 4), plank);
        map.put(new BlockPos(15, 4, 5), plank);
        map.put(new BlockPos(15, 4, 6), plank);
        map.put(new BlockPos(15, 4, 7), wood);
        map.put(new BlockPos(15, 4, 8), plank);
        map.put(new BlockPos(15, 4, 9), plank);
        map.put(new BlockPos(15, 4, 10), wood);


        //roof
        for(int z1 = 15; z1>2; z1--) {
            map.put(new BlockPos(z1, 4, 11), nsstairs);
        }
        map.put(new BlockPos(16, 4, 11), nsstairs.with(SHAPE, StairShape.OUTER_LEFT));
        for(int z2 = 10; z2>-1; z2--){
            map.put( new BlockPos(16, 4, z2), wsstairs);
        }
        map.put(new BlockPos(16, 4, -1), ssstairs.with(SHAPE, StairShape.OUTER_RIGHT));
        for(int z1 = 15; z1>2; z1--) {
            map.put(new BlockPos(z1, 4, -1), ssstairs);
        }
        map.put(new BlockPos(2, 4, -1), ssstairs.with(SHAPE, StairShape.OUTER_LEFT));
        map.put(new BlockPos(2, 4, 0), esstairs);
        map.put(new BlockPos(2, 4, 1), esstairs);
        map.put(new BlockPos(2, 4, 2), eostairs);
        map.put(new BlockPos(2, 4, 11), nsstairs.with(SHAPE, StairShape.OUTER_RIGHT));
        map.put(new BlockPos(2, 4, 10), esstairs);
        map.put(new BlockPos(2, 4, 9), esstairs);
        map.put(new BlockPos(2, 4, 8), eostairs);

        map.put(new BlockPos(1, 4, 9), lsslab);
        map.put(new BlockPos(1, 4, 8), lsslab);
        map.put(new BlockPos(0, 4, 8), lsslab);
        map.put(new BlockPos(0, 4, 7), lsslab);
        map.put(new BlockPos(0, 4, 6), lsslab);
        map.put(new BlockPos(1, 4, 6), uoslab);
        map.put(new BlockPos(2, 4, 6), uoslab);
        map.put(new BlockPos(2, 4, 7), plank);
        map.put(new BlockPos(1, 4, 7), plank);

        map.put(new BlockPos(0, 4, 5), usslab);
        map.put(new BlockPos(1, 4, 4), uoslab);
        map.put(new BlockPos(2, 4, 4), uoslab);
        map.put(new BlockPos(2, 4, 3), plank);
        map.put(new BlockPos(1, 4, 3), plank);
        map.put(new BlockPos(1, 4, 5), uoslab);
        map.put(new BlockPos(2, 4, 5), uoslab);
        map.put(new BlockPos(2, 5, 5), loslab);
        map.put(new BlockPos(1, 5, 5), loslab);
        map.put(new BlockPos(0, 4, 4), lsslab);
        map.put(new BlockPos(0, 4, 3), lsslab);
        map.put(new BlockPos(0, 4, 2), lsslab);
        map.put(new BlockPos(1, 4, 2), lsslab);
        map.put(new BlockPos(1, 4, 2), lsslab);
        map.put(new BlockPos(1, 4, 1), lsslab);
        for(int z3 = 14; z3>3; z3--){
            map.put(new BlockPos(z3, 5, 0), sostairs);
        }

        for(int z4 = 9; z4>0; z4--){
            map.put(new BlockPos(15, 5, z4), wostairs);
        }
        map.put(new BlockPos(3, 5, 0), sostairs.with(SHAPE, StairShape.OUTER_LEFT));
        map.put(new BlockPos(15, 5, 10), wostairs.with(SHAPE, StairShape.OUTER_RIGHT));
        map.put(new BlockPos(15, 5, 0), wostairs.with(SHAPE, StairShape.OUTER_LEFT));
        map.put(new BlockPos(3, 5, 10), nostairs.with(SHAPE, StairShape.OUTER_RIGHT));
        for(int z5 = 14; z5>3; z5--){
            map.put(new BlockPos(z5, 5, 10), nostairs);
        }

        for(int z4 = 9; z4>0; z4--){
            map.put(new BlockPos(3, 5, z4), eostairs);
        }
        for(int z4 = 9; z4>0; z4--){
            map.put(new BlockPos(4, 6, z4), eostairs);
        }
        for(int z5 = 14; z5>3; z5--){
            map.put(new BlockPos(z5, 6, 9), nostairs);
        }
        for(int z3 = 14; z3>3; z3--){
            map.put(new BlockPos(z3, 6, 1), sostairs);
        }
        for(int z4 = 9; z4>0; z4--){
            map.put(new BlockPos(14, 6, z4), wostairs);
        }
        for(int z4 = 9; z4>0; z4--){
            map.put(new BlockPos(4, 5, z4), plank);
        }
        for(int z5 = 14; z5>3; z5--){
            map.put(new BlockPos(z5, 5, 9), plank);
        }
        for(int z3 = 14; z3>3; z3--){
            map.put(new BlockPos(z3, 5, 1), plank);
        }
        for(int z4 = 9; z4>0; z4--){
            map.put(new BlockPos(14, 5, z4), plank);
        }


        for(int z6 = 8; z6>1; z6--){
            map.put(new BlockPos(5, 7, z6), eostairs);
        }
        for(int z7 = 13; z7>4; z7--){
            map.put(new BlockPos(z7, 7, 8), nostairs);
        }
        for(int z8 = 13; z8>4; z8--){
            map.put(new BlockPos(z8, 7, 2), sostairs);
        }
        for(int z9 = 8; z9>1; z9--){
            map.put(new BlockPos(13, 7, z9), wostairs);

        }
        for(int z7 = 12; z7>5; z7--){
            map.put(new BlockPos(z7, 8, 7), loslab);
        }
        for(int z8 = 12; z8>5; z8--){
            map.put(new BlockPos(z8, 8, 3), loslab);
        }
        for(int z9 = 7; z9>2; z9--){
            map.put(new BlockPos(12, 8, z9), loslab);

        }
        for(int z10 = 4; z10 < 7; z10++) {
            for(int x1 = 5; x1 < 12; x1++) {
                map.put(new BlockPos(x1, 8, z10), uoslab);
            }
        }









        //middle of first layer
        for(int z = 1; z < 10; z++) {
            for(int x = 4; x < 15; x++) {
                map.put(new BlockPos(x, 0, z), plank);
            }
        }




        //this is the thingy that makes it a relative position, as well as places it
        map.put(new BlockPos(4, 6, 5), loslab);
        map.put(new BlockPos(5, 6, 5), wood);
        map.put(new BlockPos(5, 5, 5), wood);
        map.put(new BlockPos(4, 6, 4), plank);
        map.put(new BlockPos(4, 6, 6), plank);
        map.put(new BlockPos(4, 7, 6), eostairs);
        map.put(new BlockPos(4, 7, 4), eostairs);
        map.put(new BlockPos(5, 7, 6), plank);
        map.put(new BlockPos(5, 7, 4), plank);
        map.put(new BlockPos(5, 7, 5), glass_south);
        map.put(new BlockPos(5, 8, 5), plank);
        map.put(new BlockPos(5, 8, 4), sostairs);
        map.put(new BlockPos(5, 8, 6), nostairs);
        map.put(new BlockPos(9, 8, 3), Blocks.POLISHED_ANDESITE.getDefaultState());
        map.put(new BlockPos(9, 9, 3), Blocks.POLISHED_ANDESITE.getDefaultState());

    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        /*
        pos = pos.add(3, 0, 3);
       //Set<BlockPos> keySet = map.keySet();
        for (Iterator<BlockPos> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            BlockPos pos1 = iterator.next();
            @NotNull
            BlockState state2 = map.get(pos1);
            BlockPos pos2 = pos1;

            if (state2 != null) {
                world.setBlockState(pos2.add(pos), state2);

            }


        }
        for (Iterator<BlockPos> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            BlockPos pos1 = iterator.next();
            @NotNull
            BlockState state2 = map.get(pos1);
            BlockPos pos2 = pos1;

            if (state2 != null) {
                world.updateNeighbors(pos2.add(pos), state2.getBlock());

            }


        }

         */

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HouseBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return HouseBlockEntity::tick;
    }
}
