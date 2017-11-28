package org.usfirst.frc6329.UglyBuckling;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static RobotDrive driveSystemRobotDrive;
	public static SpeedController driveSystemleftfront;
    public static SpeedController driveSystemleftrear;
    public static SpeedController driveSystemrightfront;
    public static SpeedController driveSystemrightrear;
    public static SpeedController gearPickupGearPickupESC;
    public static SpeedController gearCraneGearCraneESC;
    public static SpeedController winchWinchESC;
    public static Solenoid shifterShifterSol;

    public static class PDP {
    
    	// Drive
        public static final int PDP_RIGHT_MOTOR_FRONT = 13;
        public static final int PDP_RIGHT_MOTOR_BACK = 12;
        public static final int PDP_LEFT_MOTOR_FRONT = 15;
        public static final int PDP_LEFT_MOTOR_BACK = 14;
    	
        // Climber
        public static final int PDP_CLIMBER_MOTOR = 0;
        
        // Gear Crane
        public static final int PDP_GEARCRANE_MOTOR = 2;
        
        // Gear Pickup
        public static final int PDP_GEARPICKUP_MOTOR = 1;
        
    }
    
    public static void init() {
        driveSystemleftfront = new Spark(0);
        LiveWindow.addActuator("Drive System", "left front ", (Spark) driveSystemleftfront);
        
        driveSystemleftrear = new Spark(1);
        LiveWindow.addActuator("Drive System", "left rear", (Spark) driveSystemleftrear);
        
        driveSystemrightfront = new Spark(2);
        LiveWindow.addActuator("Drive System", "right front", (Spark) driveSystemrightfront);
        
        driveSystemrightrear = new Spark(3);
        LiveWindow.addActuator("Drive System", "right rear", (Spark) driveSystemrightrear);
        
        driveSystemRobotDrive = new RobotDrive(driveSystemleftfront, driveSystemleftrear,
              driveSystemrightfront, driveSystemrightrear);
        
        driveSystemRobotDrive.setSafetyEnabled(true);
        driveSystemRobotDrive.setExpiration(0.1);
        driveSystemRobotDrive.setSensitivity(0.5);
        driveSystemRobotDrive.setMaxOutput(1.0);

        gearPickupGearPickupESC = new VictorSP(6);
        LiveWindow.addActuator("Gear Pickup", "gearPickupESC", (VictorSP) gearPickupGearPickupESC);
        
        winchWinchESC = new VictorSP(7);
        LiveWindow.addActuator("Winch", "WinchESC", (VictorSP) winchWinchESC);
        
        gearCraneGearCraneESC = new VictorSP(8);
        LiveWindow.addActuator("Gear Crane", "gearCraneESC", (VictorSP) gearCraneGearCraneESC);
        
        shifterShifterSol = new Solenoid(0, 0);
        LiveWindow.addActuator("Shifter", "ShifterSol", shifterShifterSol);
    
    }
}
