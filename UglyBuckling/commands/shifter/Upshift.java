package org.usfirst.frc6329.UglyBuckling.commands.shifter;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class Upshift extends CommandGroup {

   public Upshift() {
	   addParallel(new RumbleRight());
	   addSequential(new HighGear());
   }
   
}
