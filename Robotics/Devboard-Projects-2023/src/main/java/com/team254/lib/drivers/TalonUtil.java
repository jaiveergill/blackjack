// package com.team254.lib.drivers;

// import com.ctre.phoenixpro.*;
// import edu.wpi.first.wpilibj.DriverStation;
// import com.ctre.phoenixpro.hardware.TalonFX;
// import com.ctre.phoenixpro.hardware.core.CoreTalonFX;

// public class TalonUtil {
//     /**
//      * checks the specified error code for issues
//      *
//      * @param errorCode error code
//      * @param message   message to print if error happens
//      */
//     public static void checkError(ErrorCode errorCode, String message) {
//         if (errorCode != errorCode.OK) { // TODO: fix this ErrorCode error
//             DriverStation.reportError(message + " " + errorCode, false);
//         }
//     }

//     /**
//      * checks the specified error code and throws an exception if there are any issues
//      *
//      * @param errorCode error code
//      * @param message   message to print if error happens
//      */
//     public static void checkErrorWithThrow(ErrorCode errorCode, String message) {
//         if (errorCode != errorCode.OK) {
//             throw new RuntimeException(message + " " + errorCode);
//         }
//     }

//     public enum StickyFault {
//         BootDuringEnable,
//         DeviceTemp,
//         ForwardHardLimit,
//         ForwardSoftLimit,
//         Hardware,
//         OverSupplyV,
//         ProcTemp,
//         ReverseHardLimit,
//         ReverseSoftLimit,
//         Undervoltage,
//         UnstableSupplyV
//     }

//     public static void checkStickyFaults(String subsystemName, TalonFX talon) {
//         boolean[] faults = new boolean[StickyFault.values().length];
//         faults[0] = talon.getStickyFault_BootDuringEnable().getValue();
//         faults[1] = talon.getStickyFault_DeviceTemp().getValue();
//         faults[2] = talon.getStickyFault_ForwardHardLimit().getValue();
//         faults[3] = talon.getStickyFault_ForwardSoftLimit().getValue();
//         faults[4] = talon.getStickyFault_Hardware().getValue();
//         faults[5] = talon.getStickyFault_OverSupplyV().getValue();
//         faults[6] = talon.getStickyFault_ProcTemp().getValue();
//         faults[7] = talon.getStickyFault_ReverseHardLimit().getValue();
//         faults[8] = talon.getStickyFault_ReverseSoftLimit().getValue();
//         faults[9] = talon.getStickyFault_Undervoltage().getValue();
//         faults[10] = talon.getStickyFault_UnstableSupplyV().getValue();

//         for (int i=0; i<faults.length; i++) {
//             if (faults[i]) {
//                 DriverStation.reportError(subsystemName + ": Talon Fault! " + StickyFault.values()[i].toString(), false);
//             }
//         }

//         talon.clearStickyFaults();
//     }
// }