/*-------------------------------------------------------------------------------*/
/* Copyright (c) 2021-2022 BHS Devilbotz. All Rights Reserved.                   */
/* Open Source Software - may be modified, commercialized, distributed,          */
/* sub-licensed and used for private use under the terms of the License.md       */
/* file in the root of the source code tree.                                     */
/*                                                                               */
/* When doing any of the above, you MUST include the original                    */
/* copyright and license files in any and all revised/modified code.             */
/* You may NOT remove this header under any circumstance unless explicitly noted */
/*-------------------------------------------------------------------------------*/

package bhs.devilbotz.commands.autonomous.buttons;


import bhs.devilbotz.util.ShuffleboardManager;
import edu.wpi.first.wpilibj2.command.CommandBase;

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