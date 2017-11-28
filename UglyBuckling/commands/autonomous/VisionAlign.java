package org.usfirst.frc6329.UglyBuckling.commands.autonomous;

import org.usfirst.frc6329.UglyBuckling.Robot;
import org.usfirst.frc6329.UglyBuckling.subsystems.DriveSystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAlign extends Command {

	DriveSystem driveSystem;

	private double moveSpeed;
	private double runTime;
	private double turn;
	private double error;
	int threshold;
	
	private NetworkTable table;
	
	public VisionAlign(double moveSpeed, double runTime) {
		requires(Robot.driveSystem);
        this.moveSpeed = moveSpeed;
        this.runTime = runTime;
		driveSystem = Robot.driveSystem;
		threshold = 2;
		table = NetworkTable.getTable("GRIP");
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// This is what happens when the robot is to center directly on the airship
	// using vision
	protected void execute() {
		error = table.getNumber("centerX", 0);
		
		if ((Math.abs(error) > threshold) && (Math.abs(error) < 320)) {
			turn = .003125 * error;
			// Turn in place
			driveSystem.autoDrive(-(turn), (turn));
			// Approach
			// driveSystem.autoDrive(moveSpeed - (turn / (5/moveSpeed), moveSpeed + (turn / (5/moveSpeed));

		}  
		
		else {
			driveSystem.autoDrive(0, 0);
		}
	}

	// Checks to see if vision was correct
	protected boolean isFinished() {
		return Math.abs(error) == 0 || (this.timeSinceInitialized() >= runTime) ;
	}

	// Finishes
	protected void end() {
		driveSystem.autoDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

