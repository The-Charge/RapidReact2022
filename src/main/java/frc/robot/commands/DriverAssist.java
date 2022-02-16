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
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.opencv.ml.Ml;

import frc.robot.RobotContainer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drivetrain;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriverAssist extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final Drivetrain m_drivetrain;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private boolean isRedTeam;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public DriverAssist(Drivetrain subsystem) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_drivetrain = subsystem;
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

        if(isRedTeam){
            chase(distance_red, angle_red);
        }
        else if(!isRedTeam){
            chase(distance_blue, angle_blue);
        }
        //https://github.com/The-Charge/SulfuricAcid2021/blob/master/src/main/java/frc/robot/commands/RunTurretVision.java
    }
    private void chase(double distance, double angle){
        double rightSpeed = -RobotContainer.getInstance().getrightJoystick().getY();
        double leftSpeed = -RobotContainer.getInstance().getleftJoystick().getY();
        double speed = (leftSpeed + rightSpeed)/2;
        if(Math.abs(angle) < 1) m_drivetrain.run(speed, speed);
        else if(distance == -1) m_drivetrain.run(speed/1.5, speed/1.5);
        else {
            
            double lefty =(speed + (speed*angle/(50 - 20*speed)));
            double righty = (speed - (speed*angle/(50 - 20*speed)));
            if(Math.abs(lefty) > Math.abs(righty) && Math.abs(lefty) > 1) m_drivetrain.run(lefty/ Math.abs(lefty), righty/ Math.abs(lefty));
            else if(Math.abs(righty) > 1) m_drivetrain.run(lefty/ Math.abs(righty), righty/ Math.abs(righty));
            else m_drivetrain.run(lefty, righty); 
            SmartDashboard.putString("LeftCorrection", " " + lefty);
            SmartDashboard.putString("RightCorrection", " " + righty);
        
        }

        
    }
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
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

