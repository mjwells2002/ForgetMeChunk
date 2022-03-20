package xyz.breadloaf.forgetmechunk.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ClientPlayNetworkHandler.class)
public class ClientPacketListenerMixin {
    @Redirect(method="onUnloadChunk", at=@At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/light/LightingProvider;setSectionStatus(Lnet/minecraft/util/math/ChunkSectionPos;Z)V"))
    private void injected(LightingProvider instance, ChunkSectionPos pos, boolean notReady) {

    }
}
