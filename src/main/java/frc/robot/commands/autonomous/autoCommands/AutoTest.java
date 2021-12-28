package frc.robot.commands.autonomous.autoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autonomous.drive.DriveCurve;
import frc.robot.commands.autonomous.drive.DriveDistance;
import frc.robot.commands.autonomous.drive.DriveRotate;
import frc.robot.commands.autonomous.drive.DriveTimed;
import frc.robot.subsystems.DriveTrain;

public class AutoTest extends SequentialCommandGroup {
    public AutoTest(DriveTrain drive) {
        addCommands(
                new DriveDistance(drive, 40, 0.5),
                new DriveRotate(drive, 90, 0.5),
                new DriveTimed(drive, 5, 0.7),
                new DriveCurve(drive, 40, 0.7, 90));
    }
}
