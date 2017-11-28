package org.usfirst.frc6329.UglyBuckling.commands.moveGear;

import org.usfirst.frc6329.UglyBuckling.commands.gearpickup.StopPickup;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StoreGear extends CommandGroup {

   public StoreGear() {
       addSequential(new RaiseGear());
       addParallel(new StopPickup());
       addSequential(new HoldGearUp());
   }
   
}
