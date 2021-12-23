package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveTimed extends CommandBase {
    private final DriveTrain m_drive;
    private final double time, speed;
    private long startTime;

    public DriveTimed(DriveTrain drive, double time, double speed) {
        m_drive = drive;
        this.time = time;
        this.speed = speed;
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drive.arcadeDrive(speed, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }


    @Override
    public boolean isFinished() {
        return (System.currentTimeMillis() - startTime) / 1000.0 >= time;
    }
}