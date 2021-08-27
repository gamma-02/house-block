package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer, ClientModInitializer {
	public static String MOD_ID = new String("house_block");
	public static final Block HouseBlock2 = new HouseBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).nonOpaque());
	public static BlockEntityType<HouseBlockEntity> houseBlockEntityBlockEntityType;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		houseBlockEntityBlockEntityType = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("malek_is_epic", "house_block_entity"), FabricBlockEntityTypeBuilder.create(HouseBlockEntity::new, HouseBlock2).build());
		Registry.register(Registry.BLOCK, new Identifier("house_seeds", "malek"), HouseBlock2);
		Registry.register(Registry.ITEM, new Identifier("house_seeds", "malek2"), new BlockItem(HouseBlock2,new FabricItemSettings().group(ItemGroup.MISC)));
		System.out.println("Hello Fabric world!");
	}


	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ExampleMod.HouseBlock2, RenderLayer.getTranslucent());
	}
}
