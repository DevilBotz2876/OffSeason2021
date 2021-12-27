/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveCurve extends CommandBase {
    private final DriveTrain m_drive;
    private final double m_distance;
    private final double m_speed;
    private final double m_rotation;
    private final SlewRateLimiter filter;

    /**
     * Creates a new DriveDistance.
     *
     * @param inches The number of inches the robot will drive
     * @param speed The speed at which the robot will drive
     * @param drive The drive subsystem on which this command will run
     */
    public DriveCurve(DriveTrain drive, double inches, double speed, double rotation) {
        m_distance = inches;
        m_speed = speed;
        m_drive = drive;
        m_rotation = rotation;
        addRequirements(drive);

        filter = new SlewRateLimiter(3);
    }

    @Override
    public void initialize() {
        m_drive.resetEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drive.arcadeDrive(filter.calculate(m_speed), filter.calculate(m_rotation));
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return m_drive.getAverageEncoderDistance() >= m_distance;
    }
}