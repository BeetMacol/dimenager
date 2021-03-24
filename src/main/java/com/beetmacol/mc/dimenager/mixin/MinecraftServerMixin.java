package com.beetmacol.mc.dimenager.mixin;

import com.beetmacol.mc.dimenager.Dimenager;
import com.beetmacol.mc.dimenager.dimensions.DimensionRepository;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerResources;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;
import java.util.Collection;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Inject(
			method = "<init>",
			at = @At("TAIL")
	)
	private void onServerInit(Thread thread, RegistryAccess.RegistryHolder registryHolder, LevelStorageSource.LevelStorageAccess levelStorageAccess, WorldData worldData, PackRepository packRepository, Proxy proxy, DataFixer dataFixer, ServerResources serverResources, MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository, GameProfileCache gameProfileCache, ChunkProgressListenerFactory chunkProgressListenerFactory, CallbackInfo ci) {
		Dimenager.dimensionRepository = new DimensionRepository(serverResources.getResourceManager(), levelStorageAccess, ((MinecraftServerAccessor) this).getLevels(), registryHolder.dimensionTypes());
	}

	@Inject(
			method = "createLevels",
			at = @At("TAIL")
	)
	private void onLevelsLoad(ChunkProgressListener chunkProgressListener, CallbackInfo ci) {
		Dimenager.dimensionRepository.reloadDimensions();
	}

	// MC Dev plugin doesn't recognise lambdas
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(
			method = "lambda$reloadResources$10",
			at = @At("TAIL")
	)
	private void onResourcesReload(Collection<String> collection, ServerResources resources, CallbackInfo ci) {
		Dimenager.dimensionRepository.resourceManagerReload(resources.getResourceManager());
	}
}