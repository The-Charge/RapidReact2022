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
private final Joystick buttonBox = new Joystick(2);
private final Joystick rightJoystick = new Joystick(1);
private final Joystick leftJoystick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public final Cooling m_cooling = new Cooling(m_drivetrain);


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
    //m_pivot.setDefaultCommand(new PivotVertical( m_pivot ) );
    m_drivetrain.setDefaultCommand(new TankDrive( m_drivetrain ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    //m_cooling.setDefaultCommand(new Cool(m_cooling));

        // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    m_chooser.addOption("T1_2Ball", new Auton_T1_2Ball(m_drivetrain, m_arm));
    m_chooser.addOption("T2_Delay", new Auton_T2_Delay(m_drivetrain, m_arm));
    m_chooser.addOption("T2_1Ball", new Auton_T2_1Ball(m_drivetrain, m_arm));
    m_chooser.setDefaultOption("Autonomous Command", new Auton_T2_Delay(m_drivetrain, m_arm));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        SmartDashboard.putData("AutoSelect", m_chooser);

        SmartDashboard.putData("currentlySelectedMode", new Auton_T2_Delay(m_drivetrain, m_arm));
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

        final JoystickButton climbBtn = new JoystickButton(buttonBox, 7);        
        climbBtn.whenPressed(new AutoClimb(m_drivetrain, m_telescope, m_pivot) ,true);
    SmartDashboard.putData("pivotArmBackwardBtn",new AutoClimb(m_drivetrain, m_telescope, m_pivot));
    
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton lowerBtn = new JoystickButton(buttonBox, 11);        
lowerBtn.whenPressed(new LowerArm( m_arm ) ,true);
    SmartDashboard.putData("lowerBtn",new LowerArm( m_arm ) );

final JoystickButton liftBtn = new JoystickButton(buttonBox, 9);        
liftBtn.whenPressed(new LiftArm( m_arm ) ,true);
    SmartDashboard.putData("liftBtn",new LiftArm( m_arm ) );

// final JoystickButton pivotArmForwardBtn = new JoystickButton(buttonBox, 6);        
// pivotArmForwardBtn.whileHeld(new ManualPivot(0.01, m_pivot) ,true);
//     SmartDashboard.putData("pivotArmForwardBtn",new ManualPivot(0, m_pivot) );
final JoystickButton pivotArmForwardBtn = new JoystickButton(buttonBox, 6);        
pivotArmForwardBtn.whileHeld(new PivotClimber(20, m_pivot) ,true);
    SmartDashboard.putData("pivotArmForwardBtn",new ManualPivot(0, m_pivot) );

final JoystickButton teleArmUpBtn = new JoystickButton(buttonBox, 5);        
teleArmUpBtn.whileHeld(new TelescopeClimber(0.8, m_telescope) ,true);
    SmartDashboard.putData("teleArmUpBtn",new TelescopeClimber(0, m_telescope) );

// final JoystickButton pivotArmBackwardBtn = new JoystickButton(buttonBox, 4);        
// pivotArmBackwardBtn.whileHeld(new ManualPivot(-0.01, m_pivot) ,true);
//     SmartDashboard.putData("pivotArmBackwardBtn",new ManualPivot(0, m_pivot) );

SmartDashboard.putData("resetPivots", new InstantCommand(() -> m_pivot.zeroSensors()));
SmartDashboard.putData("resetDrivetrain", new InstantCommand(() -> m_drivetrain.resetEncoders()));

    final JoystickButton pivotArmBackwardBtn = new JoystickButton(buttonBox, 4);        
pivotArmBackwardBtn.whileHeld(new PivotClimber(-20, m_pivot) ,true);
    SmartDashboard.putData("pivotArmBackwardBtn",new ManualPivot(0, m_pivot) );

final JoystickButton teleArmDownBtn = new JoystickButton(buttonBox, 3);        
teleArmDownBtn.whileHeld(new TelescopeClimber(-0.8, m_telescope) ,true);
    SmartDashboard.putData("teleArmDownBtn",new TelescopeClimber(0, m_telescope) );

final JoystickButton intakeBtn = new JoystickButton(buttonBox, 2);        
intakeBtn.whileHeld(new IntakeCargo(0.4, m_arm) ,true);
    SmartDashboard.putData("intakeBtn",new IntakeCargo(0, m_arm) );

final JoystickButton deliverBtn = new JoystickButton(buttonBox, 1);        
deliverBtn.whileHeld(new DeliverCargo(1.0, m_arm) ,true);
    SmartDashboard.putData("deliverBtn",new DeliverCargo(0, m_arm) );

final JoystickButton halfSpeedBtn = new JoystickButton(rightJoystick, 6);        
halfSpeedBtn.toggleWhenPressed(new HalfSpeed( m_drivetrain ) ,true);
    SmartDashboard.putData("halfSpeedBtn",new HalfSpeed( m_drivetrain ) );

final JoystickButton highGearBtn = new JoystickButton(rightJoystick, 5);        
highGearBtn.whenPressed(new ShiftHigh( m_shifters ) ,true);
    SmartDashboard.putData("highGearBtn",new ShiftHigh( m_shifters ) );

final JoystickButton quarterSpeedBtn = new JoystickButton(rightJoystick, 4);        
quarterSpeedBtn.toggleWhenPressed(new QuarterSpeed( m_drivetrain ) ,true);
    SmartDashboard.putData("quarterSpeedBtn",new QuarterSpeed( m_drivetrain ) );

final JoystickButton lowGearBtn = new JoystickButton(rightJoystick, 3);        
lowGearBtn.whenPressed(new ShiftLow( m_shifters ) ,true);
    SmartDashboard.putData("lowGearBtn",new ShiftLow( m_shifters ) );

final JoystickButton invertDriveBtn = new JoystickButton(rightJoystick, 1);        
invertDriveBtn.whileHeld(new InvertDrive( m_drivetrain ) ,true);
    SmartDashboard.putData("invertDriveBtn",new InvertDrive( m_drivetrain ) );

final JoystickButton assistBtn = new JoystickButton(leftJoystick, 1);        
assistBtn.whileHeld(new DriverAssist( m_drivetrain ) ,true);
    SmartDashboard.putData("assistBtn",new DriverAssist( m_drivetrain ) );

final JoystickButton alignBtn = new JoystickButton(leftJoystick, 8);        
alignBtn.whileHeld(new ColorAlign( m_drivetrain ) ,true);
    SmartDashboard.putData("alignBtn",new ColorAlign( m_drivetrain ) );

final JoystickButton toggleLockBtn = new JoystickButton(leftJoystick, 5);        
toggleLockBtn.toggleWhenPressed(new ToggleLockStraight( m_drivetrain ) ,true);
    SmartDashboard.putData("toggleLockBtn",new ToggleLockStraight( m_drivetrain ) );

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getleftJoystick() {
        return leftJoystick;
    }

public Joystick getrightJoystick() {
        return rightJoystick;
    }

public Joystick getbuttonBox() {
        return buttonBox;
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
