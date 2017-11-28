package org.usfirst.frc6329.UglyBuckling.commands.moveGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc6329.UglyBuckling.Robot;
import org.usfirst.frc6329.UglyBuckling.commands.gearpickup.StartPickup;

/**
 * Command group for lowering and opening the dustpan to intake a gear
 */
public class PickUpGear extends CommandGroup {

    public PickUpGear() {
        addSequential(new LowerGear());
        addParallel(new StartPickup());
        addSequential(new HoldGearDown());
    }

    @Override
    protected boolean isFinished() {
        return !Robot.oi.ispickupGearPressed();
    }
}