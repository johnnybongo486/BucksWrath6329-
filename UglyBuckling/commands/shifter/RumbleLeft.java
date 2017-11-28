package org.usfirst.frc6329.UglyBuckling.commands.shifter;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc6329.UglyBuckling.Robot;

/**
 *
 */
public class RumbleLeft extends Command {

    public RumbleLeft() {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.rumbleLeft();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.stopRumble();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
