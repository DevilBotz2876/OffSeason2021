/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.ShuffleboardManager;

import java.util.function.BooleanSupplier;

public class DriveTrain extends SubsystemBase {
  private final Field2d m_field = new Field2d();

  /**
   * Creates a new DriveTrain.
   */

  private static final double TRACK_WIDTH = 0.595; // meters

  private WPI_TalonSRX rightMaster = new WPI_TalonSRX(2);
  private WPI_TalonSRX leftMaster = new WPI_TalonSRX(4);
  private WPI_TalonSRX rightFollower = new WPI_TalonSRX(1);
  private WPI_TalonSRX leftFollower = new WPI_TalonSRX(3);


  private AHRS navx = new AHRS(SPI.Port.kMXP);

  DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

  // TODO what should these be? should they be different? find out next time on
  private final PIDController leftPIDController = new PIDController(1, 0, 0);
  private final PIDController rightPIDController = new PIDController(1, 0, 0);

  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(TRACK_WIDTH);

  public DriveTrain() {

    SmartDashboard.putData("Field", m_field);

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
    // odometry = new DifferentialDriveOdometry(getAngle());
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
  public NetworkTableEntry reversedControls;


  public void tankDrive(double leftValue, double rightValue) {
    // Lock the values if they are close enough to each other
    if (ShuffleboardManager.getInstance().getForwardSnapping()) {
      if (Math.abs((leftValue - rightValue) / rightValue) < 0.15 || Math.abs((rightValue - leftValue) / leftValue) < 0.15) {
        leftValue = (leftValue + rightValue) / 2;

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

  /**
   * Sets the desired wheel speeds.
   *
   * @param speeds The desired wheel speeds.
   */
  public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {

  }

  /**
   * Drives the robot with the given linear velocity and angular velocity.
   *
   * @param xSpeed Linear velocity in m/s.
   * @param rot    Angular velocity in rad/s.
   */
  @SuppressWarnings("ParameterName")
  public void drive(double xSpeed, double rot) {
    var wheelSpeeds = kinematics.toWheelSpeeds(new ChassisSpeeds(xSpeed, 0.0, rot));
    setSpeeds(wheelSpeeds);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distance", getAverageEncoderDistance());

    // Update the pose

  }
}
