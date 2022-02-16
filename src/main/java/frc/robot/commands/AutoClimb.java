package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climber;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drivetrain;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutoClimb extends SequentialCommandGroup {

    Drivetrain m_drivetrain;
    Climber m_climber;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutoClimb(Drivetrain drivetrain, Climber climber) {

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_drivetrain = drivetrain;
        addCommands(
            new DriveXFeet(4, m_drivetrain),//move half foot past bar
            new PivotClimber(-30, m_climber),//move pivot arm forward
            new TelescopeClimber(.5, m_climber),//extend tele arm
            new DriveXFeet(-1, m_drivetrain),//drive back to bar
            climbProcess(),//onto high bar
            climbProcess()//onto transversal
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
            new TelescopeClimber(1, m_climber), //retract tele arm
            new PivotClimber(0, m_climber),     //get pivot arm above bar
            new TelescopeClimber(1.5, m_climber),//extend tele arm. pivot is now on bar
            new PivotClimber(45, m_climber),    //rotates robot towards next bar
            new TelescopeClimber(3, m_climber), //extends tele arm to next bar
            new PivotClimber(30, m_climber),    //angles robot up to help grab next bar
            new TelescopeClimber(1.5, m_climber),//retract and pulls robot onto next bar
            new PivotClimber(-30, m_climber)    //resets pivot arm
            //------------------------------------
            //on wednesday, program button to stop autoclimb in case of emergency
        );
    }
}