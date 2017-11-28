package org.usfirst.frc6329.UglyBuckling;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc6329.UglyBuckling.RobotMap;

public class PDP extends PowerDistributionPanel {

    private DigitalInput indicator;
    public PDP() {
        super();
        indicator = new DigitalInput(0);
    }

    public double getClimberAmps() {
        return getCurrent(RobotMap.PDP.PDP_CLIMBER_MOTOR);
    }

    public double getGearCraneAmps() {
        return getCurrent(RobotMap.PDP.PDP_GEARCRANE_MOTOR);
    }

    public double getGearPickupAmps() {
        return getCurrent(RobotMap.PDP.PDP_GEARPICKUP_MOTOR);
    }

    public double getMeanDrivetrainAmps() {
        return getTotalDriveTrainAmps() / 4;
    }

    /*
     * Methods for getting current from individual drivetrain cims
     */

    public double getRightFrontAmps() {
        return getCurrent(RobotMap.PDP.PDP_RIGHT_MOTOR_FRONT);
    }

    public double getRightBackAmps() {
        return getCurrent(RobotMap.PDP.PDP_RIGHT_MOTOR_BACK);
    }

    public double getLeftFrontAmps() {
        return getCurrent(RobotMap.PDP.PDP_LEFT_MOTOR_FRONT);
    }

    public double getLeftBackAmps() {
        return getCurrent(RobotMap.PDP.PDP_LEFT_MOTOR_BACK);
    }

    public double getTotalLeftDriveTrainAmps() {
        return getLeftFrontAmps() + getLeftBackAmps();
    }

    public double getTotalRightDriveTrainAmps() {
        return getRightFrontAmps() + getRightBackAmps();
    }

    public double getTotalDriveTrainAmps() {
        return getTotalLeftDriveTrainAmps() + getTotalRightDriveTrainAmps();
    }

    public boolean getIndicator() {
        return indicator.get();
    }

    public boolean isUglyBuckling() {
        return indicator.get();
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("PDP/Current/Total", getTotalCurrent());
        SmartDashboard.putNumber("PDP/Current/DriveTrain", getTotalDriveTrainAmps());
        SmartDashboard.putNumber("PDP/Current/LeftFront", getLeftFrontAmps());
        SmartDashboard.putNumber("PDP/Current/LeftBack", getLeftBackAmps());
        SmartDashboard.putNumber("PDP/Current/RightFront", getRightFrontAmps());
        SmartDashboard.putNumber("PDP/Current/RightBack", getRightBackAmps());
        SmartDashboard.putNumber("PDP/Current/Gear Crane", getGearCraneAmps());
        SmartDashboard.putNumber("PDP/Current/Gear Pickup", getGearPickupAmps());
        SmartDashboard.putNumber("PDP/Current/Climber", getClimberAmps());
    }

}