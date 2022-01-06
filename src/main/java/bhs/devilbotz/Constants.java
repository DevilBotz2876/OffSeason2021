/*-------------------------------------------------------------------------------*/
/* Copyright (c) 2021-2022 BHS Devilbotz. All Rights Reserved.                   */
/* Open Source Software - may be modified, commercialized, distributed,          */
/* sub-licensed and used for private use under the terms of the License.md       */
/* file in the root of the source code tree.                                     */
/*                                                                               */
/* When doing any of the above, you MUST include the original                    */
/* copyright and license files in any and all revised/modified code.             */
/* You may NOT remove this header under any circumstance unless explicitly noted */
/*-------------------------------------------------------------------------------*/

package bhs.devilbotz;

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
    public static final int SLEW_TOGGLE = 4;
    public static final int SNAPPING_TOGGLE = 5;

    // USB IDs
    public static final int JOYSTICK = 1;
    public static final GenericHID.Hand JOYSTICK_ONE_HAND = GenericHID.Hand.kRight;

    public static final int JOYSTICK_TWO = 0;
    public static final GenericHID.Hand JOYSTICK_TWO_HAND = GenericHID.Hand.kLeft;

    // Data
    public static boolean EStop = false;

    // Auto Constants
    public static final class AutoConstants {
        public static final double kWheelDiameterInches = 7.15;
    }

}