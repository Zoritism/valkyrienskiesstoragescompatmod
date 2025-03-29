package com.zoritism.valkyrienskiesstoragescompatmod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin implements Clearable {

    @Override
    public void clearContent() {
        BlockEntity blockEntity = (BlockEntity) (Object) this;
        String blockName = blockEntity.getClass().getSimpleName();
        BlockPos pos = blockEntity.getBlockPos();

        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        if (elements.length > 1) {
            String callerClassName = elements[1].getClassName();
            if (callerClassName.startsWith("org.valkyrienskies")) {
                CompoundTag emptyTag = new CompoundTag();
                blockEntity.load(emptyTag);
                return;
            }
        }
    }
}
