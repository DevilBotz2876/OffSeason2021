package bhs.devilbotz.commands.autonomous.autoCommands;

import bhs.devilbotz.commands.autonomous.drive.DriveDistance;
import bhs.devilbotz.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DistanceAccTest extends SequentialCommandGroup {
    public DistanceAccTest(DriveTrain drive) {
        addCommands(
                new DriveDistance(drive, 40, 0.6));
    }
}
