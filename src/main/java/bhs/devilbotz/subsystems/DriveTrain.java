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

package bhs.devilbotz.subsystems;

import bhs.devilbotz.Constants;
import bhs.devilbotz.util.ShuffleboardManager;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

    /**
     * Creates a new DriveTrain.
     */

    private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(2);
    private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(4);
    private final WPI_TalonSRX rightFollower = new WPI_TalonSRX(1);
    private final WPI_TalonSRX leftFollower = new WPI_TalonSRX(3);

    private static final AHRS navx = new AHRS(SPI.Port.kMXP);
    DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
    double oldLeft = 0;
    double oldRight = 0;

    public DriveTrain() {

        // https://phoenix-documentation.readthedocs.io/en/latest/ch13_MC.html#follower
        rightFollower.follow(rightMaster);
        leftFollower.follow(leftMaster);

        // https://phoenix-documentation.readthedocs.io/en/latest/ch13_MC.html#inverts
        rightMaster.setInverted(false);
        rightFollower.setInverted(InvertType.FollowMaster);
        leftMaster.setInverted(false);
        leftFollower.setInverted(InvertType.FollowMaster);
        leftMaster.setSensorPhase(true);
        rightMaster.setSensorPhase(true);

        TalonSRXConfiguration allConfigs = new TalonSRXConfiguration();

        allConfigs.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;

        leftMaster.configAllSettings(allConfigs);
        rightMaster.configAllSettings(allConfigs);

        resetGyro();
        resetEncoders();
        setTalonMode(NeutralMode.Brake);
    }

    public void resetGyro() {
        navx.reset();
    }

    public void resetEncoders() {
        leftMaster.setSelectedSensorPosition(0, 0, 0);
        rightMaster.setSelectedSensorPosition(0, 0, 0);
    }

    /**
     * Calculates the distance traveled by the robot by reading encoder values
     *
     * @return the linear distance traveled by the robot in inches
     */
    public double getAverageEncoderDistance() {
        double leftDistance = leftMaster.getSelectedSensorPosition()
                * (Constants.AutoConstants.kWheelDiameterInches * Math.PI / 4096);
        double rightDistance = rightMaster.getSelectedSensorPosition()
                * (Constants.AutoConstants.kWheelDiameterInches * Math.PI / 4096);
        return ((Math.abs(leftDistance) + Math.abs(rightDistance)) / 2);
    }

    public void tankDrive(double leftValue, double rightValue) {
        // Lock the values if they are close enough to each other
        if (ShuffleboardManager.getInstance().getForwardSnapping()) {
            if ((Math.abs((leftValue - rightValue) / rightValue) < 0.25 || Math.abs((rightValue - leftValue) / leftValue) < 0.25) && ((leftValue > 0.05 || leftValue < -0.05) || (rightValue > 0.05 || rightValue < -0.05))) {
                oldLeft = leftValue;
                oldRight = rightValue;

                leftValue = (oldLeft + oldRight) / 2;

                rightValue = (oldLeft + oldRight) / 2;

                SmartDashboard.putBoolean("Forward Lock", true);
            } else {
                SmartDashboard.putBoolean("Forward Lock", false);
            }
        }

        differentialDrive.tankDrive(leftValue, rightValue);
    }

    public void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
    }

    public void setTalonMode(NeutralMode mode) {
        leftMaster.setNeutralMode(mode);
        rightMaster.setNeutralMode(mode);
        leftFollower.setNeutralMode(mode);
        rightFollower.setNeutralMode(mode);
    }

    /**
     * Returns the angle of the robot as a Rotation2d.
     *
     * @return The angle of the robot.
     */
    public Rotation2d getAngle() {
        // Negating the angle because WPILib gyros are CW positive.
        return Rotation2d.fromDegrees(-navx.getAngle());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Distance", getAverageEncoderDistance());

        // Robot velocity from navx
        SmartDashboard.putNumber("Robot Velocity", navx.getVelocityZ() + navx.getVelocityX());

    }

    public static AHRS getNavx() {
        return navx;
    }
}
