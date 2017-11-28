package org.usfirst.frc6329.UglyBuckling.commands.moveGear;

import org.usfirst.frc6329.UglyBuckling.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 * Lower the dustpan to the hardstop (by time or amps).
 */
public class HoldGear extends Command {

	public final double speed;

    public HoldGear(double speed) {
        requires(Robot.gearCrane);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	Robot.gearCrane.gearCraneGearCraneESC.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    	Robot.gearCrane.StopTheCrane();
    }

    @Override
    protected void interrupted() {
        end();
    }
}