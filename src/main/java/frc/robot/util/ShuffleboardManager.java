package frc.robot.util;

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
