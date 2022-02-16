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
private WPI_TalonFX leftMidMotor;
private WPI_TalonFX leftBackMotor;
private WPI_TalonFX rightFrontMotor;
private WPI_TalonFX rightMidMotor;
private WPI_TalonFX rightBackMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

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

	public Drivetrain() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftFrontMotor = new WPI_TalonFX(13);
 
 

leftMidMotor = new WPI_TalonFX(14);
 
 

leftBackMotor = new WPI_TalonFX(15);
 
 

<<<<<<< HEAD
rightFrontMotor = new WPI_TalonFX(28);
=======
rightFrontMotor = new WPI_TalonFX(26);
>>>>>>> 035001f86f73ff0f120c1de3ed4072bed5566c71
 
 

rightMidMotor = new WPI_TalonFX(2);
 
 

rightBackMotor = new WPI_TalonFX(3);
 
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		// Need encoders for drive x feet?
		// SmartDashboard.putNumber("leftEnc", getLeftEncoder());
		// SmartDashboard.putNumber("rightEnc", getRightEncoder());

	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run when in simulation

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initializeMotors() {

		leftFrontMotor.configFactoryDefault();
		rightFrontMotor.configFactoryDefault();

		rightBackMotor.setInverted(true);
		rightMidMotor.setInverted(true);
		rightFrontMotor.setInverted(true);

		rightBackMotor.follow(rightFrontMotor);
		rightMidMotor.follow(rightFrontMotor);
		leftBackMotor.follow(leftFrontMotor);
		leftMidMotor.follow(leftFrontMotor);

		rightBackMotor.configOpenloopRamp(0.5);
		rightFrontMotor.configOpenloopRamp(0.5);
		rightMidMotor.configOpenloopRamp(0.5);
		leftBackMotor.configOpenloopRamp(0.5);
		leftFrontMotor.configOpenloopRamp(0.5);
		leftMidMotor.configOpenloopRamp(0.5);

	}

	public void setBrakeMode() {
		leftFrontMotor.setNeutralMode(NeutralMode.Brake);
		rightFrontMotor.setNeutralMode(NeutralMode.Brake);
	}

	public void setCoastMode() {
		leftFrontMotor.setNeutralMode(NeutralMode.Coast);
		rightFrontMotor.setNeutralMode(NeutralMode.Coast);
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
		leftFrontMotor.set(l);
		rightFrontMotor.set(r);
	}

	public void stop() {
		leftFrontMotor.set(0);
		rightFrontMotor.set(0);
	}

	public void setControlMode(ControlMode mode) {
		leftFrontMotor.set(mode, 0);
		rightFrontMotor.set(mode, 0);
	}

	public ControlMode getControlMode() {
		return leftFrontMotor.getControlMode();
	}

	public void setReversed(boolean rev) {
		isReversed = rev;
	}

	public boolean getReversed() {
		return isReversed;
	}

	public void setHalfSpeed() {
		halfSpeed = !halfSpeed;
	}

	public void setQuarterSpeed() {
		quarterSpeed = !quarterSpeed;
	}

	// mehods for motion magic for drive x feet

	public void resetEncoders() {
		leftFrontMotor.getSensorCollection().setIntegratedSensorPosition(0, kTimeoutMs);
		rightFrontMotor.getSensorCollection().setIntegratedSensorPosition(0, kTimeoutMs);
	}

	public void initMotionMagic(double distance) {

		leftFrontMotor.configFactoryDefault();
		rightFrontMotor.configFactoryDefault();

		TalonFXInvertType _leftInvert = TalonFXInvertType.CounterClockwise; // Same as invert = "false"
		TalonFXInvertType _rightInvert = TalonFXInvertType.Clockwise; // Same as invert = "true"

		/* Configure output and sensor direction */
		leftFrontMotor.setInverted(_leftInvert);
		leftMidMotor.setInverted(_leftInvert);
		leftBackMotor.setInverted(_leftInvert);
		rightFrontMotor.setInverted(_rightInvert);
		rightMidMotor.setInverted(_rightInvert);
		rightBackMotor.setInverted(_rightInvert);

		rightBackMotor.follow(rightFrontMotor);
		rightMidMotor.follow(rightFrontMotor);
		leftBackMotor.follow(leftFrontMotor);
		leftMidMotor.follow(leftFrontMotor);

		rightBackMotor.configOpenloopRamp(0.5);
		rightFrontMotor.configOpenloopRamp(0.5);
		rightMidMotor.configOpenloopRamp(0.5);
		leftBackMotor.configOpenloopRamp(0.5);
		leftFrontMotor.configOpenloopRamp(0.5);
		leftMidMotor.configOpenloopRamp(0.5);

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
}
