/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.drive;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveRotate extends CommandBase {
    private final DriveTrain m_drive;
    private final double degrees, rotationSpeed;
    private final SlewRateLimiter filter;
    private double initialRotation;

    public DriveRotate(DriveTrain drive, double degrees, double rotationSpeed) {
        this.degrees = degrees;
        this.rotationSpeed = rotationSpeed;
        m_drive = drive;
        addRequirements(drive);

        filter = new SlewRateLimiter(3);
    }

    /**
     * Called when the command is initially scheduled.
     */
    @Override
    public void initialize() {
        m_drive.resetGyro();
        initialRotation = m_drive.getAngle().getDegrees();
    }

    /**
     * Called every time the scheduler runs while the command is scheduled.
     */
    @Override
    public void execute() {
        m_drive.arcadeDrive(0, -filter.calculate(rotationSpeed));
    }

    /**
     * Called once the command ends or is interrupted.
     *
     * @param interrupted whether the command was interrupted by another one
     */
    @Override
    public void end(boolean interrupted) {
        m_drive.arcadeDrive(0, 0);
    }

    /**
     * Returns true when the command should end.
     *
     * @return whether the command should end
     */
    @Override
    public boolean isFinished() {
        return m_drive.getAngle().getDegrees() >= initialRotation + degrees;
        // return false;
    }
}