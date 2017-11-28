package org.usfirst.frc6329.UglyBuckling.subsystems;

import org.usfirst.frc6329.UglyBuckling.RobotMap;
import org.usfirst.frc6329.UglyBuckling.commands.moveGear.StoreGear;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class GearCrane extends Subsystem {

    public final SpeedController gearCraneGearCraneESC = RobotMap.gearCraneGearCraneESC;

    public void initDefaultCommand() {

        setDefaultCommand(new StoreGear());

    }
    
    public void StopTheCrane() {
    	gearCraneGearCraneESC.set(0);	
    }
}
