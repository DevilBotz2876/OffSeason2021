/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.ShuffleboardManager;

import java.util.function.DoubleSupplier;

/**
 * Drive command subsystem
 */
public class DriveCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveTrain m_drive;
    private final DoubleSupplier m_left;
    private final DoubleSupplier m_right;
    private final SlewRateLimiter filterLeft;
    private final SlewRateLimiter filterRight;

    /**
     * Creates a new DriveCommand.
     *
     * @param drive Drive train supplied by method
     * @param left Left motor speed
     * @param right Right motor speed
     */
    public DriveCommand(DriveTrain drive, DoubleSupplier left, DoubleSupplier right) {
        m_drive = drive;
        m_left = left;
        m_right = right;
        addRequirements(drive);

        // Creates a SlewRateLimiter to limit the rate of change of the motors
        filterLeft = new SlewRateLimiter(3.75);
        filterRight = new SlewRateLimiter(3.75);
    }

    /**
     * Called when the command is initially scheduled.
     */
    @Override
    public void initialize() {
    }

    /**
     * Called every time the scheduler runs while the command is scheduled.
     */
    @Override
    public void execute() {
        double r;
        double l;

        if (ShuffleboardManager.getInstance().getSlewLimit()) {
            r = filterRight.calculate(m_right.getAsDouble());
            l = filterLeft.calculate(m_left.getAsDouble());
        } else {
            r = m_right.getAsDouble();
            l = m_left.getAsDouble();
        }

        // (a*(x^3)+(b-a)*x)*1.1
        double a = 0.5;
        double b = 0.9;

        // Modified curve
        r = (a * (r * r * r) + (b - a) * r) * 1.1;
        l = (a * (l * l * l) + (b - a) * l) * 1.1;

        m_drive.tankDrive(r, l);
    }

    /**
     * Called once the command ends or is interrupted.
     *
     * @param interrupted Whether the command was interrupted.
     */
    @Override
    public void end(boolean interrupted) {
    }

    /**
     * Returns true when the command should end.
     *
     * @return Whether the command should end.
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
