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

import bhs.devilbotz.commands.DriveCommand;
import bhs.devilbotz.commands.autonomous.autoCommands.DistanceAccTest;
import bhs.devilbotz.commands.autonomous.buttons.SlewRateToggle;
import bhs.devilbotz.commands.autonomous.buttons.SnappingToggle;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import bhs.devilbotz.commands.autonomous.autoCommands.AutoTest;
import bhs.devilbotz.subsystems.DriveTrain;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Joystick joystick = new Joystick(Constants.JOYSTICK);
    private final Joystick joystickTwo = new Joystick(Constants.JOYSTICK_TWO);
    private final DriveTrain drive = new DriveTrain();
    DriverStation ds = DriverStation.getInstance();
    PowerDistributionPanel pdp = new PowerDistributionPanel(0);
    private final SendableChooser<Command> autonomousChooser = new SendableChooser<>();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Print event and alliance
        try {
            System.out.println("********************************************"
                    + "\nEvent: " + ds.getEventName()
                    + "\nAlliance: " + ds.getAlliance()
                    + "\n********************************************");
        } catch (Exception e) {
            System.out.println("An error occurred when attempting to get Event or Alliance");
        }

        AutoTest autoTest = new AutoTest(drive);
        autonomousChooser.addOption("Auto Test", autoTest);
        DistanceAccTest distanceAccTest = new DistanceAccTest(drive);
        autonomousChooser.addOption("Distance Acc Test", distanceAccTest);
        // DriveDistance backupCommand = new DriveDistance(drive, Constants.AutoConstants.DISTANCE_TO_GOAL, -0.5);
        // autonomousChooser.addOption("Back Up", backupCommand);
        SmartDashboard.putData("Auto Chooser", autonomousChooser);

        // Put initial booleans
        SmartDashboard.putBoolean("E-Stop", Constants.EStop);
        SmartDashboard.putBoolean("Forward Lock", false);

        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        drive.setDefaultCommand(new DriveCommand(drive,
                () -> -joystick.getY(Constants.JOYSTICK_ONE_HAND),
                () -> -joystickTwo.getY(Constants.JOYSTICK_TWO_HAND)
        ));

        new JoystickButton(joystick, Constants.SLEW_TOGGLE)
                .whenPressed(new SlewRateToggle());

        new JoystickButton(joystick, Constants.SNAPPING_TOGGLE)
                .whenPressed(new SnappingToggle());
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autonomousChooser.getSelected();
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public Joystick getJoystickTwo() {
        return joystickTwo;
    }

    public PowerDistributionPanel getPDP() {
        return pdp;
    }

}
