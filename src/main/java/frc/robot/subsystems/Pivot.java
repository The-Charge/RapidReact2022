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
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Pivot extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX leftPivotMotor;
private WPI_TalonFX rightPivotMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private static final double PIVOT_VEL = 2000;
	private static final double PIVOT_ACC = 2000;

	private static double m_angle;
	private static int THRESHOLD = 50;
	private static final double TICKSPERDEGREE = 90;

	private static final int kTimeoutMs = 30;
	private static final int PID_INDEX = 0;
	private static final int PID_SLOT = 0;

	private static final double PIVOT_kP = 0.2;
	private static final double PIVOT_kI = 0.0005;
	private static final double PIVOT_kD = 0.0;
	private static final double PIVOT_kF = 0;
	private static final int PIVOT_SMOOTHING = 4;


	private static final double MAX_TEMP = 35;




	TalonFXInvertType _leftInvert = TalonFXInvertType.Clockwise; //Same as invert = "false"
	TalonFXInvertType _rightInvert = TalonFXInvertType.Clockwise; //Same as invert = "true"

	/** Config Objects for motor controllers */
	TalonFXConfiguration _leftConfig = new TalonFXConfiguration();
	TalonFXConfiguration _rightConfig = new TalonFXConfiguration();
	

    private final static double kNeutralDeadband = 0.001;

	private final static int REMOTE_0 = 0;
	private final static int REMOTE_1 = 1;
	private final static int PID_PRIMARY = 0;
	private final static int PID_TURN = 1;
	private final static int kSlot_Distance = 0;
	private final static int kSlot_Turning = 1;

	private static final double DISTANCE_kP = 0.04;
	private static final double DISTANCE_kI = 0.0005;
	private static final double DISTANCE_kD = 0.0;
	private static final double DISTANCE_kF = 0.0;
	private static final double DISTANCE_kIzone = 100;
	private static final double DISTANCE_PEAK = 0.50;

	private static final double TURN_kP = 0.01;
	private static final double TURN_kI = 0.0002;
	private static final double TURN_kD = 4.0;
	private static final double TURN_kF = 0.0;
	private static final double TURN_kIzone = 200;
	private static final double TURN_PEAK = 1.00;
    
    /**
    *
    */
    public Pivot() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftPivotMotor = new WPI_TalonFX(5);
 
 

rightPivotMotor = new WPI_TalonFX(6);
 
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
		SmartDashboard.putNumber("Pivot Left Enc", leftPivotMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Pivot Right Enc", rightPivotMotor.getSelectedSensorPosition());

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	// public void initializeMotors() {
	// 	rightPivotMotor.set(TalonFXControlMode.PercentOutput, 0);
	// 	leftPivotMotor.set(TalonFXControlMode.PercentOutput, 0);
		
	// 	/* Set Neutral Mode */
	// 	leftPivotMotor.setNeutralMode(NeutralMode.Brake);
	// 	rightPivotMotor.setNeutralMode(NeutralMode.Brake);
		
	// 	/** Feedback Sensor Configuration */
		
	// 	/* Configure the left Talon's selected sensor as local Integrated Sensor */
	// 	_leftConfig.primaryPID.selectedFeedbackSensor =	TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();	// Local Feedback Source

	// 	/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
	// 	_rightConfig.remoteFilter0.remoteSensorDeviceID =leftPivotMotor.getDeviceID(); // Device ID of Source
	// 	_rightConfig.remoteFilter0.remoteSensorSource = RemoteSensorSource.TalonFX_SelectedSensor; // Remote Feedback Source
		
	// 	/* Now that the Left sensor can be used by the master Talon,
	// 	 * set up the Left (Aux) and Right (Master) distance into a single
	// 	 * Robot distance as the Master's Selected Sensor 0. */
	// 	setRobotDistanceConfigs(_rightInvert, _rightConfig);
		
	// 	/* Setup difference signal to be used for turn when performing Drive Straight with encoders */
	// 	setRobotTurnConfigs(_rightInvert, _rightConfig);

	// 	/* Configure neutral deadband */
	// 	_rightConfig.neutralDeadband = kNeutralDeadband;
	// 	_leftConfig.neutralDeadband = kNeutralDeadband;
		
	// 	/* Motion Magic Configurations */
	// 	_rightConfig.motionAcceleration = 2000;
	// 	_rightConfig.motionCruiseVelocity = 4000;

	// 	/**
	// 	 * Max out the peak output (for all modes).  
	// 	 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
	// 	 */
	// 	_leftConfig.peakOutputForward = +1.0;
	// 	_leftConfig.peakOutputReverse = -1.0;
	// 	_rightConfig.peakOutputForward = +1.0;
	// 	_rightConfig.peakOutputReverse = -1.0;

	// 	/* FPID Gains for distance servo */
	// 	_rightConfig.slot0.kP = DISTANCE_kP;
	// 	_rightConfig.slot0.kI = DISTANCE_kI;
	// 	_rightConfig.slot0.kD = DISTANCE_kD;
	// 	_rightConfig.slot0.kF = DISTANCE_kF;
	// 	_rightConfig.slot0.integralZone = DISTANCE_kIzone;
	// 	_rightConfig.slot0.closedLoopPeakOutput = DISTANCE_PEAK;
	// 	_rightConfig.slot0.allowableClosedloopError = 0;

	// 	/* FPID Gains for turn servo */
	// 	_rightConfig.slot1.kP = TURN_kP;
	// 	_rightConfig.slot1.kI = TURN_kI;
	// 	_rightConfig.slot1.kD = TURN_kD;
	// 	_rightConfig.slot1.kF = TURN_kF;
	// 	_rightConfig.slot1.integralZone = TURN_kIzone;
	// 	_rightConfig.slot1.closedLoopPeakOutput = TURN_PEAK;
	// 	_rightConfig.slot1.allowableClosedloopError = 0;

		
	// 	int closedLoopTimeMs = 1;
	// 	_rightConfig.slot0.closedLoopPeriod = closedLoopTimeMs;
	// 	_rightConfig.slot1.closedLoopPeriod = closedLoopTimeMs;
	// 	_rightConfig.slot2.closedLoopPeriod = closedLoopTimeMs;
	// 	_rightConfig.slot3.closedLoopPeriod = closedLoopTimeMs;

	// 	leftPivotMotor.configAllSettings(_leftConfig);
	// 	rightPivotMotor.configAllSettings(_rightConfig);
		
	// 	/* Configure output and sensor direction */
	// 	leftPivotMotor.setInverted(_leftInvert);
	// 	rightPivotMotor.setInverted(_rightInvert);
		
	// 	/* Set status frame periods to ensure we don't have stale data */
	// 	rightPivotMotor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, kTimeoutMs);
	// 	rightPivotMotor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, kTimeoutMs);
	// 	rightPivotMotor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, kTimeoutMs);
	// 	rightPivotMotor.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, kTimeoutMs);
	// 	leftPivotMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, kTimeoutMs);

	// 	/* Initialize */
	// 	rightPivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 10);
	// 	//zeroSensors();
	// }

	public void zeroSensors() {
		leftPivotMotor.getSensorCollection().setIntegratedSensorPosition(0, kTimeoutMs);
		rightPivotMotor.getSensorCollection().setIntegratedSensorPosition(0, kTimeoutMs);
	}

	// public void initMotionMagic(double distance) {
	// 	m_angle = distance;
	// 	m_angle *= TICKSPERDEGREE;

	// 	rightPivotMotor.selectProfileSlot(kSlot_Distance, PID_PRIMARY);
	// 	rightPivotMotor.selectProfileSlot(kSlot_Turning, PID_TURN);

	// 	double target_turn = rightPivotMotor.getSelectedSensorPosition(1);

	// 	rightPivotMotor.set(TalonFXControlMode.MotionMagic, m_angle, DemandType.AuxPID, target_turn);
	// 	leftPivotMotor.follow(rightPivotMotor, FollowerType.AuxOutput1);

	// }

	public void setRobotDistanceConfigs(TalonFXInvertType masterInvertType, TalonFXConfiguration masterConfig){
		/* Check if we're inverted */
		if (masterInvertType == TalonFXInvertType.Clockwise){
			masterConfig.diff0Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); //Local Integrated Sensor
			masterConfig.diff1Term = TalonFXFeedbackDevice.RemoteSensor0.toFeedbackDevice();   //Aux Selected Sensor
			masterConfig.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorDifference.toFeedbackDevice(); //Diff0 - Diff1
		} else {
			/* Master is not inverted, both sides are positive so we can sum them. */
			masterConfig.sum0Term = TalonFXFeedbackDevice.RemoteSensor0.toFeedbackDevice();    //Aux Selected Sensor
			masterConfig.sum1Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); //Local IntegratedSensor
			masterConfig.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorSum.toFeedbackDevice(); //Sum0 + Sum1
		}

		/* Since the Distance is the sum of the two sides, divide by 2 so the total isn't double
		   the real-world value */
		masterConfig.primaryPID.selectedFeedbackCoefficient = 0.5;
	 }

	public void setRobotTurnConfigs(TalonFXInvertType masterInvertType, TalonFXConfiguration masterConfig){
		/* Check if we're inverted */
		if (masterInvertType == TalonFXInvertType.Clockwise){
			masterConfig.sum0Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); //Local Integrated Sensor
			masterConfig.sum1Term = TalonFXFeedbackDevice.RemoteSensor0.toFeedbackDevice();   //Aux Selected Sensor
			masterConfig.auxiliaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorSum.toFeedbackDevice(); //Sum0 + Sum1
			masterConfig.auxPIDPolarity = true;
		} else {
			/* Master is not inverted, both sides are positive so we can diff them. */
			masterConfig.diff0Term = TalonFXFeedbackDevice.RemoteSensor1.toFeedbackDevice();    //Aux Selected Sensor
			masterConfig.diff1Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice(); //Local IntegratedSensor
			masterConfig.auxiliaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.SensorDifference.toFeedbackDevice(); //Sum0 + Sum1
			/* With current diff terms, a counterclockwise rotation results in negative heading with a right master */
			masterConfig.auxPIDPolarity = true;
		}
		masterConfig.auxiliaryPID.selectedFeedbackCoefficient = 1;
	 }


	public void initializeMotors(){
		leftPivotMotor.setInverted(true);
		rightPivotMotor.setInverted(true);
		leftPivotMotor.setNeutralMode(NeutralMode.Brake);
		rightPivotMotor.setNeutralMode(NeutralMode.Brake);
	}

    public void initPivotMotionMagic(double angle){
		m_angle = angle * TICKSPERDEGREE;

		leftPivotMotor.configFactoryDefault();
		rightPivotMotor.configFactoryDefault();

		initializeMotors();

		leftPivotMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, PID_INDEX, kTimeoutMs);

		leftPivotMotor.configNeutralDeadband(0.001, kTimeoutMs);	

		leftPivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		leftPivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* Set the peak and nominal outputs */
		leftPivotMotor.configNominalOutputForward(0, kTimeoutMs);
		leftPivotMotor.configNominalOutputReverse(0, kTimeoutMs);
		leftPivotMotor.configPeakOutputForward(1, kTimeoutMs);
		leftPivotMotor.configPeakOutputReverse(-1, kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		leftPivotMotor.selectProfileSlot(PID_SLOT, PID_INDEX);
		leftPivotMotor.config_kF(PID_SLOT, PIVOT_kF, kTimeoutMs);
		leftPivotMotor.config_kP(PID_SLOT, PIVOT_kP, kTimeoutMs);
		leftPivotMotor.config_kI(PID_SLOT, PIVOT_kI, kTimeoutMs);
		leftPivotMotor.config_kD(PID_SLOT, PIVOT_kD, kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		leftPivotMotor.configMotionCruiseVelocity(PIVOT_VEL, kTimeoutMs);
		leftPivotMotor.configMotionAcceleration(PIVOT_ACC, kTimeoutMs);

		/* Zero the sensor once on robot boot up */
		//leftPivotMotor.setSelectedSensorPosition(0, PID_INDEX, kTimeoutMs);

		leftPivotMotor.configMotionSCurveStrength(PIVOT_SMOOTHING);

		rightPivotMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, PID_INDEX, kTimeoutMs);

		rightPivotMotor.configNeutralDeadband(0.001, kTimeoutMs);	

		rightPivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		rightPivotMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* Set the peak and nominal outputs */
		rightPivotMotor.configNominalOutputForward(0, kTimeoutMs);
		rightPivotMotor.configNominalOutputReverse(0, kTimeoutMs);
		rightPivotMotor.configPeakOutputForward(1, kTimeoutMs);
		rightPivotMotor.configPeakOutputReverse(-1, kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		rightPivotMotor.selectProfileSlot(PID_SLOT, PID_INDEX);
		rightPivotMotor.config_kF(PID_SLOT, PIVOT_kF, kTimeoutMs);
		rightPivotMotor.config_kP(PID_SLOT, PIVOT_kP, kTimeoutMs);
		rightPivotMotor.config_kI(PID_SLOT, PIVOT_kI, kTimeoutMs);
		rightPivotMotor.config_kD(PID_SLOT, PIVOT_kD, kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		rightPivotMotor.configMotionCruiseVelocity(PIVOT_VEL, kTimeoutMs);
		rightPivotMotor.configMotionAcceleration(PIVOT_ACC, kTimeoutMs);

		/* Zero the sensor once on robot boot up */
		//rightPivotMotor.setSelectedSensorPosition(0, PID_INDEX, kTimeoutMs);

		rightPivotMotor.configMotionSCurveStrength(PIVOT_SMOOTHING);

		leftPivotMotor.set(TalonFXControlMode.MotionMagic, m_angle);
		rightPivotMotor.set(TalonFXControlMode.MotionMagic, m_angle);
		
	}

    public double getLeftPivotEncoder(){
		return leftPivotMotor.getSelectedSensorPosition();
	}

	public double getRightPivotEncoder(){
		return rightPivotMotor.getSelectedSensorPosition();
	}

    public void resetPivotEncoders(){
		rightPivotMotor.setSelectedSensorPosition(0);
		leftPivotMotor.setSelectedSensorPosition(0);
	}


    public boolean isPivotAtDestination(){
		return (Math.abs(m_angle) - Math.abs(getLeftPivotEncoder()) < THRESHOLD || Math.abs(m_angle) - Math.abs(getRightPivotEncoder()) < THRESHOLD);
	}

	public void runPivot(double l, double r) {
		leftPivotMotor.set(l);
		rightPivotMotor.set(r);
	}

    public void stopPivot(){
		rightPivotMotor.set(0);
		leftPivotMotor.set(0);
	}

	public void setControlMode(ControlMode mode){
		leftPivotMotor.set(mode, 0);
		rightPivotMotor.set(mode, 0);
	}


	public boolean pastLimitSwitchLeftTele() {
		return leftPivotMotor.getSensorCollection().isRevLimitSwitchClosed() == 1
				|| leftPivotMotor.getSensorCollection().isFwdLimitSwitchClosed() == 1;
	}

	public boolean pastLimitSwitchRightTele() {
		return rightPivotMotor.getSensorCollection().isRevLimitSwitchClosed() == 1
				|| rightPivotMotor.getSensorCollection().isFwdLimitSwitchClosed() == 1;
	}

}

