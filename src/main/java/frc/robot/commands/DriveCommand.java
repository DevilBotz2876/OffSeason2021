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
 * An example command that uses an example subsystem.
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
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveTrain drive, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drive = drive;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(drive);

    // Creates a SlewRateLimiter that limits the rate of change of the signal to 0.5 units per second
    filterLeft = new SlewRateLimiter(3);
    filterRight = new SlewRateLimiter(3);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
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

    // y=a(x^3)+(1-a)x
    double a = -0.1;
    // f = (a * (f*f*f)) + ((1-a) * f);
    // Modified curve
    r = (a * (r * r * r)+ (0.9-a) * r) * 1.1;
    l = (a * (l * l * l)+ (0.9-a) * l) * 1.1;


    m_drive.arcadeDrive(f, -r);
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
