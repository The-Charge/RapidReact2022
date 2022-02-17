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

import frc.robot.Constants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Climber extends SubsystemBase {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX leftPivotMotor;
private WPI_TalonFX rightPivotMotor;
private WPI_TalonSRX leftTeleMotor;
private WPI_TalonSRX rightTeleMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	private static final double PIVOT_VEL = 2000;
	private static final double PIVOT_ACC = 2000;

	private static final double TELE_VEL = 15000;
	private static final double TELE_ACC = 6000;

	private static double m_angle;
	private static double m_distance;
	private static int THRESHOLD = 50;
	private static final double TICKSPERDEGREE = 1000;
	private static final double TICKSPERFOOT = 14000;
	private static final double TICKS_TOP = 42000;
	private static final double TICKS_BOTTOM = 0;
	private static final double SAFETY_TICKS_TOP = 39000;
	private static final double SAFETY_TICKS_BOTTOM = 3000;

	private static final int kTimeoutMs = 30;
	private static final int PID_INDEX = 0;
	private static final int PID_SLOT = 0;

	private static final double PIVOT_kP = 0.2;
	private static final double PIVOT_kI = 0;
	private static final double PIVOT_kD = 0.2;
	private static final double PIVOT_kF = 0;
	private static final int PIVOT_SMOOTHING = 4;
	private static final int TELE_SMOOTHING = 4;
	/**
	*
	*/
	public Climber() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftPivotMotor = new WPI_TalonFX(16);
 
 

rightPivotMotor = new WPI_TalonFX(17);
 
 

leftTeleMotor = new WPI_TalonSRX(18);
 
 

rightTeleMotor = new WPI_TalonSRX(19);
 
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run when in simulation

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initPivotMotionMagic(double angle){
		m_angle = angle * TICKSPERDEGREE;

		leftPivotMotor.configFactoryDefault();

		leftPivotMotor.setNeutralMode(NeutralMode.Brake);

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
		leftPivotMotor.setSelectedSensorPosition(0, PID_INDEX, kTimeoutMs);

		leftPivotMotor.configMotionSCurveStrength(PIVOT_SMOOTHING);

		leftPivotMotor.set(TalonFXControlMode.MotionMagic, m_angle);

		rightPivotMotor.setInverted(true);
		rightPivotMotor.follow(leftPivotMotor);
	}


	public void initTeleMotionMagic(double dist){
		leftTeleMotor.configFactoryDefault();
		rightTeleMotor.configFactoryDefault();

		brakemodeOn();

		 leftTeleMotor.configForwardSoftLimitThreshold(SAFETY_TICKS_TOP);
		 rightTeleMotor.configForwardSoftLimitThreshold(SAFETY_TICKS_TOP);
		leftTeleMotor.configReverseSoftLimitThreshold(SAFETY_TICKS_BOTTOM);
		rightTeleMotor.configReverseSoftLimitThreshold(SAFETY_TICKS_BOTTOM);

		leftTeleMotor.configForwardSoftLimitEnable(true);
		rightTeleMotor.configForwardSoftLimitEnable(true);
		leftTeleMotor.configReverseSoftLimitEnable(true);
		rightTeleMotor.configReverseSoftLimitEnable(true);

		m_distance = dist * TICKSPERFOOT;


		leftTeleMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_INDEX, kTimeoutMs);
		rightTeleMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_INDEX, kTimeoutMs);

		leftTeleMotor.configNeutralDeadband(0.001, kTimeoutMs);	
		rightTeleMotor.configNeutralDeadband(0.001, kTimeoutMs);	

		leftTeleMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		leftTeleMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
		rightTeleMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		rightTeleMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* Set the peak and nominal outputs */
		leftTeleMotor.configNominalOutputForward(0.15, kTimeoutMs);
		leftTeleMotor.configNominalOutputReverse(-0.15, kTimeoutMs);
		leftTeleMotor.configPeakOutputForward(1, kTimeoutMs);
		leftTeleMotor.configPeakOutputReverse(-1, kTimeoutMs);

		rightTeleMotor.configNominalOutputForward(0, kTimeoutMs);
		rightTeleMotor.configNominalOutputReverse(0, kTimeoutMs);
		rightTeleMotor.configPeakOutputForward(1, kTimeoutMs);
		rightTeleMotor.configPeakOutputReverse(-1, kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		leftTeleMotor.selectProfileSlot(PID_SLOT, PID_INDEX);
		leftTeleMotor.config_kF(PID_SLOT, PIVOT_kF, kTimeoutMs);
		leftTeleMotor.config_kP(PID_SLOT, PIVOT_kP, kTimeoutMs);
		leftTeleMotor.config_kI(PID_SLOT, PIVOT_kI, kTimeoutMs);
		leftTeleMotor.config_kD(PID_SLOT, PIVOT_kD, kTimeoutMs);

		rightTeleMotor.selectProfileSlot(PID_SLOT, PID_INDEX);
		rightTeleMotor.config_kF(PID_SLOT, PIVOT_kF, kTimeoutMs);
		rightTeleMotor.config_kP(PID_SLOT, PIVOT_kP, kTimeoutMs);
		rightTeleMotor.config_kI(PID_SLOT, PIVOT_kI, kTimeoutMs);
		rightTeleMotor.config_kD(PID_SLOT, PIVOT_kD, kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		leftTeleMotor.configMotionCruiseVelocity(TELE_VEL, kTimeoutMs);
		leftTeleMotor.configMotionAcceleration(TELE_ACC, kTimeoutMs);

		rightTeleMotor.configMotionCruiseVelocity(TELE_VEL, kTimeoutMs);
		rightTeleMotor.configMotionAcceleration(TELE_ACC, kTimeoutMs);

		leftTeleMotor.configMotionSCurveStrength(TELE_SMOOTHING);
		rightTeleMotor.configMotionSCurveStrength(TELE_SMOOTHING);

		leftTeleMotor.set(ControlMode.MotionMagic, m_distance);

		rightTeleMotor.setInverted(true);

		rightTeleMotor.set(ControlMode.MotionMagic, m_distance);

	}

	public double getLeftPivotEncoder(){
		return leftPivotMotor.getSelectedSensorPosition();
	}

	public double getRightPivotEncoder(){
		return rightPivotMotor.getSelectedSensorPosition();
	}

	public double getLeftTeleEncoder(){
		return leftTeleMotor.getSelectedSensorPosition();
	}

	public double getRightTeleEncoder(){
		return rightTeleMotor.getSelectedSensorPosition();
	}

	public void resetPivotEncoders(){
		rightPivotMotor.setSelectedSensorPosition(0);
		leftPivotMotor.setSelectedSensorPosition(0);
	}

	public void resetTeleEncoders(){
		rightTeleMotor.setSelectedSensorPosition(0);
		leftTeleMotor.setSelectedSensorPosition(0);
	}


	public boolean isPivotAtDestination(){
		return (Math.abs(m_angle) - Math.abs(getLeftPivotEncoder()) < THRESHOLD || Math.abs(m_angle) - Math.abs(getRightPivotEncoder()) < THRESHOLD);
	}

	public boolean isTeleAtDestination(){
		return (Math.abs(getLeftTeleEncoder() - m_distance) < THRESHOLD || Math.abs(getLeftTeleEncoder() - m_distance) < THRESHOLD);
	}

	public void runTele(double l, double r){
		leftTeleMotor.set(l);
		rightTeleMotor.set(r);
	}

	public void runPivot(double l, double r){
		leftPivotMotor.set(l);
		rightPivotMotor.set(r);
	}

	public void stopPivot(){
		rightPivotMotor.set(0);
		leftPivotMotor.set(0);
	}

	public void stopTele(){
		rightTeleMotor.set(0);
		leftTeleMotor.set(0);
	}

	public boolean pastLimitSwitch(){
		return leftTeleMotor.getSensorCollection().isRevLimitSwitchClosed() && rightTeleMotor.getSensorCollection().isRevLimitSwitchClosed();
	}

	public boolean pastLimitSwitchLeftTele(){
		return leftTeleMotor.getSensorCollection().isRevLimitSwitchClosed();
	}

	public boolean pastLimitSwitchRightTele(){
		return rightTeleMotor.getSensorCollection().isRevLimitSwitchClosed();
	}

	public void resetPosTop() {
    	leftTeleMotor.setSelectedSensorPosition(TICKS_TOP, 0, kTimeoutMs);
		rightTeleMotor.setSelectedSensorPosition(TICKS_TOP, 0, kTimeoutMs);
    }

	public void resetPosBottom() {
    	leftTeleMotor.setSelectedSensorPosition(TICKS_BOTTOM, 0, kTimeoutMs);
		rightTeleMotor.setSelectedSensorPosition(TICKS_BOTTOM, 0, kTimeoutMs);
    }

	public void overrideSoftLimit(boolean change) {
		leftTeleMotor.overrideSoftLimitsEnable(change);
		rightTeleMotor.overrideSoftLimitsEnable(change);
	}

	public void brakemodeOn() {
		leftTeleMotor.setNeutralMode(NeutralMode.Brake);
		rightTeleMotor.setNeutralMode(NeutralMode.Brake);
	}
}
