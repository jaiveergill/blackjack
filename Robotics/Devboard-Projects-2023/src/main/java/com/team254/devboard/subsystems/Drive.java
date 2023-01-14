package com.team254.devboard.subsystems;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.team254.devboard.Constants;
import com.team254.lib.drivers.TalonFXFactory;
import com.team254.lib.loops.ILooper;
import com.team254.lib.util.DriveSignal;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Drive extends Subsystem{
    private static Drive mInstance;
    private final TalonFX mLeftMaster, mRightMaster;


    public static Drive getInstance() {
        if (mInstance == null) {
            mInstance = new Drive();
        }

        return mInstance;
    }

    public static class PeriodicIO {
        public static double left;
        public static double right;
    }

    public void setOpenLoop(DriveSignal signal) {
        PeriodicIO.right = signal.getRight();
        PeriodicIO.left = signal.getLeft();
        
    }

        
    private Drive() {
        mLeftMaster = TalonFXFactory.createDefaultTalon(Constants.kLeftMasterId);
        mRightMaster = TalonFXFactory.createDefaultTalon(Constants.kRightMasterId);
    }

    @Override
    public void stop() {
        
    }

    @Override
    public boolean checkSystem() {
        return false;
    }


    @Override
    public void outputTelemetry() {
        SmartDashboard.putNumber("left demand", PeriodicIO.left);
        SmartDashboard.putNumber("right demand", PeriodicIO.right);
    }

    public void writePeriodicOutputs() {

    }

    
}