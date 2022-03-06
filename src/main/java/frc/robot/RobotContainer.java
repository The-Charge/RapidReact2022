// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {

    private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Pivot m_pivot = new Pivot();
    public final Telescope m_telescope = new Telescope();
    public final Arm m_arm = new Arm();
    public final Shifters m_shifters = new Shifters();
    public final Drivetrain m_drivetrain = new Drivetrain();

// Joysticks
private final Joystick rightJoystick = new Joystick(1);
private final Joystick leftJoystick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("TankDrive", new TankDrive( m_drivetrain ));
    SmartDashboard.putData("HalfSpeed", new HalfSpeed( m_drivetrain ));
    SmartDashboard.putData("QuarterSpeed", new QuarterSpeed( m_drivetrain ));
    SmartDashboard.putData("ToggleLockStraight", new ToggleLockStraight( m_drivetrain ));
    SmartDashboard.putData("InvertDrive", new InvertDrive( m_drivetrain ));
    SmartDashboard.putData("ShiftHigh", new ShiftHigh( m_shifters ));
    SmartDashboard.putData("ShiftLow", new ShiftLow( m_shifters ));
    SmartDashboard.putData("LiftArm", new LiftArm( m_arm ));
    SmartDashboard.putData("LowerArm", new LowerArm( m_arm ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
        // Configure the button bindings
        configureButtonBindings();

        // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_drivetrain.setDefaultCommand(new TankDrive( m_drivetrain ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

        // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("Autonomous Command", new Auton_T1_2Ball(m_drivetrain));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        SmartDashboard.putData("Auto Mode", m_chooser);
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton lowerBtn = new JoystickButton(rightJoystick, 8);        
lowerBtn.whenPressed(new LowerArm( m_arm ) ,true);
    SmartDashboard.putData("lowerBtn",new LowerArm( m_arm ) );

final JoystickButton liftBtn = new JoystickButton(rightJoystick, 7);        
liftBtn.whenPressed(new LiftArm( m_arm ) ,true);
    SmartDashboard.putData("liftBtn",new LiftArm( m_arm ) );

final JoystickButton intakeBtn = new JoystickButton(rightJoystick, 6);        
intakeBtn.whenPressed(new IntakeCargo(0.3, m_arm) ,true);
    SmartDashboard.putData("intakeBtn",new IntakeCargo(0.3, m_arm) );

final JoystickButton deliverBtn = new JoystickButton(rightJoystick, 5);        
deliverBtn.whileHeld(new DeliverCargo(0.3, m_arm) ,true);
    SmartDashboard.putData("deliverBtn",new DeliverCargo(0.3, m_arm) );

final JoystickButton driveXFeetBtn = new JoystickButton(rightJoystick, 4);        
driveXFeetBtn.whenPressed(new DriveXFeet(0, m_drivetrain) ,true);
    SmartDashboard.putData("driveXFeetBtn",new DriveXFeet(0, m_drivetrain) );

final JoystickButton shiftLowBtn = new JoystickButton(rightJoystick, 3);        
shiftLowBtn.whenPressed(new ShiftLow( m_shifters ) ,true);
    SmartDashboard.putData("shiftLowBtn",new ShiftLow( m_shifters ) );

final JoystickButton shiftHighBtn = new JoystickButton(rightJoystick, 2);        
shiftHighBtn.whenPressed(new ShiftHigh( m_shifters ) ,true);
    SmartDashboard.putData("shiftHighBtn",new ShiftHigh( m_shifters ) );

final JoystickButton quarterSpeedBtn = new JoystickButton(rightJoystick, 1);        
quarterSpeedBtn.toggleWhenPressed(new QuarterSpeed( m_drivetrain ) ,true);
    SmartDashboard.putData("quarterSpeedBtn",new QuarterSpeed( m_drivetrain ) );

final JoystickButton pivotBtn = new JoystickButton(leftJoystick, 7);        
pivotBtn.whenPressed(new PivotClimber(90, m_pivot) ,true);
    SmartDashboard.putData("pivotBtn",new PivotClimber(90, m_pivot) );

final JoystickButton resetTeleBtn = new JoystickButton(leftJoystick, 6);        
resetTeleBtn.whenPressed(new ResetTelescope( m_telescope ) ,true);
    SmartDashboard.putData("resetTeleBtn",new ResetTelescope( m_telescope ) );

final JoystickButton retractTeleBtn = new JoystickButton(leftJoystick, 5);        
retractTeleBtn.whenPressed(new TelescopeClimber(0.0, m_telescope) ,true);
    SmartDashboard.putData("retractTeleBtn",new TelescopeClimber(0.0, m_telescope) );

final JoystickButton extendTeleBtn = new JoystickButton(leftJoystick, 4);        
extendTeleBtn.whenPressed(new TelescopeClimber(3, m_telescope) ,true);
    SmartDashboard.putData("extendTeleBtn",new TelescopeClimber(3, m_telescope) );

final JoystickButton toggleLockBtn = new JoystickButton(leftJoystick, 3);        
toggleLockBtn.whileHeld(new ToggleLockStraight( m_drivetrain ) ,true);
    SmartDashboard.putData("toggleLockBtn",new ToggleLockStraight( m_drivetrain ) );

final JoystickButton invertBtn = new JoystickButton(leftJoystick, 2);        
invertBtn.toggleWhenPressed(new InvertDrive( m_drivetrain ) ,true);
    SmartDashboard.putData("invertBtn",new InvertDrive( m_drivetrain ) );

final JoystickButton halfSpeedBtn = new JoystickButton(leftJoystick, 1);        
halfSpeedBtn.toggleWhenPressed(new HalfSpeed( m_drivetrain ) ,true);
    SmartDashboard.putData("halfSpeedBtn",new HalfSpeed( m_drivetrain ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getleftJoystick() {
        return leftJoystick;
    }

public Joystick getrightJoystick() {
        return rightJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return m_chooser.getSelected();
    }

}
