package org.usfirst.frc6329.UglyBuckling.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc6329.UglyBuckling.Robot;
import org.usfirst.frc6329.UglyBuckling.commands.moveGear.EjectGear;
import org.usfirst.frc6329.UglyBuckling.commands.moveGear.HoldGearDown;
import org.usfirst.frc6329.UglyBuckling.commands.moveGear.StoreGear;

/**
 *
 */
public class Blue2 extends CommandGroup {

    public Blue2() {

    	requires(Robot.driveSystem);
    	
    	addParallel(new StoreGear());
    	addSequential(new DriveStraight(0.7,3));
    	addSequential(new DriveStraight(0.5,1.5));
    	addSequential(new DriveStraight(0,1));
      	addParallel(new EjectGear());
    	addSequential(new DriveStraight(0,.5));
    	addParallel(new HoldGearDown());
    	addSequential(new DriveStraight(-0.5,2));
    	addParallel(new StoreGear());
    	addSequential(new DriveStraight(-0.7,2));
    } 
}
