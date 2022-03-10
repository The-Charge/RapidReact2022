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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX armMotor;
private DoubleSolenoid liftPiston;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //PID VALUES
    private final static double SPEED_P_CONSTANT = 0.05; //.3
    private final static double SPEED_I_CONSTANT = 0.0003; //.002
    private final static double SPEED_D_CONSTANT = 0.0;
    private final static double SPEED_F_CONSTANT = 0.0;

    public final static int PID_SLOT_SPEED_MODE = 0;

    public static final double MAXSPEED = 11384;
    /**
    *
    */
    public Arm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
armMotor = new WPI_TalonSRX(7);
 
 

liftPiston = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, 0, 1);
 addChild("liftPiston", liftPiston);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    initializeMotor();
    initSpeedMode();
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("Arm Up", !liftPiston.isFwdSolenoidDisabled());
        SmartDashboard.putNumber("Intake Speed", armMotor.getSelectedSensorVelocity());
    }
    public void stop() {
        armMotor.set(0);
        //armMotor.setNeutralMode(NeutralMode.Coast);
    }
    public void initSpeedMode() {
        armMotor.config_kP(PID_SLOT_SPEED_MODE, SPEED_P_CONSTANT);
        armMotor.config_kI(PID_SLOT_SPEED_MODE, SPEED_I_CONSTANT);
        armMotor.config_kD(PID_SLOT_SPEED_MODE, SPEED_D_CONSTANT);
        armMotor.config_kF(PID_SLOT_SPEED_MODE, SPEED_F_CONSTANT);
        armMotor.selectProfileSlot(PID_SLOT_SPEED_MODE, 0);
        armMotor.set(ControlMode.Velocity, 0);
    }
    public void run(double pow) {armMotor.set(pow);}
    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    public void initializeMotor() {
        armMotor.setInverted(true);
        armMotor.setSensorPhase(true);
    }
    public void setBrakeMode() {
        armMotor.setNeutralMode(NeutralMode.Brake);
    }
    public void setCoastMode() {
        armMotor.setNeutralMode(NeutralMode.Coast);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void intakeCargo (double pow) {
        pow *= MAXSPEED;
        armMotor.set(ControlMode.Velocity, pow);
    }
    public void deliverCargo(Double pow) {
        // pow *= MAXSPEED;
        // armMotor.set(ControlMode.Velocity, -pow);
        armMotor.set(ControlMode.PercentOutput, -pow);
    }
    public void liftArm() { //activate solenoids on both sides of the robot, raise arm.
        liftPiston.set(Value.kForward);
    }
    public void lowerArm() { //deactive solenoids on both ends of the robot, lower arm.
        liftPiston.set(Value.kReverse);
    }

}
