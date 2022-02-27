package xyz.breadloaf.forgetmechunk.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.SectionPos;
import net.minecraft.network.protocol.game.ClientboundForgetLevelChunkPacket;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Shadow private ClientLevel level;

    @Inject(method = "queueLightUpdate(Lnet/minecraft/network/protocol/game/ClientboundForgetLevelChunkPacket;)V", at = @At("HEAD"), cancellable = true)
    private void injected(ClientboundForgetLevelChunkPacket clientboundForgetLevelChunkPacket, CallbackInfo ci) {
        this.level.queueLightUpdate(() -> {
            LevelLightEngine levelLightEngine = this.level.getLightEngine();
            for (int i = this.level.getMinSection(); i < this.level.getMaxSection(); ++i) {
                this.level.setSectionDirtyWithNeighbors(clientboundForgetLevelChunkPacket.getX(), i, clientboundForgetLevelChunkPacket.getZ());
            }
            levelLightEngine.enableLightSources(new ChunkPos(clientboundForgetLevelChunkPacket.getX(), clientboundForgetLevelChunkPacket.getZ()), false);
            this.level.setLightReady(clientboundForgetLevelChunkPacket.getX(), clientboundForgetLevelChunkPacket.getZ());
        });
        ci.cancel();
    }
}
