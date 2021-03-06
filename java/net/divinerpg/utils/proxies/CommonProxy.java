package net.divinerpg.utils.proxies;

import net.divinerpg.DivineRPG;
import net.divinerpg.api.entity.tileentity.TileEntityStupidSpawner;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDemonFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDramixAltar;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityExtractor;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityGreenlightFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityMoltenFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityMoonlightFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityOceanfireFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityParasectaAltar;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityWhitefireFurnace;
import net.divinerpg.blocks.iceika.tileentity.TileEntityCoalstoneFurnace;
import net.divinerpg.blocks.iceika.tileentity.TileEntityFrostedChest;
import net.divinerpg.blocks.iceika.tileentity.TileEntityPresentBox;
import net.divinerpg.blocks.twilight.TileEntityEdenChest;
import net.divinerpg.blocks.vanilla.container.tile_entity.TileEntityAltarOfCorruption;
import net.divinerpg.blocks.vanilla.container.tile_entity.TileEntityAyeracoBeam;
import net.divinerpg.blocks.vanilla.container.tile_entity.TileEntityBoneChest;
import net.divinerpg.blocks.vethea.container.tile_entity.TileEntityInfusionTable;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.client.render.block.TileEntityStatue;
import net.divinerpg.dimensions.vanilla.DivineWorldgen;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.entities.ArcanaEntityRegistry;
import net.divinerpg.utils.entities.IceikaEntityRegistry;
import net.divinerpg.utils.entities.MobSpawning;
import net.divinerpg.utils.entities.TwilightEntityRegistry;
import net.divinerpg.utils.entities.VanillaEntityRegistry;
import net.divinerpg.utils.entities.VetheaEntityRegistry;
import net.divinerpg.utils.events.ArcanaTickHandler;
import net.divinerpg.utils.events.DivineRPGCommands;
import net.divinerpg.utils.events.EntityConstructorEvent;
import net.divinerpg.utils.events.EventArmorFullSet;
import net.divinerpg.utils.events.EventArmorTick;
import net.divinerpg.utils.events.EventBonemeal;
import net.divinerpg.utils.events.EventBucketFill;
import net.divinerpg.utils.events.EventClientLogin;
import net.divinerpg.utils.events.EventDeath;
import net.divinerpg.utils.events.EventHarvest;
import net.divinerpg.utils.events.EventInteract;
import net.divinerpg.utils.events.EventLightning;
import net.divinerpg.utils.events.EventTooltip;
import net.divinerpg.utils.events.Ticker;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsTools;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsTools;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.items.VetheaItems;
import net.divinerpg.utils.recipes.TwilightRecipeHelper;
import net.divinerpg.utils.recipes.VanillaRecipeHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy{
	public void preInitClient(FMLPreInitializationEvent event){}

	public void renderThings(){}
	
	public void preInitServer(FMLPreInitializationEvent event){
		LogHelper.info("Adding events");
		Util.postForgeEvent(new EventBucketFill());
		Util.postForgeEvent(new EventBonemeal());
		Util.postForgeEvent(new EventArmorFullSet());
		Util.postFMLEvent(new EventArmorTick());
		Util.postForgeEvent(new EventHarvest());
		Util.postForgeEvent(new EventDeath());
        Util.postFMLEvent(new ArcanaTickHandler());
        Util.postForgeEvent(new EntityConstructorEvent());
        Util.postForgeEvent(new EventTooltip());
        Util.postForgeEvent(new EventLightning());
        Util.postForgeEvent(new EventClientLogin());
        Util.postForgeEvent(new EventInteract());
        Util.postFMLEvent(new Ticker());
		LogHelper.info("Registering tile entities");
		ItemsFood.init();
		IceikaItems.init();
		IceikaBlocks.init();
		VetheaItems.init();
		VetheaBlocks.init();
		VanillaBlocks.init();
		TwilightItemsOther.init();
		TwilightBlocks.init();
		TwilightItemsCrops.init();
		TwilightItemsArmor.init();
		TwilightItemsWeapons.init();
		TwilightItemsTools.init();
		VanillaItemsOther.init();
		VanillaItemsArmor.init();
		VanillaItemsWeapons.init();
		VanillaItemsTools.init();
		ArcanaBlocks.init();
		ArcanaItems.init();
		DivineRPGTabs.init();
		GameRegistry.registerTileEntity(TileEntityInfusionTable.class, "Infusion Table");
		GameRegistry.registerTileEntity(TileEntityStatue.class, "Statue");
		GameRegistry.registerTileEntity(TileEntityFrostedChest.class, "Frosted Chest");
		GameRegistry.registerTileEntity(TileEntityEdenChest.class, "Eden Chest");
		GameRegistry.registerTileEntity(TileEntityBoneChest.class, "Bone Chest");
		GameRegistry.registerTileEntity(TileEntityAyeracoBeam.class, "Ayeraco Beam");
		GameRegistry.registerTileEntity(TileEntityExtractor.class, "Arcanium Extractor");
		GameRegistry.registerTileEntity(TileEntityDramixAltar.class, "Dramix Altar");
		GameRegistry.registerTileEntity(TileEntityParasectaAltar.class, "Parasecta Altar");
		GameRegistry.registerTileEntity(TileEntityCoalstoneFurnace.class, "Coalstone Furnace");
		GameRegistry.registerTileEntity(TileEntityGreenlightFurnace.class, "Greenlight Furnace");
		GameRegistry.registerTileEntity(TileEntityMoltenFurnace.class, "Molten Furnace");
		GameRegistry.registerTileEntity(TileEntityMoonlightFurnace.class, "Moonlight Furnace");
		GameRegistry.registerTileEntity(TileEntityOceanfireFurnace.class, "Oceanfire Furnace");
		GameRegistry.registerTileEntity(TileEntityWhitefireFurnace.class, "Whitefire Furnace");
		GameRegistry.registerTileEntity(TileEntityDemonFurnace.class, "Demon Furnace");
		GameRegistry.registerTileEntity(TileEntityAltarOfCorruption.class, "Altar Of Corruption");
		GameRegistry.registerTileEntity(TileEntityStupidSpawner.class, "StupidMobSpawner");
		GameRegistry.registerTileEntity(TileEntityPresentBox.class, "PresentBox");
		LogHelper.info("Registering entities");
		VanillaEntityRegistry.init();
		TwilightEntityRegistry.init();
		IceikaEntityRegistry.init();
		VetheaEntityRegistry.init();
		ArcanaEntityRegistry.init();
		LogHelper.info("Adding crafting/smelting recipes");
		VanillaRecipeHelper.init();
		TwilightRecipeHelper.init();
		DimensionHelper.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(DivineRPG.instance, new GuiHandler());
        MobSpawning.addSpawns();
	}

	public void init(FMLInitializationEvent event){
		LogHelper.info("Adding world generators");
		GameRegistry.registerWorldGenerator(new DivineWorldgen(), 0);
		DivineRPGAchievements.init();
	}

	public void postInit(FMLPostInitializationEvent event){
		Util.addBucket(DivineRPG.tarFluid, new ItemStack(VanillaItemsOther.tarBucket));
	}

	public void serverStarting(FMLServerStartingEvent event){ 
		LogHelper.info("Setting up commands");
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager)
			((CommandHandler)MinecraftServer.getServer().getCommandManager()).registerCommand(new DivineRPGCommands());
	}
	public void spawnParticle(World w, double x, double y, double z, String particle, boolean random) {}
	public void spawnParticle(World w, double x, double y, double z, String particle, boolean random, int randFactor) {}
}
