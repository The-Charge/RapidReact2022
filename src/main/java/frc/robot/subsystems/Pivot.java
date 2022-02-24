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


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
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
	private static final double TICKSPERDEGREE = 1000;

	private static final int kTimeoutMs = 30;
	private static final int PID_INDEX = 0;
	private static final int PID_SLOT = 0;

	private static final double PIVOT_kP = 0.2;
	private static final double PIVOT_kI = 0;
	private static final double PIVOT_kD = 0.2;
	private static final double PIVOT_kF = 0;
	private static final int PIVOT_SMOOTHING = 4;


	private static final double MAX_TEMP = 35;
    
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

		leftPivotMotor.setInverted(true);
		rightPivotMotor.setInverted(true);
		
		rightPivotMotor.follow(leftPivotMotor);

		leftPivotMotor.set(TalonFXControlMode.MotionMagic, m_angle);
		
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

    public boolean checkTemp() {
        SmartDashboard.putNumber("Temp", leftPivotMotor.getTemperature());
        if (leftPivotMotor.getTemperature() > MAX_TEMP || rightPivotMotor.getTemperature() > MAX_TEMP)
            return true;
        else
            return false;
    }

}

