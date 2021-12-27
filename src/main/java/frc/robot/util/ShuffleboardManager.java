package frc.robot.util;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShuffleboardManager {
    private static ShuffleboardManager defaultInstance = null;
    public NetworkTableEntry slewLimit;
    public NetworkTableEntry forwardSnapping;
    public ShuffleboardManager() {
        /* This is how to declare HashMap */
        HashMap<Integer, String> hMap = new HashMap<>();
        hMap.put(12, "Cool");
        hMap.put(2, "Beans");
        hMap.put(7, "This");
        hMap.put(49, "is");
        hMap.put(3, "Amazing");

        Set<Map.Entry<Integer, String>> set = hMap.entrySet();
        for (Object o : set) {
            Map.Entry mEntry = (Map.Entry) o;
            System.out.print("key is: " + mEntry.getKey() + " & Value is: ");
            System.out.println(mEntry.getValue());
        }

        /* Get values based on key*/
        String var = hMap.get(2);
        System.out.println("Value at index 2 is: " + var);

        /* Remove values based on key*/
        hMap.remove(3);
        System.out.println("Map key and values3 after removal:");
        Set<Map.Entry<Integer, String>> set2 = hMap.entrySet();
        for (Map.Entry<Integer, String> integerStringEntry : set2) {
            System.out.print("Key is: " + ((Map.Entry<?, ?>) integerStringEntry).getKey() + " & Value is: ");
            System.out.println(((Map.Entry<?, ?>) integerStringEntry).getValue());
        }


        slewLimit = Shuffleboard.getTab("Driver Assist")
                .add("Slew Limit", true)
                .withWidget("Toggle Button")
                .getEntry();

        forwardSnapping = Shuffleboard.getTab("Driver Assist")
                .add("Forward Snapping", false)
                .withWidget("Toggle Button")
                .getEntry();

        forwardSnapping.setBoolean(true);

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
