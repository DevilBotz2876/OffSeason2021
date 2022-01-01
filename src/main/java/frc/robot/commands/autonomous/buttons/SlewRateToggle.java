package frc.robot.commands.autonomous.buttons;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.util.ShuffleboardManager;

public class SlewRateToggle extends CommandBase {
    /**
     * Called when the command is first initialized.
     */
    @Override
    public void initialize() {
        ShuffleboardManager.getInstance().setSlewLimit(!ShuffleboardManager.getInstance().getSlewLimit());

        end(true);
    }


    /**
     * Called periodically during the command
     */
    @Override
    public void execute() {
    }

    /**
     * Called once the command ends or is interrupted.
     *
     * @param interrupted whether the command was interrupted or not
     */
    @Override
    public void end(boolean interrupted) {
    }

    /**
     * Returns true when the command should end.
     *
     * @return whether the command should end or not
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}