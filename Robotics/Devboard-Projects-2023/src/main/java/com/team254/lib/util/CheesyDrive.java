package com.team254.lib.util;


public class CheesyDrive {

    private static CheesyDrive mInstance;


    public static CheesyDrive getInstance() {
        if (mInstance == null) {
            mInstance = new CheesyDrive();
        }

        return mInstance;
    }


    public DriveSignal setSignal(double throttle, double turn) {
        double left = throttle + turn*throttle;
        double right = throttle - turn*throttle;

        DriveSignal signal = new DriveSignal(left, right);
        return signal;
    }
    
    
    public void readInputs() {
        // double currentTimestamp = Timer.getFPGATimestamp();
        
    }

}