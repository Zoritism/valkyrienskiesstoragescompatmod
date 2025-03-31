package com.zoritism.valkyrienskiesstoragescompatmod.util;

import mcjty.lib.varia.LogicFacing;
import net.minecraft.world.level.block.Rotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class LogicFacingRotationHelper {

    private static final Logger LOGGER = LogManager.getLogger("ValkyrieStoragesCompatMod");

    private static final Map<LogicFacing, LogicFacing> rotate90 = new HashMap<>();
    private static final Map<LogicFacing, LogicFacing> rotate180 = new HashMap<>();
    private static final Map<LogicFacing, LogicFacing> rotate270 = new HashMap<>();

    static {
        // Horizontal CLOCKWISE (Y-axis)
        rotate90.put(LogicFacing.NORTH_TOEAST, LogicFacing.EAST_TOSOUTH);
        rotate90.put(LogicFacing.EAST_TOSOUTH, LogicFacing.SOUTH_TOWEST);
        rotate90.put(LogicFacing.SOUTH_TOWEST, LogicFacing.WEST_TONORTH);
        rotate90.put(LogicFacing.WEST_TONORTH, LogicFacing.NORTH_TOEAST);

        rotate90.put(LogicFacing.SOUTH_TOEAST, LogicFacing.WEST_TOSOUTH);
        rotate90.put(LogicFacing.WEST_TOSOUTH, LogicFacing.NORTH_TOWEST);
        rotate90.put(LogicFacing.NORTH_TOWEST, LogicFacing.EAST_TONORTH);
        rotate90.put(LogicFacing.EAST_TONORTH, LogicFacing.SOUTH_TOEAST);

        // Horizontal COUNTERCLOCKWISE (Y-axis)
        rotate270.put(LogicFacing.NORTH_TOEAST, LogicFacing.WEST_TONORTH);
        rotate270.put(LogicFacing.WEST_TONORTH, LogicFacing.SOUTH_TOWEST);
        rotate270.put(LogicFacing.SOUTH_TOWEST, LogicFacing.EAST_TOSOUTH);
        rotate270.put(LogicFacing.EAST_TOSOUTH, LogicFacing.NORTH_TOEAST);

        rotate270.put(LogicFacing.SOUTH_TOEAST, LogicFacing.EAST_TONORTH);
        rotate270.put(LogicFacing.EAST_TONORTH, LogicFacing.NORTH_TOWEST);
        rotate270.put(LogicFacing.NORTH_TOWEST, LogicFacing.WEST_TOSOUTH);
        rotate270.put(LogicFacing.WEST_TOSOUTH, LogicFacing.SOUTH_TOEAST);

        // 180-degree rotation
        rotate180.put(LogicFacing.NORTH_TOEAST, LogicFacing.SOUTH_TOWEST);
        rotate180.put(LogicFacing.SOUTH_TOWEST, LogicFacing.NORTH_TOEAST);
        rotate180.put(LogicFacing.EAST_TOSOUTH, LogicFacing.WEST_TONORTH);
        rotate180.put(LogicFacing.WEST_TONORTH, LogicFacing.EAST_TOSOUTH);

        rotate180.put(LogicFacing.SOUTH_TOEAST, LogicFacing.NORTH_TOWEST);
        rotate180.put(LogicFacing.NORTH_TOWEST, LogicFacing.SOUTH_TOEAST);
        rotate180.put(LogicFacing.EAST_TONORTH, LogicFacing.WEST_TOSOUTH);
        rotate180.put(LogicFacing.WEST_TOSOUTH, LogicFacing.EAST_TONORTH);

        // DOWN (vertical axis)
        rotate90.put(LogicFacing.DOWN_TONORTH, LogicFacing.DOWN_TOEAST);
        rotate90.put(LogicFacing.DOWN_TOEAST, LogicFacing.DOWN_TOSOUTH);
        rotate90.put(LogicFacing.DOWN_TOSOUTH, LogicFacing.DOWN_TOWEST);
        rotate90.put(LogicFacing.DOWN_TOWEST, LogicFacing.DOWN_TONORTH);

        rotate180.put(LogicFacing.DOWN_TONORTH, LogicFacing.DOWN_TOSOUTH);
        rotate180.put(LogicFacing.DOWN_TOEAST, LogicFacing.DOWN_TOWEST);
        rotate180.put(LogicFacing.DOWN_TOSOUTH, LogicFacing.DOWN_TONORTH);
        rotate180.put(LogicFacing.DOWN_TOWEST, LogicFacing.DOWN_TOEAST);

        rotate270.put(LogicFacing.DOWN_TONORTH, LogicFacing.DOWN_TOWEST);
        rotate270.put(LogicFacing.DOWN_TOEAST, LogicFacing.DOWN_TONORTH);
        rotate270.put(LogicFacing.DOWN_TOSOUTH, LogicFacing.DOWN_TOEAST);
        rotate270.put(LogicFacing.DOWN_TOWEST, LogicFacing.DOWN_TOSOUTH);

        // UP (vertical axis)
        rotate90.put(LogicFacing.UP_TONORTH, LogicFacing.UP_TOEAST);
        rotate90.put(LogicFacing.UP_TOEAST, LogicFacing.UP_TOSOUTH);
        rotate90.put(LogicFacing.UP_TOSOUTH, LogicFacing.UP_TOWEST);
        rotate90.put(LogicFacing.UP_TOWEST, LogicFacing.UP_TONORTH);

        rotate180.put(LogicFacing.UP_TONORTH, LogicFacing.UP_TOSOUTH);
        rotate180.put(LogicFacing.UP_TOEAST, LogicFacing.UP_TOWEST);
        rotate180.put(LogicFacing.UP_TOSOUTH, LogicFacing.UP_TONORTH);
        rotate180.put(LogicFacing.UP_TOWEST, LogicFacing.UP_TOEAST);

        rotate270.put(LogicFacing.UP_TONORTH, LogicFacing.UP_TOWEST);
        rotate270.put(LogicFacing.UP_TOEAST, LogicFacing.UP_TONORTH);
        rotate270.put(LogicFacing.UP_TOSOUTH, LogicFacing.UP_TOEAST);
        rotate270.put(LogicFacing.UP_TOWEST, LogicFacing.UP_TOSOUTH);

// VERTICAL (multi-side)
        rotate90.put(LogicFacing.SOUTH_TODOWN, LogicFacing.WEST_TODOWN);
        rotate90.put(LogicFacing.WEST_TODOWN, LogicFacing.NORTH_TODOWN);
        rotate90.put(LogicFacing.NORTH_TODOWN, LogicFacing.EAST_TODOWN);
        rotate90.put(LogicFacing.EAST_TODOWN, LogicFacing.SOUTH_TODOWN);

        rotate90.put(LogicFacing.SOUTH_TOUP, LogicFacing.WEST_TOUP);
        rotate90.put(LogicFacing.WEST_TOUP, LogicFacing.NORTH_TOUP);
        rotate90.put(LogicFacing.NORTH_TOUP, LogicFacing.EAST_TOUP);
        rotate90.put(LogicFacing.EAST_TOUP, LogicFacing.SOUTH_TOUP);

        rotate270.put(LogicFacing.SOUTH_TODOWN, LogicFacing.EAST_TODOWN);
        rotate270.put(LogicFacing.EAST_TODOWN, LogicFacing.NORTH_TODOWN);
        rotate270.put(LogicFacing.NORTH_TODOWN, LogicFacing.WEST_TODOWN);
        rotate270.put(LogicFacing.WEST_TODOWN, LogicFacing.SOUTH_TODOWN);

        rotate270.put(LogicFacing.SOUTH_TOUP, LogicFacing.EAST_TOUP);
        rotate270.put(LogicFacing.EAST_TOUP, LogicFacing.NORTH_TOUP);
        rotate270.put(LogicFacing.NORTH_TOUP, LogicFacing.WEST_TOUP);
        rotate270.put(LogicFacing.WEST_TOUP, LogicFacing.SOUTH_TOUP);

// 180-degree for vertical
        rotate180.put(LogicFacing.SOUTH_TODOWN, LogicFacing.NORTH_TODOWN);
        rotate180.put(LogicFacing.NORTH_TODOWN, LogicFacing.SOUTH_TODOWN);
        rotate180.put(LogicFacing.EAST_TODOWN, LogicFacing.WEST_TODOWN);
        rotate180.put(LogicFacing.WEST_TODOWN, LogicFacing.EAST_TODOWN);

        rotate180.put(LogicFacing.SOUTH_TOUP, LogicFacing.NORTH_TOUP);
        rotate180.put(LogicFacing.NORTH_TOUP, LogicFacing.SOUTH_TOUP);
        rotate180.put(LogicFacing.EAST_TOUP, LogicFacing.WEST_TOUP);
        rotate180.put(LogicFacing.WEST_TOUP, LogicFacing.EAST_TOUP);

    }

    public static LogicFacing rotate(LogicFacing old, Rotation rotation) {
        LOGGER.info("[RotateHelper] Starting rotation: oldFacing = {}, rotation = {}", old, rotation);
        LogicFacing newFacing = switch (rotation) {
            case CLOCKWISE_90 -> rotate90.getOrDefault(old, old);
            case CLOCKWISE_180 -> rotate180.getOrDefault(old, old);
            case COUNTERCLOCKWISE_90 -> rotate270.getOrDefault(old, old);
            default -> old;
        };
        LOGGER.info("[RotateHelper] Result of rotation: newFacing = {}", newFacing);
        return newFacing;
    }
}

