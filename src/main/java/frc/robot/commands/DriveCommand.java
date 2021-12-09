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

import java.util.function.DoubleSupplier;

/**
 * An example command that uses an example subsystem.
 */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_drive;
  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;
  private final SlewRateLimiter filterForward;
  private final SlewRateLimiter filterRotation;

  /**
   * Creates a new DriveCommand.
   *
   * @param drive Drive train supplied by method
   */
  public DriveCommand(DriveTrain drive, DoubleSupplier left, DoubleSupplier right) {
    m_drive = drive;
    m_left = left;
    m_right = right;
    addRequirements(drive);

    // Creates a SlewRateLimiter that limits the rate of change of the signal to 0.5 units per second
    filterForward = new SlewRateLimiter(2.25);
    filterRotation = new SlewRateLimiter(5);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // double f = m_forward.getAsDouble();
    // double r = m_rotation.getAsDouble();

    // Added division to slow control speed
    // Slowing 10%
    double r = filterForward.calculate(m_right.getAsDouble());
    // Slowing 20%
    double l = filterRotation.calculate(m_left.getAsDouble());

    // y=a(x^3)+(1-a)x
    double a = .4;
    // f = (a * (f*f*f)) + ((1-a) * f);
    // Modified curve
    // r = (a * (r*r*r)) + ((0.9-a) * r);
    // l = (a * (l*l*l)) + ((0.9-a) * l);

    m_drive.tankDrive(r, l);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
