package org.usfirst.frc6329.UglyBuckling.subsystems;

import org.usfirst.frc6329.UglyBuckling.RobotMap;
import org.usfirst.frc6329.UglyBuckling.commands.shifter.Downshift;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Shifter extends Subsystem {

    private final Solenoid shifterSol = RobotMap.shifterShifterSol;    
    
    public void initDefaultCommand() {

        setDefaultCommand(new Downshift());

    }
    
    public void LowGear() {
    	shifterSol.set(false);	
    }

    public void HighGear() {
    	shifterSol.set(true);
    	}
    }
