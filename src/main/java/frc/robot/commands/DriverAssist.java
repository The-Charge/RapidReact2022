// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.



package frc.robot.commands;

import java.util.Date;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.Arm;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drivetrain;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriverAssist extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final Drivetrain m_drivetrain;
        private final Arm m_arm;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private boolean isRedTeam;
    private double lastSeen;

    private  double MIN_SPEED = 0.5;
    private  double SLOW_SPEED = 0.3;
    private  double AREA_SIZE = 100/MIN_SPEED;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public DriverAssist(Drivetrain subsystem, Arm arm) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_drivetrain = subsystem;
        m_arm = arm;
        addRequirements(m_drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
            isRedTeam = true;
        } else if (DriverStation.getAlliance() == DriverStation.Alliance.Blue) {
            isRedTeam = false;
        }
        lastSeen = new Date().getTime();
        
        SmartDashboard.putBoolean("Driver Assist", true);
        m_drivetrain.setControlMode(ControlMode.PercentOutput);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        
        //for object in detections, find the lowest distance that is the correct color, drive at it 
        //read JSON
        NetworkTable table = NetworkTableInstance.getDefault().getTable("ML");
        double distance_red = table.getEntry("distance_red").getDouble(0.0);
        double angle_red = table.getEntry("angle_red").getDouble(0.0);
        double distance_blue = table.getEntry("distance_blue").getDouble(0.0);
        double angle_blue = table.getEntry("angle_blue").getDouble(0.0);
        double area_blue = table.getEntry("area_blue").getDouble(0.0);
        double area_red = table.getEntry("area_red").getDouble(0.0);

        if(isRedTeam){
            chase(distance_red, angle_red, area_red);
        }
        else if(!isRedTeam){
            chase(distance_blue, angle_blue, area_blue);
        }
        //https://github.com/The-Charge/SulfuricAcid2021/blob/master/src/main/java/frc/robot/commands/RunTurretVision.java
    }
    private void chase(double distance, double angle, double area){
        double speed = MIN_SPEED;
        AREA_SIZE = 30/m_drivetrain.getWheelSpeeds().leftMetersPerSecond;

        if(area >= AREA_SIZE){
            m_arm.lowerArm();
            m_arm.intakeCargo(AutoConstants.intakeSpeed);
            speed = SLOW_SPEED;
        }

        SmartDashboard.putNumber("LASTSEEN", (new Date().getTime() - lastSeen));
        SmartDashboard.putNumber("Velocity", m_drivetrain.getWheelSpeeds().leftMetersPerSecond);
        if(area < 1){
            if((new Date().getTime() - lastSeen) > 1000){
                if(RobotContainer.getInstance().getrightJoystick().getX() > 0.2) m_drivetrain.run(0.4, -0.2);
                else if(RobotContainer.getInstance().getrightJoystick().getX() < -0.2) m_drivetrain.run(-0.2, 0.4);
                else m_drivetrain.run(0, 0);
            }
        }
        else if(Math.abs(angle) < 1){ m_drivetrain.run(speed, speed);
            lastSeen = new Date().getTime();
        }
        else {
            /*
             double lefty =(speed + (speed*angle/(70)));
             double righty = (speed - (speed*angle/(70)));
             if(Math.abs(lefty) > Math.abs(righty) && Math.abs(lefty) > 1) m_drivetrain.run(lefty/ Math.abs(lefty), righty/ Math.abs(lefty));
             else if(Math.abs(righty) > 1) m_drivetrain.run(lefty/ Math.abs(righty), righty/ Math.abs(righty));
             else m_drivetrain.run(lefty, righty); 
            */
            lastSeen = new Date().getTime();
             double lefty =(speed + (speed*angle/(70)));
             double righty = (speed - (speed*angle/(70)));
             if(Math.abs(lefty) > Math.abs(righty)) m_drivetrain.run(speed*lefty/ Math.abs(lefty), speed*righty/ Math.abs(lefty));
             else m_drivetrain.run(speed*lefty/ Math.abs(righty), speed*righty/ Math.abs(righty));
         }

  
    }
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("Driver Assist", false);
        m_arm.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED 
    } 
}
