package com.team254.lib.util;

import edu.wpi.first.wpilibj.Timer;

public class PID {
    double Kp; // proportional constant
    double Ki; // integral constant
    double Kd; // derivative/dampening term constant
    double lastError; // previous error to calculate derivative
    double previousTime; // previous time to get change in time
    double setpoint;
    double error;
    double totalError; // total error for calculating integral term
    
    public PID(int Ki, int Kp, int Kd) {
        previousTime = 0;
        lastError = 0; 	
        totalError = 0;
    }

    public void setSetpoint(double newSetpoint) {
        setpoint = newSetpoint;
    }
    
    public void setError(double newError) {
        error = newError;
    }

    public double calculatePID(double value) {
        setError(setpoint-value); // set the error to difference between measured value and setpoint
        double dT = Timer.getFPGATimestamp() - previousTime; // calculate change in time
        
        totalError += error*dT;
        
        double dampeningTerm = Kd * (error-lastError)/dT; // calculate derivative/dampening term using Kd and difference in error divided by change in time (slope)
        double proportionalTerm = Kp*error; // calculate proportional term
        double integralTerm = Ki*(totalError); // integral term using constant integral time multiplied by total error for integral
        
        lastError = error; // set new last error for finding change in error
        previousTime = Timer.getFPGATimestamp(); // set previous time for finding change in time
        
        return dampeningTerm + proportionalTerm + integralTerm; // return output as sum of terms
    }

    
}