// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

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
    // Button IDs
    public static final int SLEW_TOGGLE = 5;
    public static final int SNAPPING_TOGGLE = 6;

    // USB IDs
    public static final int JOYSTICK = 0;
    public static final GenericHID.Hand JOYSTICK_ONE_HAND = GenericHID.Hand.kRight;

    public static final int JOYSTICK_TWO = 1;
    public static final GenericHID.Hand JOYSTICK_TWO_HAND = GenericHID.Hand.kRight;

    // Data
    public static boolean EStop = false;

    // Auto Constants
    public static final class AutoConstants {
        public static final double kWheelDiameterInches = 7.15;
    }

}