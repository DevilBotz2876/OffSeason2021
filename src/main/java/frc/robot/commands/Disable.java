// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class Disable extends CommandBase {


    /**
     * Creates a new ExampleCommand.
     *
     */
    public Disable() {

    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("******** DISABLE TRIGGERED ********\n" +
                "The Disable button has been triggered.\n" +
                "The robot will now be disabled.\n" +
                "Please re-enable the robot to continue.\n" +
                "To re-enable the robot, press the disable\n" +
                "button again\n" +
                "******************************************");
        Constants.Disabled = true;
        SmartDashboard.putBoolean("Disabled", true);


    }


    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }


    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
