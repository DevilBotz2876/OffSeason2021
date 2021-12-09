// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be declared
 * globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Button IDs (there are for Dillan's Joystick)
    public static final int TRIGGER_BUTTON = 3;
    public static final int ESTOP_BUTTON = 4;

    // USB IDs
    public static final int JOYSTICK = 0;

    // Data
    public static boolean EStop = false;

    public static final class AutoConstants {
        public static final double kWheelDiameterInches = 6;
        // Temp Practice Bot Change
        // public static final double DISTANCE_TO_GOAL = RobotType.isPracticeBot ? 40 : 80;
        // public static final double ROTATE_ANGLE = RobotType.isPracticeBot ? 114 : 94;

        public static final double DISTANCE_TO_GOAL = 80;
        public static final double ROTATE_ANGLE = 94;
    }

}