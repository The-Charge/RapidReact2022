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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Drivetrain extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX leftFrontMotor;
private WPI_TalonSRX leftMidMotor;
private WPI_TalonSRX leftBackMotor;
private WPI_TalonSRX rightFrontMotor;
private WPI_TalonSRX rightMidMotor;
private WPI_TalonSRX rightBackMotor;



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private static boolean isReversed = false;
    /**
    *
    */
    public Drivetrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
leftFrontMotor = new WPI_TalonSRX(0);
 
 

leftMidMotor = new WPI_TalonSRX(1);
 
 

leftBackMotor = new WPI_TalonSRX(2);
 
 

rightFrontMotor = new WPI_TalonSRX(3);
 
 

rightMidMotor = new WPI_TalonSRX(4);
 
 

rightBackMotor = new WPI_TalonSRX(5);
 


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

    public void initializeMotors() {
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
            l *= -1; //inverse left motor speed
            r *= -1; //inverse right motor speed
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

    public void resetEncoders() {
        leftFrontMotor.setSelectedSensorPosition(0);
        rightFrontMotor.setSelectedSensorPosition(0);
    }
    public void setReversed(boolean rev)
    {
        isReversed = rev;
        //set isReversed to bool whether is reversed or not
    }
    public boolean getReversed()
    {
        return isReversed;
        //return isReversed
    }
}
