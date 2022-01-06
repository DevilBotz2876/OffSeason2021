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

package bhs.devilbotz.util;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class ShuffleboardManager {
    private static ShuffleboardManager defaultInstance = null;
    public NetworkTableEntry slewLimit;
    public NetworkTableEntry forwardSnapping;
    public ShuffleboardManager() {
        slewLimit = Shuffleboard.getTab("Driver Assist")
                .add("Slew Limit", true)
                .withWidget("Toggle Button")
                .getEntry();

        forwardSnapping = Shuffleboard.getTab("Driver Assist")
                .add("Forward Snapping", true)
                .withWidget("Toggle Button")
                .getEntry();

    }

    public static ShuffleboardManager getInstance() {
        if (defaultInstance == null) {
            defaultInstance = new ShuffleboardManager();
        }
        return defaultInstance;
    }

    public boolean getForwardSnapping() {
        return forwardSnapping.getBoolean(false);
    }

    public void setForwardSnapping(boolean value) {
        forwardSnapping.setBoolean(value);
    }

    public boolean getSlewLimit() {
        return slewLimit.getBoolean(false);
    }

    public void setSlewLimit(boolean value) {
        slewLimit.setBoolean(value);
    }

}
