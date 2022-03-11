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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Telescope;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class TelescopeClimber extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final Telescope m_telescope;
    private double m_distance;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATION
    private final double correctionR = 1.05;

    private final double THRESHOLD = .025;

    private static double setpointL = 0, setpointR = 0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public TelescopeClimber(double distance, Telescope subsystem) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_distance = distance;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_telescope = subsystem;
        addRequirements(m_telescope);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        // if(m_telescope.pastRevLimitSwitchLeftTele()){
        //     m_telescope.resetLeftEncoder();
        //     setpointL = 100;
        // }

        // if(m_telescope.pastRevLimitSwitchRightTele()){
        //     m_telescope.resetRightEncoder();
        //     setpointR = 100;
        // }

        m_telescope.runLeftMotionMagic(setpointL);
        m_telescope.runRightMotionMagic(setpointR);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //m_telescope.stopTele();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
        // if(m_telescope.getLeftTeleEncoder() - m_distance < 0)
        // {
        //  return m_telescope.pastFwdLimitSwitchLeftTele() && m_telescope.pastFwdLimitSwitchRightTele();
        //  }
        //  return m_telescope.pastRevLimitSwitchLeftTele() && m_telescope.pastRevLimitSwitchRightTele();
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
