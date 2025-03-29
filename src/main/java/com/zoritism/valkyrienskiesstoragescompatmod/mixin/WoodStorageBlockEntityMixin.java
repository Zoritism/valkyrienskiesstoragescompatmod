package com.zoritism.valkyrienskiesstoragescompatmod.mixin;

import net.minecraft.world.Clearable;
import net.p3pp3rf1y.sophisticatedstorage.block.WoodStorageBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WoodStorageBlockEntity.class)
public abstract class WoodStorageBlockEntityMixin implements Clearable {

    @Shadow(remap = false)
    public abstract void setPacked(boolean packed);

    public void clearContent() {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();

        if (elements.length > 1) {
            String callerClassName = elements[1].getClassName();

            if (callerClassName.startsWith("org.valkyrienskies")) {
                this.setPacked(true);
            }
        }
    }
}
