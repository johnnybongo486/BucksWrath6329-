package org.usfirst.frc6329.UglyBuckling;

public class Constants {

    public static boolean isUglyBuckling = true;

    public static double pickConstant(double bucklingValue, double wrathValue) {
        return isUglyBuckling ? bucklingValue : wrathValue;
    }

    public static int pickConstant(int bucklingValue, int wrathValue) {
        return isUglyBuckling ? bucklingValue : wrathValue;
    }

    public static boolean pickConstant(boolean bucklingValue, boolean wrathValue) {
        return isUglyBuckling ? bucklingValue : wrathValue;
    }

    public static long pickConstant(long bucklingValue, long wrathValue) {
        return isUglyBuckling ? bucklingValue : wrathValue;
    }

    public class OI {
    	public static final long RUMBLE_MILLIS = 250;
        public static final double RUMBLE_INTENSITY = 1.0;
    }

    public class GearSystem {
        public static final double LOWER_SPEED = 0.2;
        public static final double RAISE_SPEED = -0.2;
        public static final double HOLD_DOWN_SPEED = 0.1;
        public static final double HOLD_UP_SPEED = -0.1;

        public static final long RAISE_TIME = 1000;
        public static final long LOWER_TIME = 500;
        public static final double HARDSTOP_AMPS = 7.0;
        public static final long OVERRIDE_TIME = 200;

        }
    }

