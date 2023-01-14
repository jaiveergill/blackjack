package com.team254.lib.util;

/**
 * A drivetrain command consisting of the left, right motor settings and whether
 * the brake mode is enabled.
 */
public class DriveSignal {
    private final double mLeftMotor;
    private final double mRightMotor;


    public DriveSignal(double left, double right) {
        mLeftMotor = left;
        mRightMotor = right;
    }

    public double getLeft() {
        return mLeftMotor;
    }

    public double getRight() {
        return mRightMotor;
    }
}
