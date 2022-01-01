/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.autonomous.buttons.EStop;
import frc.robot.commands.autonomous.buttons.SlewRateToggle;
import frc.robot.commands.autonomous.buttons.SnappingToggle;
import frc.robot.commands.autonomous.autoCommands.AutoTest;
import frc.robot.subsystems.DriveTrain;

import static frc.robot.Constants.JOYSTICK;
import static frc.robot.Constants.JOYSTICK_TWO;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Joystick joystick = new Joystick(JOYSTICK);
    private final Joystick joystickTwo = new Joystick(JOYSTICK_TWO);
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
