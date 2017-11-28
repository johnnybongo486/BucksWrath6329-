package org.usfirst.frc6329.UglyBuckling.commands.shifter;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class Downshift extends CommandGroup {

   public Downshift() {
	   addParallel(new RumbleLeft());
	   addSequential(new LowGear());
   }
   
}
