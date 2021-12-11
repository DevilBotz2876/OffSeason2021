package frc.robot.util;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ShuffleboardManager {
    private static ShuffleboardManager defaultInstance = null;


    public static ShuffleboardManager getInstance() {
        if (defaultInstance == null) {
            defaultInstance = new ShuffleboardManager();
        }
        return defaultInstance;
    }
    public NetworkTableEntry slewLimit;
    public NetworkTableEntry forwardSnapping;
    private ShuffleboardTab shuffleTab;

    public ShuffleboardManager() {
        /* This is how to declare HashMap */
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();
        hmap.put(12, "Cool");
        hmap.put(2, "Beans");
        hmap.put(7, "This");
        hmap.put(49, "is");
        hmap.put(3, "Amazing");

        Set set = hmap.entrySet();
        for (Object o : set) {
            Map.Entry mentry = (Map.Entry) o;
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }

        /* Get values based on key*/
        String var= hmap.get(2);
        System.out.println("Value at index 2 is: "+var);

        /* Remove values based on key*/
        hmap.remove(3);
        System.out.println("Map key and values3 after removal:");
        Set set2 = hmap.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry mentry2 = (Map.Entry) iterator2.next();
            System.out.print("Key is: " + mentry2.getKey() + " & Value is: ");
            System.out.println(mentry2.getValue());
        }



        /*SlewRateLimiter filter = new SlewRateLimiter(0.5);
    filter.calculate(input);
    */

        //SmartDashboard.putBoolean("Driving Reverse", xbox.getBButtonPressed());
        //SmartDashboard.putNumber("Slew Limit", joystickChangeLimit);
        /**Below are the updated versions of above **/
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


    public void setForwardSnapping(boolean value) {
        forwardSnapping.setBoolean(value);
    }

    public boolean getForwardSnapping() {
        return forwardSnapping.getBoolean(false);
    }

    public void setSlewLimit(boolean value) {
        slewLimit.setBoolean(value);
    }

    public boolean getSlewLimit() {
        return slewLimit.getBoolean(false);
    }

    public void CreateWidget(String name) {
        for (ShuffleboardComponent widgetComponent : shuffleTab.getComponents()) {
            if (widgetComponent.getTitle().equals(name)) {
            }
        }
    }
}
