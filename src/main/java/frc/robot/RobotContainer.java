/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.EStop;
import frc.robot.commands.printOn;
import frc.robot.subsystems.print;

import static frc.robot.Constants.JOYSTICK;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Joystick joystick = new Joystick(JOYSTICK);

    DriverStation ds = DriverStation.getInstance();

    PowerDistributionPanel pdp = new PowerDistributionPanel(0);

    print print = new print();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Print event and alliance
        try {
            System.out.println("Event: " + ds.getEventName()
                    + "\nAlliance: " + ds.getAlliance()
                    + "\n********************************************");
        }
        catch (Exception e) {
            System.out.println("An error occurred when attempting to get Event or Alliance");
        }

        // Put initial booleans
        SmartDashboard.putBoolean("E-Stop", Constants.Disabled);
        SmartDashboard.putBoolean("Disabled", Constants.Disabled);

        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        new JoystickButton(joystick, Constants.TRIGGER_BUTTON)
                .whenPressed(new printOn());
        new JoystickButton(joystick, Constants.ESTOP_BUTTON)
                .whenPressed(new EStop());
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public PowerDistributionPanel getPDP() {
        return pdp;
    }

}
