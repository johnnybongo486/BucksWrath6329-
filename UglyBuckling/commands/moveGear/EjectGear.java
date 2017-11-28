package org.usfirst.frc6329.UglyBuckling.commands.moveGear;

import org.usfirst.frc6329.UglyBuckling.Robot;
import org.usfirst.frc6329.UglyBuckling.commands.gearpickup.ReversePickup;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class EjectGear extends CommandGroup {

   public EjectGear() {
       addParallel(new LowerGear());
       addParallel(new ReversePickup());
   }

   @Override
   protected boolean isFinished() {
       return !Robot.oi.isdropGearPressed();
}
}
