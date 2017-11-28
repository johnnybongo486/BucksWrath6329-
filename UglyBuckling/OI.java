package org.usfirst.frc6329.UglyBuckling;

import org.usfirst.frc6329.UglyBuckling.commands.*;
import org.usfirst.frc6329.UglyBuckling.commands.moveGear.EjectGear;
import org.usfirst.frc6329.UglyBuckling.commands.moveGear.PickUpGear;
import org.usfirst.frc6329.UglyBuckling.commands.shifter.Downshift;
import org.usfirst.frc6329.UglyBuckling.commands.shifter.Upshift;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public Joystick xbox;

    public JoystickButton HighGearButton;
    public JoystickButton LowGearButton;
    public JoystickButton pauseWinch;
    public JoystickButton runWinch;
    public JoystickButton pickupGear;
    public JoystickButton dropGear;
    public JoystickButton goForward;
    public JoystickButton goBackward;
    
    public Joystick flightStick;

    public OI() {

    	xbox = new Joystick(0);
    	flightStick = new Joystick(1); 
        runWinch = new JoystickButton(flightStick, 3);
        runWinch.whileHeld(new StartWinch());
        pauseWinch = new JoystickButton(flightStick, 3);
        pauseWinch.whenReleased(new StopWinch());
        HighGearButton = new JoystickButton(xbox, 6);
        HighGearButton.whenPressed(new Upshift());
        LowGearButton = new JoystickButton(xbox,5);
        LowGearButton.whenPressed(new Downshift());
        pickupGear = new JoystickButton(flightStick,1);
        pickupGear.whenPressed(new PickUpGear());
        dropGear = new JoystickButton(flightStick,2);
        dropGear.whenPressed(new EjectGear());
        
        // Changes default forward
        goForward = new JoystickButton(xbox,1);
        goForward.whenPressed(new Forward());
        goBackward = new JoystickButton(xbox,2);
        goBackward.whenPressed(new Backward());
    
    }

    public Joystick getXbox() {
        return xbox;
    }

    public Joystick getFlightStick() {
        return flightStick;
    }

    public boolean ispickupGearPressed() {
        return pickupGear.get();
    }
    
    public boolean isdropGearPressed() {
        return dropGear.get();
    }
    
    public boolean isForwardPressed() {
        return goForward.get();
    }
    
    public boolean isBackwardPressed() {
        return goBackward.get();
    }
    
    public void rumbleLeft() {
        xbox.setRumble(GenericHID.RumbleType.kLeftRumble, Constants.OI.RUMBLE_INTENSITY);
    }

    public void rumbleRight() {
        xbox.setRumble(GenericHID.RumbleType.kRightRumble, Constants.OI.RUMBLE_INTENSITY);
    }
    
    public void stopRumble() {
    	xbox.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        xbox.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    }

}
