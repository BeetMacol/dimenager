package com.beetmacol.mc.dimensions.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.concurrent.Executor;

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor {
	@Accessor
	LevelStorageSource.LevelStorageAccess getStorageSource();

	@Accessor
	Executor getExecutor();
}
