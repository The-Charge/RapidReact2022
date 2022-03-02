package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.Cool;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Cooling implements Subsystem {
    /*
     * The cooling subsystem cools down Falcons.
     */
    private Solenoid coolingSolenoid;
    private Drivetrain m_drivetrain;

    public Cooling(Drivetrain drivetrain) {
        coolingSolenoid = new Solenoid(1, PneumaticsModuleType.REVPH, 3);
        m_drivetrain = drivetrain;
        setDefaultCommand(new Cool(this));
        coolingSolenoid.set(false);
    }
    // Put code here to be run every loop

    public void cool() {
        SmartDashboard.putBoolean("Drivetrain Temp", m_drivetrain.checkTemp());
        if (m_drivetrain.checkTemp())
            coolingSolenoid.set(true);
        else
            coolingSolenoid.set(false);
        // Put methods for controlling this subsystem
    }
    // here. Call these from Commands.
}