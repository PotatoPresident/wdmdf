package com.github.potatopresident.wdmdf.mixin;

import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerLoginNetworkHandlerMixin {
    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "acceptPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;disconnect(Lnet/minecraft/text/Text;)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onInvalidData(CallbackInfo ci, ServerPlayerEntity serverPlayerEntity, Exception exception, Text text2) {
        LOGGER.error("Invalid player data", exception);
    }
}
