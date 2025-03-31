package com.zoritism.valkyrienskiesstoragescompatmod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"mcjty.lib.blocks.LogicSlabBlock"}, remap = false)
public class LogicSlabBlockMixin {

    private static final Logger LOGGER = LogManager.getLogger("ValkyrieStoragesCompatMod");

    /**
     * После вызова метода rotate, логируем новое состояние блока.
     * Если блок имеет стандартное свойство FACING, выводим его значение.
     */
    @Inject(method = "rotate", at = @At("RETURN"))
    private void afterRotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation rot, CallbackInfoReturnable<BlockState> cir) {
        BlockState newState = cir.getReturnValue();
        if (newState.hasProperty(BlockStateProperties.FACING)) {
            Object facingValue = newState.getValue(BlockStateProperties.FACING);
            LOGGER.info("Block at {} rotated with {}. New facing: {}", pos, rot, facingValue);
        } else {
            LOGGER.info("Block at {} rotated with {}. No facing property found.", pos, rot);
        }
    }
}
