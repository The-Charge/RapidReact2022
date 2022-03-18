// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotor1Port = 1;
        public static final int kLeftMotor2Port = 2;
        public static final int kLeftMotor3Port = 3;
        public static final int kRightMotor1Port = 7;
        public static final int kRightMotor2Port = 0;
        public static final int kRightMotor3Port = 8;
        public static final boolean kLeftMotorReversed = false;
        public static final boolean kRightMotorReversed = true;
        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;

        public static final double kTrackwidthMeters = 0.583;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
                kTrackwidthMeters);

        public static final int kEncoderCPR = 19000; 
        public static final double kWheelDiameterMeters = 0.152;
        public static final double kEncoderDistancePerPulse =
                // Assumes the encoders are directly mounted on the wheel shafts
                (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

        public static final boolean kGyroReversed = true;
        // TODO test/tune these values
        public static final double ksVolts = .62031;//0.75588; // .501
        public static final double kvVoltSecondsPerMeter = 2.4678; //1.5582; //1.6
        public static final double kaVoltSecondsSquaredPerMeter = .39963;// 0.176;

        // Example value only - as above, this must be tuned for your drive!
        public static final double kPDriveVel = 3.4457; //4
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 1;
    }

    public static final class PivotConstants{
        public static final double tickPerDegree = 90;
    }
    
    public static final class AutoConstants {
        public static final double intakeSpeed = 0.4;
        public static final double deliverSpeed = 1.0;
        public static final double teleSpeed = 0.8;
        public static final double kMaxSpeedMetersPerSecond = 1.5; // 1.0
        public static final double kMaxAccelerationMetersPerSecondSquared = 1.0; // .5

        // Reasonable baseline values for a RAMSETE follower in units of meters and
        // seconds
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;    
    }
}

