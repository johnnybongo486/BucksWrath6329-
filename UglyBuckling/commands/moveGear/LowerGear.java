package org.usfirst.frc6329.UglyBuckling.commands.moveGear;

import org.usfirst.frc6329.UglyBuckling.Constants;
/**
 * Lower the dustpan to the hardstop (by time or amps).
 */
public class LowerGear extends MoveTheCrane {

    public LowerGear() {
        super(Constants.GearSystem.LOWER_TIME, Constants.GearSystem.LOWER_SPEED);
    }


    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void end() {
        super.end();    }
}
