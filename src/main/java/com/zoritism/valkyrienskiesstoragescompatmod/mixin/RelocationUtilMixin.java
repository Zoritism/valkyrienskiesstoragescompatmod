package com.zoritism.valkyrienskiesstoragescompatmod.mixin;

import mcjty.lib.blocks.LogicSlabBlock;
import mcjty.lib.varia.LogicFacing;
import com.zoritism.valkyrienskiesstoragescompatmod.util.LogicFacingRotationHelper;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.StateDefinition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "org.valkyrienskies.mod.util.RelocationUtilKt")
public class RelocationUtilMixin {

    private static final Logger LOGGER = LogManager.getLogger("ValkyrieStoragesCompatMod");

    @Redirect(
            method = "relocateBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;rotate(Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;"
            )
    )
    private static BlockState redirectRotate(BlockState state, Rotation rotation) {
        LOGGER.info("[RelocationUtilMixin] Intercepted rotate call with state: {} and rotation: {}", state, rotation);
        Block block = state.getBlock();

        if (block instanceof LogicSlabBlock logicBlock) {
            Property<LogicFacing> property = LogicSlabBlock.LOGIC_FACING;
            if (state.hasProperty(property)) {
                LogicFacing oldFacing = state.getValue(property);
                LogicFacing newFacing = LogicFacingRotationHelper.rotate(oldFacing, rotation);
                BlockState newState = state.setValue(property, newFacing);
                LOGGER.info("[RelocationUtilMixin] Updated LogicFacing from {} to {}", oldFacing, newFacing);
                return newState;
            }
        }

        BlockState result = state.rotate(rotation); // fallback
        LOGGER.info("[RelocationUtilMixin] Resulting state: {}", result);
        return result;
    }
}
