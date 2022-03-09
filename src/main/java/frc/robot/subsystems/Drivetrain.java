// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.SerialPort.Port; //might change to I2C
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants.DriveConstants;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Drivetrain extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX leftFrontMotor;
private WPI_TalonFX leftBackMotor;
private WPI_TalonFX rightFrontMotor;
private WPI_TalonFX rightBackMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private MotorControllerGroup m_leftMotors; 

    private MotorControllerGroup m_rightMotors; 

    // The robot's drive
    // private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors,
    // m_rightMotors);

    // The gyro sensor
    private final AHRS m_gyro = new AHRS(Port.kMXP);
    private double gyroOffset = 0;

    // Odometry class for tracking robot pose
    public final DifferentialDriveOdometry m_odometry;

	private static boolean isReversed = false;
	private boolean halfSpeed = false;
	private boolean quarterSpeed = false;

	private static final double MM_VEL = 4000;
	private static final double MM_ACC = 2000;

	private final static int kTimeoutMs = 30;

	private final static double kNeutralDeadband = 0.001;

	private final static int kEncoderUnitsPerRotation = 51711;

	private static final double TICKSPERFEET = 9938;

	private final static int PID_PRIMARY = 0;
	private final static int PID_TURN = 1;
	private final static int kSlot_Distance = 0;
	private final static int kSlot_Turning = 1;

	private static final double DISTANCE_kP = 0.1;
	private static final double DISTANCE_kI = 0.0;
	private static final double DISTANCE_kD = 0.0;
	private static final double DISTANCE_kF = 0.0;
	private static final double DISTANCE_kIzone = 100;
	private static final double DISTANCE_PEAK = 0.50;

	private static final double TURN_kP = 0.2;
	private static final double TURN_kI = 0.0;
	private static final double TURN_kD = 0.0;
	private static final double TURN_kF = 0.0;
	private static final double TURN_kIzone = 200;
	private static final double TURN_PEAK = 1.00;

	private static double m_distance;
	private static final double THRESHOLD = 200;
	private static final double MAX_TEMP = 35;

	public Drivetrain() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftFrontMotor = new WPI_TalonFX(1);
 
 

leftBackMotor = new WPI_TalonFX(2);
 
 

rightFrontMotor = new WPI_TalonFX(3);
 
 

rightBackMotor = new WPI_TalonFX(4);
 
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

	m_leftMotors = new MotorControllerGroup(leftFrontMotor, leftBackMotor);
	m_rightMotors = new MotorControllerGroup(rightFrontMotor, rightBackMotor);

	m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
	}

	 @Override
    public void periodic() {
        // This method will be called once per scheduler run
		// Put code here to run every loop
		SmartDashboard.putNumber("LeftEnc", leftFrontMotor.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("RightEnc", rightFrontMotor.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("POSE X", getPose().getX());
        SmartDashboard.putNumber("POSE Y", getPose().getY());

        m_odometry.update(Rotation2d.fromDegrees(getHeading()),
                leftFrontMotor.getSelectedSensorPosition(0) * DriveConstants.kEncoderDistancePerPulse,
                rightFrontMotor.getSelectedSensorPosition(0) * DriveConstants.kEncoderDistancePerPulse);
    }

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initializeMotors() {

		leftFrontMotor.configFactoryDefault();
		rightFrontMotor.configFactoryDefault();

		rightBackMotor.setInverted(true);
		rightFrontMotor.setInverted(true);

		rightBackMotor.follow(rightFrontMotor);
		leftBackMotor.follow(leftFrontMotor);

		rightBackMotor.configOpenloopRamp(0.5);
		rightFrontMotor.configOpenloopRamp(0.5);
		leftBackMotor.configOpenloopRamp(0.5);
		leftFrontMotor.configOpenloopRamp(0.5);

		leftFrontMotor.configNeutralDeadband(0.05);
		rightFrontMotor.configNeutralDeadband(0.05);

		resetEncoders();
		setBrakeMode();

    }

  
	public void run(double l, double r) {
		if (isReversed) {
			l *= -1; // inverse left motor speed
			r *= -1; // inverse right motor speed
		}
		if (halfSpeed) {
			l *= 0.5;
			r *= 0.5;
		} else if (quarterSpeed) {
			l *= 0.25;
			r *= 0.25;
		}

		l = Math.pow(l, 3);
		r = Math.pow(r, 3);
		leftFrontMotor.set(l);
		rightFrontMotor.set(r);
	}

	public void stop() {
		leftFrontMotor.set(0);
		rightFrontMotor.set(0);
	}

    public double getHeading() {
		return m_gyro.getRotation2d().getDegrees() - gyroOffset;
	}


    public void setBrakeMode() {
        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void setCoastMode() {
        leftFrontMotor.setNeutralMode(NeutralMode.Coast);
        rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    }


    public void setControlMode(ControlMode mode) {
        leftFrontMotor.set(mode, 0);
        rightFrontMotor.set(mode, 0);
    }

    public ControlMode getControlMode() {
        return leftFrontMotor.getControlMode();
    }

    public void resetEncoders() {
        leftFrontMotor.setSelectedSensorPosition(0);
        rightFrontMotor.setSelectedSensorPosition(0);
    }
    public void setReversed(boolean r)
    {
        isReversed = r;
        //set isReversed to bool whether is reversed or not
    }
    public boolean getReversed()
    {
        return isReversed;
    }


    public void setHalfSpeed(){
        halfSpeed = !halfSpeed;
    }

    public void setQuarterSpeed(){
        quarterSpeed = !quarterSpeed;

	}
	
	public void initMotionMagic(double distance) {

		leftFrontMotor.configFactoryDefault();
		rightFrontMotor.configFactoryDefault();

		TalonFXInvertType _leftInvert = TalonFXInvertType.CounterClockwise; // Same as invert = "false"
		TalonFXInvertType _rightInvert = TalonFXInvertType.Clockwise; // Same as invert = "true"

		/* Configure output and sensor direction */
		leftFrontMotor.setInverted(_leftInvert);
		leftBackMotor.setInverted(_leftInvert);
		rightFrontMotor.setInverted(_rightInvert);
		rightBackMotor.setInverted(_rightInvert);

		rightBackMotor.follow(rightFrontMotor);
		leftBackMotor.follow(leftFrontMotor);

		rightBackMotor.configOpenloopRamp(0.5);
		rightFrontMotor.configOpenloopRamp(0.5);
		leftBackMotor.configOpenloopRamp(0.5);
		leftFrontMotor.configOpenloopRamp(0.5);

		/** Config Objects for motor controllers */
		TalonFXConfiguration _leftConfig = new TalonFXConfiguration();
		TalonFXConfiguration _rightConfig = new TalonFXConfiguration();

		rightFrontMotor.set(TalonFXControlMode.PercentOutput, 0);
		leftFrontMotor.set(TalonFXControlMode.PercentOutput, 0);

		/* Set Neutral Mode */
		leftFrontMotor.setNeutralMode(NeutralMode.Brake);
		rightFrontMotor.setNeutralMode(NeutralMode.Brake);

		/** Feedback Sensor Configuration */

		/* Configure the left Talon's selected sensor as local Integrated Sensor */
		_leftConfig.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); // Local
																													// Feedback
																													// Source

		/*
			* Configure the Remote Talon's selected sensor as a remote sensor for the right
			* Talon
			*/
		_rightConfig.remoteFilter0.remoteSensorDeviceID = leftFrontMotor.getDeviceID(); // Device ID of Source
		_rightConfig.remoteFilter0.remoteSensorSource = RemoteSensorSource.TalonFX_SelectedSensor; // Remote Feedback
																									// Source

		/*
			* Now that the Left sensor can be used by the master Talon,
			* set up the Left (Aux) and Right (Master) distance into a single
			* Robot distance as the Master's Selected Sensor 0.
			*/
		setRobotDistanceConfigs(_rightInvert, _rightConfig);

		/*
			* Setup difference signal to be used for turn when performing Drive Straight
			* with encoders
			*/
		setRobotTurnConfigs(_rightInvert, _rightConfig);

		/* Configure neutral deadband */
		_rightConfig.neutralDeadband = kNeutralDeadband;
		_leftConfig.neutralDeadband = kNeutralDeadband;

		/* Motion Magic Configurations */
		_rightConfig.motionAcceleration = MM_ACC;
		_rightConfig.motionCruiseVelocity = MM_VEL;

		/**
		 * Max out the peak output (for all modes).
		 * However you can limit the output of a given PID object with
		 * configClosedLoopPeakOutput().
		 */
		_leftConfig.peakOutputForward = +1.0;
		_leftConfig.peakOutputReverse = -1.0;
		_rightConfig.peakOutputForward = +1.0;
		_rightConfig.peakOutputReverse = -1.0;

		/* FPID Gains for distance servo */
		_rightConfig.slot0.kP = DISTANCE_kP;
		_rightConfig.slot0.kI = DISTANCE_kI;
		_rightConfig.slot0.kD = DISTANCE_kD;
		_rightConfig.slot0.kF = DISTANCE_kF;
		_rightConfig.slot0.integralZone = DISTANCE_kIzone;
		_rightConfig.slot0.closedLoopPeakOutput = DISTANCE_PEAK;
		_rightConfig.slot0.allowableClosedloopError = 0;

		/* FPID Gains for turn servo */
		_rightConfig.slot1.kP = TURN_kP;
		_rightConfig.slot1.kI = TURN_kI;
		_rightConfig.slot1.kD = TURN_kD;
		_rightConfig.slot1.kF = TURN_kF;
		_rightConfig.slot1.integralZone = TURN_kIzone;
		_rightConfig.slot1.closedLoopPeakOutput = TURN_PEAK;
		_rightConfig.slot1.allowableClosedloopError = 0;

		int closedLoopTimeMs = 1;
		_rightConfig.slot0.closedLoopPeriod = closedLoopTimeMs;
		_rightConfig.slot1.closedLoopPeriod = closedLoopTimeMs;
		_rightConfig.slot2.closedLoopPeriod = closedLoopTimeMs;
		_rightConfig.slot3.closedLoopPeriod = closedLoopTimeMs;

		leftFrontMotor.configAllSettings(_leftConfig);
		rightFrontMotor.configAllSettings(_rightConfig);

		/* Set status frame periods to ensure we don't have stale data */
		rightFrontMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, kTimeoutMs);
		rightFrontMotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, kTimeoutMs);
		rightFrontMotor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, kTimeoutMs);
		rightFrontMotor.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, kTimeoutMs);
		leftFrontMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, kTimeoutMs);

		/* Initialize */
		rightFrontMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 10);
		resetEncoders();

		m_distance = distance;
		m_distance *= TICKSPERFEET;

		rightFrontMotor.selectProfileSlot(kSlot_Distance, PID_PRIMARY);
		rightFrontMotor.selectProfileSlot(kSlot_Turning, PID_TURN);

		double target_turn = rightFrontMotor.getSelectedSensorPosition(1);

		rightFrontMotor.set(TalonFXControlMode.MotionMagic, m_distance, DemandType.AuxPID, target_turn);
		leftFrontMotor.follow(rightFrontMotor, FollowerType.AuxOutput1);

	}

	public void setRobotDistanceConfigs(TalonFXInvertType masterInvertType, TalonFXConfiguration masterConfig) {
		/* Check if we're inverted */
		if (masterInvertType == TalonFXInvertType.Clockwise) {
			masterConfig.diff0Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); // Local Integrated
																								// Sensor
			masterConfig.diff1Term = TalonFXFeedbackDevice.RemoteSensor0.toFeedbackDevice(); // Aux Selected Sensor
			masterConfig.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorDifference.toFeedbackDevice(); // Diff0
																														// -
																														// Diff1
		} else {
			/* Master is not inverted, both sides are positive so we can sum them. */
			masterConfig.sum0Term = TalonFXFeedbackDevice.RemoteSensor0.toFeedbackDevice(); // Aux Selected Sensor
			masterConfig.sum1Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); // Local IntegratedSensor
			masterConfig.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorSum.toFeedbackDevice(); // Sum0
																													// +
																													// Sum1
		}

		/*
			* Since the Distance is the sum of the two sides, divide by 2 so the total
			* isn't double
			* the real-world value
			*/
		masterConfig.primaryPID.selectedFeedbackCoefficient = 0.5;
	}

	public void setRobotTurnConfigs(TalonFXInvertType masterInvertType, TalonFXConfiguration masterConfig) {
		/* Check if we're inverted */
		if (masterInvertType == TalonFXInvertType.Clockwise) {
			masterConfig.sum0Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); // Local Integrated
																								// Sensor
			masterConfig.sum1Term = TalonFXFeedbackDevice.RemoteSensor0.toFeedbackDevice(); // Aux Selected Sensor
			masterConfig.auxiliaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorSum.toFeedbackDevice(); // Sum0
																													// +
																													// Sum1
			masterConfig.auxPIDPolarity = true;
		} else {
			/* Master is not inverted, both sides are positive so we can diff them. */
			masterConfig.diff0Term = TalonFXFeedbackDevice.RemoteSensor1.toFeedbackDevice(); // Aux Selected Sensor
			masterConfig.diff1Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); // Local
																								// IntegratedSensor
			masterConfig.auxiliaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorDifference
					.toFeedbackDevice(); // Sum0 + Sum1
			/*
				* With current diff terms, a counterclockwise rotation results in negative
				* heading with a right master
				*/
			masterConfig.auxPIDPolarity = true;
		}
		masterConfig.auxiliaryPID.selectedFeedbackCoefficient = kEncoderUnitsPerRotation / kEncoderUnitsPerRotation;
	}

	public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                leftFrontMotor.getSelectedSensorVelocity() * DriveConstants.kEncoderDistancePerPulse,
                rightFrontMotor.getSelectedSensorVelocity() * DriveConstants.kEncoderDistancePerPulse);
    }


    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose2d The pose to which to set the odometry.
     */
    public void resetOdometry(edu.wpi.first.math.geometry.Pose2d pose2d) {
        resetEncoders();
        m_odometry.resetPosition(pose2d, Rotation2d.fromDegrees(getHeading()));
    }

    /**
     * Controls the left and right sides of the drive directly with `s.
     *
     * @param leftVolts  the commanded left output
     * @param rightVolts the commanded right output
     */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_leftMotors.setVoltage(leftVolts);
        m_rightMotors.setVoltage(rightVolts);
        SmartDashboard.putNumber("Left Encoder", leftFrontMotor.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Right Encoder", rightFrontMotor.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("HEADING", m_odometry.getPoseMeters().getRotation().getDegrees());
        
    }

    /**
     * Resets the drive encoders to currently read a position of 0.
     */

    public void setEncoders(int left, int right) {
        leftFrontMotor.setSelectedSensorPosition(left);
        rightFrontMotor.setSelectedSensorPosition(right);
    }

    /**
     * Zeroes the heading of the robot.
     */
    public void zeroHeading() {
        //m_gyro.reset();
        gyroOffset = m_gyro.getAngle();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from -180 to 180
     */


	public double getLeftEncoder() {
		return leftFrontMotor.getSelectedSensorPosition();
	}

	public double getRightEncoder() {
		return rightFrontMotor.getSelectedSensorPosition();
	}

	public boolean isAtPIDDestination() {
		return (Math.abs(m_distance) - Math.abs(getLeftEncoder()) < THRESHOLD
				|| Math.abs(m_distance) - Math.abs(getRightEncoder()) < THRESHOLD);
	}

	public boolean checkTemp() {
        if (rightFrontMotor.getTemperature() > MAX_TEMP || rightBackMotor.getTemperature() > MAX_TEMP 
		|| leftFrontMotor.getTemperature() > MAX_TEMP || leftBackMotor.getTemperature() > MAX_TEMP)
            return true;
        else
            return false;
    }

	public double getGyroZ(){
		SmartDashboard.putNumber("ZAxis", m_gyro.getWorldLinearAccelZ());
		return m_gyro.getWorldLinearAccelZ();
	}
}
