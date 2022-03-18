package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;



public class AutonCommandFactory extends CommandBase {


    public AutonCommandFactory() {


    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
        return false;
    }


/*
    public Command shootFor(double seconds, Stopper stopper, Indexer indexer, Shooter shooter, Turret turret) {
        ParallelCommandGroup runShooter = new ParallelCommandGroup(new OpenStopper(stopper), new Index(indexer, 0.5, true));
        ParallelCommandGroup shoot = new ParallelCommandGroup(new RunTurretVision(turret, 0.8), new Shoot(0.65, shooter), new WaitCommand(2).andThen(runShooter));
        return new ParallelRaceGroup(new WaitCommand(seconds), shoot);
    }
    */

    public Command lowerAndIntake(double time, double pow, Arm arm) {
        return
            new ParallelDeadlineGroup(new WaitCommand(time),
            new SequentialCommandGroup(
                new LowerArm(arm),
                new IntakeCargo(pow, arm),
                new LiftArm(arm)
                )
            );
    }

    public Command deliver(double time, double pow, Arm arm) {
        return new ParallelRaceGroup(
            new WaitCommand(time),
             new DeliverCargo(pow, arm));
    }

}