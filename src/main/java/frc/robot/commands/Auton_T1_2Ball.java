// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands;

import java.io.IOException;
import java.nio.file.Path;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Arm;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Drivetrain;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Auton_T1_2Ball extends SequentialCommandGroup {

    RamseteCommand ramsete1;
    RamseteCommand ramsete2;
    RamseteCommand ramsete3;

    AutonCommandFactory factory;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    private final Drivetrain m_drivetrain;
    private final Arm m_arm;
    public Auton_T1_2Ball(Drivetrain drivetrain, Arm arm){


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    m_drivetrain = drivetrain;
    m_arm = arm;
    factory = new AutonCommandFactory();
    addCommands(
        new ParallelCommandGroup(
        getAutonomous1(),
        new SequentialCommandGroup(
            factory.deliver(0.5, AutoConstants.deliverSpeed, arm),
            new WaitCommand(2.5),
            factory.lowerAndIntake(6.0, AutoConstants.intakeSpeed, arm),
            new LiftArm(arm),
            new WaitCommand(3.0),
            factory.deliver(2.0, AutoConstants.deliverSpeed, arm)
        )
        )
        //getAutonomous2()
        //getAutonomous3()
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }


    public Command getAutonomous1() {
        try {

            String trajectoryJSON = "output/T1_BackupFast.wpilib.json";
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            Trajectory exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

            trajectoryJSON = "output/T1_Grab2Fast.wpilib.json";
            Path trajectoryPath1 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            Trajectory exampleTrajectory1 = TrajectoryUtil.fromPathweaverJson(trajectoryPath1);

            Trajectory traj = exampleTrajectory.concatenate(exampleTrajectory1);

            SmartDashboard.putNumber("INIT POSE X", exampleTrajectory.getInitialPose().getX());
            SmartDashboard.putNumber("INIT POSE Y", exampleTrajectory.getInitialPose().getY());

            ramsete1 = new RamseteCommand(traj, m_drivetrain::getPose,
                    new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
                    new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
                            DriveConstants.kaVoltSecondsSquaredPerMeter),
                    DriveConstants.kDriveKinematics, m_drivetrain::getWheelSpeeds,
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    // RamseteCommand passes volts to the callback
                    m_drivetrain::tankDriveVolts, m_drivetrain);

            // Run path following command, then stop at the end.
            return new SequentialCommandGroup(
                // new InstantCommand(() -> m_drivetrain.zeroHeading()),
                // new InstantCommand(() -> m_drivetrain.resetEncoders()),
                new InstantCommand(() -> m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose())),
                 ramsete1.andThen(() -> m_drivetrain.tankDriveVolts(0, 0))
                 );
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());
            System.out.println("Inside Catch");
        }
        return null;
    }

    public Command getAutonomous2() {
        try {

            String trajectoryJSON = "output/T1_Grab2.wpilib.json";
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            Trajectory exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

            SmartDashboard.putNumber("INIT POSE X", exampleTrajectory.getInitialPose().getX());
            SmartDashboard.putNumber("INIT POSE Y", exampleTrajectory.getInitialPose().getY());

            ramsete2 = new RamseteCommand(exampleTrajectory, m_drivetrain::getPose,
                    new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
                    new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
                            DriveConstants.kaVoltSecondsSquaredPerMeter),
                    DriveConstants.kDriveKinematics, m_drivetrain::getWheelSpeeds,
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    // RamseteCommand passes volts to the callback
                    m_drivetrain::tankDriveVolts, m_drivetrain);

            // Run path following command, then stop at the end.
            return new SequentialCommandGroup(
                new InstantCommand(() -> m_drivetrain.zeroHeading()),
                new InstantCommand(() -> m_drivetrain.resetEncoders()),
                new InstantCommand(() -> m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose())),
                 ramsete2.andThen(() -> m_drivetrain.tankDriveVolts(0, 0))
                 );
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());
            System.out.println("Inside Catch");
        }
        return null;
    }


    public Command getAutonomous3() {
        try {

            String trajectoryJSON = "output/T1_Grab20.wpilib.json";
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            Trajectory exampleTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

            SmartDashboard.putNumber("INIT POSE X", exampleTrajectory.getInitialPose().getX());
            SmartDashboard.putNumber("INIT POSE Y", exampleTrajectory.getInitialPose().getY());

            ramsete3 = new RamseteCommand(exampleTrajectory, m_drivetrain::getPose,
                    new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
                    new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
                            DriveConstants.kaVoltSecondsSquaredPerMeter),
                    DriveConstants.kDriveKinematics, m_drivetrain::getWheelSpeeds,
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    // RamseteCommand passes volts to the callback
                    m_drivetrain::tankDriveVolts, m_drivetrain);

            // Run path following command, then stop at the end.
            return new SequentialCommandGroup(
                new InstantCommand(() -> m_drivetrain.zeroHeading()),
                new InstantCommand(() -> m_drivetrain.resetEncoders()),
                new InstantCommand(() -> m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose())),
                 ramsete3.andThen(() -> m_drivetrain.tankDriveVolts(0, 0))
                 );
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());
            System.out.println("Inside Catch");
        }
        return null;
    }


}
