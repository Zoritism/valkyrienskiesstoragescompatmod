package com.zoritism.valkyrienskiesstoragescompatmod.mixin;

import kotlin.Triple;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.eureka.util.ShipAssembler;

import java.util.HashSet;
import java.util.Iterator;

@Mixin(value = ShipAssembler.class, remap = false)
public class ShipAssemblerMixin {

    private static final Logger LOGGER = LogManager.getLogger("ValkyrieStoragesCompatMod");

    /**
     * Инъекция в метод unfillShip.
     * Мы пытаемся захватить локальную переменную "toUpdate" (HashSet&lt;Triple&lt;BlockPos, BlockPos, BlockState&gt;&gt;)
     * и переменную "rotation" (Rotation), используемые при перемещении блоков.
     * Для каждого элемента из toUpdate выводим в лог информацию:
     * внутреннюю позицию блока, мировую позицию, состояние и используемый поворот.
     *
     * Обратите внимание: из-за особенностей компиляции Kotlin порядок и наличие локальных переменных может изменяться.
     * Поэтому параметр require установлен в 0 – если захват не удастся, инъекция не произойдёт, но мод не упадёт.
     */
    @Inject(
            method = "unfillShip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/Server;executeIf(Ljava/util/function/Predicate;Ljava/lang/Runnable;)V",
                    shift = At.Shift.BEFORE
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            require = 0
    )
    private void logBlocksBeforeUpdates(ServerLevel level, ServerShip ship, BlockPos shipCenter, BlockPos center, CallbackInfoReturnable<ServerShip> cir,
            /* локальная переменная toUpdate */ HashSet<?> toUpdate,
            /* локальная переменная rotation */ Rotation rotation) {
        if (toUpdate == null) {
            LOGGER.warn("Локальная переменная toUpdate не найдена!");
            return;
        }
        // Перебираем элементы. Каждый элемент должен быть типа kotlin.Triple<BlockPos, BlockPos, BlockState>
        Iterator<?> it = toUpdate.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof Triple) {
                Triple<?, ?, ?> triple = (Triple<?, ?, ?>) obj;
                Object first = triple.getFirst();
                Object second = triple.getSecond();
                Object third = triple.getThird();
                // Пытаемся привести к нужным типам (BlockPos и BlockState)
                if (first instanceof BlockPos && second instanceof BlockPos && third instanceof BlockState) {
                    BlockPos inShipPos = (BlockPos) first;
                    BlockPos inWorldPos = (BlockPos) second;
                    BlockState state = (BlockState) third;
                    LOGGER.info("Block relocated: inShipPos={}, inWorldPos={}, state={}, rotation={}",
                            inShipPos, inWorldPos, state, rotation);
                } else {
                    LOGGER.warn("Элемент toUpdate имеет неожидаемые типы: {} {} {}", first, second, third);
                }
            } else {
                LOGGER.warn("Элемент toUpdate не является Triple: {}", obj);
            }
        }
    }
}
