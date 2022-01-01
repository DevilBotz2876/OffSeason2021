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

package bhs.devilbotz.commands.autonomous.autoCommands;

import bhs.devilbotz.commands.autonomous.drive.DriveRotate;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import bhs.devilbotz.commands.autonomous.drive.DriveCurve;
import bhs.devilbotz.commands.autonomous.drive.DriveDistance;
import bhs.devilbotz.commands.autonomous.drive.DriveTimed;
import bhs.devilbotz.subsystems.DriveTrain;

public class AutoTest extends SequentialCommandGroup {
    public AutoTest(DriveTrain drive) {
        addCommands(
                new DriveDistance(drive, 40, 0.5),
                new DriveRotate(drive, 90, 0.5),
                new DriveTimed(drive, 5, 0.7),
                new DriveCurve(drive, 40, 0.7, 90));
    }
}
