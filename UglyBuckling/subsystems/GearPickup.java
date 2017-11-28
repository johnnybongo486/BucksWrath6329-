package org.usfirst.frc6329.UglyBuckling.subsystems;

import org.usfirst.frc6329.UglyBuckling.RobotMap;
import org.usfirst.frc6329.UglyBuckling.commands.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class GearPickup extends Subsystem {


    private final SpeedController gearPickupGearPickupESC = RobotMap.gearPickupGearPickupESC;


    public void initDefaultCommand() {
    	setDefaultCommand(new StopPickup());

    }
    public void runPickup() {
    	gearPickupGearPickupESC.set(1);
    }
    
    public void stopPickup() {
    	gearPickupGearPickupESC.set(0);	
    }

    public void reversePickup() {
    	gearPickupGearPickupESC.set(-1);	
    }
}
