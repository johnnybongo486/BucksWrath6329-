package org.usfirst.frc6329.UglyBuckling;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import org.usfirst.frc6329.UglyBuckling.commands.StartCompressor;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.Blue1;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.Blue2;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.Blue3;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.GyroTest;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.Red1;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.Red2;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.Red3;
import org.usfirst.frc6329.UglyBuckling.commands.autonomous.VisionTest;
import org.usfirst.frc6329.UglyBuckling.subsystems.*;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();
    
    public static OI oi;

    public static Robot robot;
    public static DriveSystem driveSystem;
    public static Winch winch;
    public static Shifter shifter;
    public static AirCompressor airCompressor;
    public static GearPickup gearPickup;
    public static GearCrane gearCrane;
    public static NetworkTable table;
    
    public static ADXRS450_Gyro gyro;
    public static PDP pdp;
    public static Compressor c;
	public static UsbCamera RearCamera;
	public static HttpCamera FrontCamera;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	robot = this;
    	 
    	pdp = new PDP();
    	gyro = new ADXRS450_Gyro();
        c = new Compressor(0);
    	 
    	Constants.isUglyBuckling = pdp.isUglyBuckling();
    	
    	RobotMap.init();
    	
    	//String cameraURL = "http://buckswrath.local:1185/?action=stream";
		// HttpCamera FrontCamera = new HttpCamera("Bob", cameraURL);
    	UsbCamera FrontCamera = CameraServer.getInstance().startAutomaticCapture("Bob", 0);
		FrontCamera.setResolution(160, 120);
    	FrontCamera.setFPS(15);
    	
    	UsbCamera RearCamera = CameraServer.getInstance().startAutomaticCapture("Robert", 1);
    	RearCamera.setResolution(160, 120);
    	RearCamera.setFPS(15);
    	
    	driveSystem = new DriveSystem();
        winch = new Winch();
        airCompressor = new AirCompressor();
        shifter = new Shifter();
        gearPickup = new GearPickup();
        gearCrane = new GearCrane();

        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
  
        oi = new OI();

    }

    public void disabledInit(){
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        pdp.updateDashboard();
        driveSystem.updateDashboard();
        runCompressor();
    }

    public void autonomousInit() {
    	 String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
    	 switch(autoSelected) { 
    	 	case "Default Auto": default : autonomousCommand = new Red2();
    	 	break; 
    	 	case "Red 2" : autonomousCommand = new Red2();
    	 	break;
    	 	case "Red 1" : autonomousCommand = new Red1();
    	 	break;
    	 	case "Red 3" : autonomousCommand = new Red3();
    	 	break;
    	 	case "Blue 1" : autonomousCommand = new Blue1();
    	 	break;
    	 	case "Blue 2" : autonomousCommand = new Blue2();
    	 	break;
    	 	case "Blue 3" : autonomousCommand = new Blue3();
    	 	break;
    	 	case "Gyro Test" : autonomousCommand = new GyroTest();
    	 	break;
    	 	case "Vision Test" : autonomousCommand = new VisionTest();
    	 	break;
    	   }
    	 
        if (autonomousCommand != null) autonomousCommand.start();
    }
    	 
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        pdp.updateDashboard();
        driveSystem.updateDashboard();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        pdp.updateDashboard();
        driveSystem.updateDashboard();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        pdp.updateDashboard();
        driveSystem.updateDashboard();
    }
    
    public void runCompressor() {
        boolean pressureSwitch = c.getPressureSwitchValue();
        if (pressureSwitch == true) {
        	new StartCompressor();
        }
    }
}
