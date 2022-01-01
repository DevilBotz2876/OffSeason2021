package frc.robot.commands.autonomous.buttons;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class EStop extends CommandBase {
    /**
     * Called when the command is first initialized.
     */
    @Override
    public void initialize() {
        //when EStop is not true, the command scheduler is run, i.e. when EStop is true, the robot must be restarted to enable it again
        Constants.EStop = true;
        SmartDashboard.putBoolean("E-Stop", true);

        for (int i = 0; i < 5; i++) {
            System.out.println("******** EMERGENCY E-STOP TRIGGERED ********\n" +
                    "The emergency E-Stop has been triggered.\n" +
                    "the roboRIO will need to be rebooted before\n" +
                    "the robot can be enabled again.\n");
        }

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
