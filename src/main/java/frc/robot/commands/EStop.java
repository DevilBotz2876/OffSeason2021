// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class EStop extends CommandBase {
    public EStop() {}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //when EStop is not true, the command scheduler is run, i.e. when EStop is true, the command scheduler is stopped and the robot must be restarted to be enabled again
        Constants.EStop = true;
        SmartDashboard.putBoolean("E-Stop", true);

        for (int i = 0; i < 5; i++) {
            System.out.println("******** EMERGENCY E-STOP TRIGGERED ********\n" +
                    "The emergency E-Stop has been triggered.\n" +
                    "the roboRIO will need to be rebooted before\n" +
                    "the robot can be enabled again.\n");
        }

    }


    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
