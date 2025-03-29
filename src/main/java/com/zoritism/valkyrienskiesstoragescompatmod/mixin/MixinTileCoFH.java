package com.zoritism.valkyrienskiesstoragescompatmod.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = {"cofh.core.common.inventory.BlockEntityCoFHMenu"}, remap = false)
public abstract class MixinTileCoFH {

    @Inject(method = "m_6875_", at = @At("HEAD"), cancellable = true)
    private void prePlayerWithinDistance(Player player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
