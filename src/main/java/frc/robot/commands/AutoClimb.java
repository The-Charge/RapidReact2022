package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Arm;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Telescope;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutoClimb extends SequentialCommandGroup {

    Drivetrain m_drivetrain;
    Telescope m_telescope;
    Pivot m_pivot;
    Arm m_arm;

    //constants here
    private static final double EXTEND_TELEARM = 2.5;
    private static final double RETRACT_TELEARM = 1;
    private static final double EXTEND_TELEARM_OVERBAR = 1.5;
    private static final double EXTEND_TELEARM_NEXTBAR = 1.6;

    private static final double STRAIGHT_PIVOTARM = 0;
    private static final double FRONT_PIVOTARM_30 = -30;
    private static final double BACK_PIVOTARM_30 = 30;
    private static final double BACK_PIVOTARM_45 = 45;

    private static final double FRONT_DRIVE_DISTANCE_4 = 4;
    private static final double BACK_DRIVE_DISTANCE_1 = -1;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutoClimb(Drivetrain drivetrain, Arm arm, Telescope telescope, Pivot pivot) {
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_drivetrain = drivetrain;
        m_telescope = telescope;
        m_pivot = pivot;
        m_arm = arm;
        addCommands(
            new InstantCommand(() -> m_drivetrain.initMotionMagic()),
            new ParallelCommandGroup(new DriveXFeet(4.0, m_drivetrain), new LowerArm(m_arm)),
            //new ManualTele(0.5, false, m_telescope),
            new DriveXFeet(2, m_drivetrain)
           // new PivotSetpoint(2, m_pivot)
            // new InstantCommand(() -> SmartDashboard.putBoolean("DONE", true)),
            // new PivotSetpoint(45, m_pivot)
            // new DriveXFeet(FRONT_DRIVE_DISTANCE_4, m_drivetrain),//move half foot past bar
            // new PivotSetpoint(FRONT_PIVOTARM_30, m_pivot),//move pivot arm forward
            // new TelescopeClimber(EXTEND_TELEARM, m_telescope),//extend tele arm
            // new DriveXFeet(BACK_DRIVE_DISTANCE_1, m_drivetrain),//drive back to bar
            // climbProcess(),//onto high bar
            // climbProcess()//onto transversal
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }

    public SequentialCommandGroup climbProcess(){
        return new SequentialCommandGroup(
            //-------------------------------------
            new TelescopeClimber(RETRACT_TELEARM, m_telescope), //retract tele arm
            new PivotSetpoint(STRAIGHT_PIVOTARM, m_pivot),     //get pivot arm above bar
            new TelescopeClimber(EXTEND_TELEARM_OVERBAR, m_telescope),//extend tele arm. pivot is now on bar
            new PivotSetpoint(BACK_PIVOTARM_45, m_pivot),    //rotates robot towards next bar
            new TelescopeClimber(EXTEND_TELEARM_NEXTBAR, m_telescope), //extends tele arm to next bar
            new PivotSetpoint(BACK_PIVOTARM_30, m_pivot),    //angles robot up to help grab next bar
            new TelescopeClimber(EXTEND_TELEARM_OVERBAR, m_telescope),//retract and pulls robot onto next bar
            new PivotSetpoint(FRONT_PIVOTARM_30, m_pivot)    //resets pivot arm
            //------------------------------------
            //on wednesday, program button to stop autoclimb in case of emergency
        );
    }
}