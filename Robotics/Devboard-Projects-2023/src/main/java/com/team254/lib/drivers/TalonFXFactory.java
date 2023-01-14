package com.team254.lib.drivers;

// import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.controls.*;
import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.signals.FeedbackSensorSourceValue;
import com.ctre.phoenixpro.signals.InvertedValue;
import com.ctre.phoenixpro.signals.NeutralModeValue;

/**
 * Creates CANTalon objects and configures all the parameters we care about to factory defaults. Closed-loop and sensor
 * parameters are not set, as these are expected to be set by the application.
 */
public class TalonFXFactory {

    private final static int kTimeoutMs = 100;

    public static class Configuration {
        public NeutralModeValue NEUTRAL_MODE = NeutralModeValue.Coast;
        // factory default
        public double NEUTRAL_DEADBAND = 0.04;

        //public SensorInitializationStrategy SENSOR_INITIALIZATION_STRATEGY = SensorInitializationStrategy.BootToZero;
        public double SENSOR_OFFSET_DEGREES = 0;

        public boolean ENABLE_SUPPLY_CURRENT_LIMIT = false;
        public boolean ENABLE_STATOR_CURRENT_LIMIT = false;

        public boolean ENABLE_SOFT_LIMIT = false;
        public boolean ENABLE_LIMIT_SWITCH = false;
        public int FORWARD_SOFT_LIMIT = 0;
        public int REVERSE_SOFT_LIMIT = 0;

        public boolean INVERTED = false;
        public boolean SENSOR_PHASE = false;

        public int CONTROL_FRAME_PERIOD_MS = 10;
        public int MOTION_CONTROL_FRAME_PERIOD_MS = 1000;
        public int GENERAL_STATUS_FRAME_RATE_MS = 10;
        public int FEEDBACK_STATUS_FRAME_RATE_MS = 1000;
        public int QUAD_ENCODER_STATUS_FRAME_RATE_MS = 1000;
        public int ANALOG_TEMP_VBAT_STATUS_FRAME_RATE_MS = 1000;
        public int PULSE_WIDTH_STATUS_FRAME_RATE_MS = 1000;

        //public SensorVelocityMeasPeriod VELOCITY_MEASUREMENT_PERIOD = SensorVelocityMeasPeriod.Period_100Ms;
        public int VELOCITY_MEASUREMENT_ROLLING_AVERAGE_WINDOW = 64;

        public double OPEN_LOOP_RAMP_RATE = 0.0;
        public double CLOSED_LOOP_RAMP_RATE = 0.0;
    }

    private static final Configuration kDefaultConfiguration = new Configuration();
    private static final Configuration kSlaveConfiguration = new Configuration();

    static {
        // This control frame value seems to need to be something reasonable to avoid the Talon's
        // LEDs behaving erratically. Potentially try to increase as much as possible.
        kSlaveConfiguration.CONTROL_FRAME_PERIOD_MS = 100;
        kSlaveConfiguration.MOTION_CONTROL_FRAME_PERIOD_MS = 1000;
        kSlaveConfiguration.GENERAL_STATUS_FRAME_RATE_MS = 1000;
        kSlaveConfiguration.FEEDBACK_STATUS_FRAME_RATE_MS = 1000;
        kSlaveConfiguration.QUAD_ENCODER_STATUS_FRAME_RATE_MS = 1000;
        kSlaveConfiguration.ANALOG_TEMP_VBAT_STATUS_FRAME_RATE_MS = 1000;
        kSlaveConfiguration.PULSE_WIDTH_STATUS_FRAME_RATE_MS = 1000;
        kSlaveConfiguration.ENABLE_SOFT_LIMIT = false;
    }

    // create a CANTalon with the default (out of the box) configuration
    public static TalonFX createDefaultTalon(CanDeviceId id) {
        return createTalon(id, kDefaultConfiguration);
    }

    public static TalonFX createPermanentSlaveTalon(CanDeviceId slave_id, CanDeviceId master_id, boolean opposeMasterDirection) {
        if( slave_id.getBus() != master_id.getBus() ) {
                throw new RuntimeException("Master and Slave Talons must be on the same CAN bus");
        }
        final TalonFX talon = createTalon(slave_id, kSlaveConfiguration);
        talon.setControl(new Follower(master_id.getDeviceNumber(), opposeMasterDirection));
        return talon;
    }


    public static TalonFX createTalon(CanDeviceId id, Configuration conf) {
        TalonFX talon = new TalonFX(id.getDeviceNumber(), id.getBus());
        talon.setControl(new DutyCycleOut(0.0));

//        //talon.changeMotionControlFramePeriod(config.MOTION_CONTROL_FRAME_PERIOD_MS);
//        talon.clearMotionProfileHasUnderrun(kTimeoutMs);
//        talon.clearMotionProfileTrajectories();

// //        talon.clearStickyFaults(kTimeoutMs);

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.HardwareLimitSwitch.ForwardLimitEnable = false;
        config.HardwareLimitSwitch.ReverseLimitEnable = false;
        config.HardwareLimitSwitch.ForwardLimitAutosetPositionEnable = false;
        config.HardwareLimitSwitch.ReverseLimitAutosetPositionEnable = false;

//        talon.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated,
//                LimitSwitchNormal.Disabled, kTimeoutMs);
//        talon.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated,
//                LimitSwitchNormal.Disabled, kTimeoutMs);
//        talon.overrideLimitSwitchesEnable(config.ENABLE_LIMIT_SWITCH);

        // Turn off re-zeroing by default.
//        talon.configSetParameter(
//                ParamEnum.eClearPositionOnLimitF, 0, 0, 0, kTimeoutMs);
//        talon.configSetParameter(
//                ParamEnum.eClearPositionOnLimitR, 0, 0, 0, kTimeoutMs);

//        talon.configNominalOutputForward(0, kTimeoutMs);
//        talon.configNominalOutputReverse(0, kTimeoutMs);
//        talon.configNeutralDeadband(conf.NEUTRAL_DEADBAND, kTimeoutMs);

        config.MotorOutput.DutyCycleNeutralDeadband = conf.NEUTRAL_DEADBAND;


        //talon.configMotorCommutation(MotorCommutation.Trapezoidal);

//        talon.configPeakOutputForward(1.0, kTimeoutMs);
//        talon.configPeakOutputReverse(-1.0, kTimeoutMs);

        config.MotorOutput.PeakForwardDutyCycle = 1.0;
        config.MotorOutput.PeakReverseDutyCycle = -1.0;

//        talon.setNeutralMode(config.NEUTRAL_MODE);

        config.MotorOutput.NeutralMode = conf.NEUTRAL_MODE;

//        talon.configForwardSoftLimitThreshold(config.FORWARD_SOFT_LIMIT, kTimeoutMs);
//        talon.configForwardSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);
//        talon.configReverseSoftLimitThreshold(config.REVERSE_SOFT_LIMIT, kTimeoutMs);
//        talon.configReverseSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);

        config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = conf.FORWARD_SOFT_LIMIT;
        config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = conf.REVERSE_SOFT_LIMIT;
        config.SoftwareLimitSwitch.ForwardSoftLimitEnable = false;
        config.SoftwareLimitSwitch.ReverseSoftLimitEnable = false;

//        talon.overrideSoftLimitsEnable(config.ENABLE_SOFT_LIMIT);

//        talon.setInverted(config.INVERTED);

        // TODO set sensor phase?
        //        talon.setSensorPhase(config.SENSOR_PHASE);
        config.MotorOutput.Inverted = conf.INVERTED ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;

//        talon.selectProfileSlot(0, 0);

//        talon.configVelocityMeasurementPeriod(config.VELOCITY_MEASUREMENT_PERIOD, kTimeoutMs);
//        talon.configVelocityMeasurementWindow(config.VELOCITY_MEASUREMENT_ROLLING_AVERAGE_WINDOW,
//                kTimeoutMs);

        config.Audio.BeepOnBoot = true;

//        talon.configOpenloopRamp(config.OPEN_LOOP_RAMP_RATE, kTimeoutMs);
//        talon.configClosedloopRamp(config.CLOSED_LOOP_RAMP_RATE, kTimeoutMs);

        config.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = conf.OPEN_LOOP_RAMP_RATE;
        config.OpenLoopRamps.TorqueOpenLoopRampPeriod = conf.OPEN_LOOP_RAMP_RATE;
        config.OpenLoopRamps.VoltageOpenLoopRampPeriod = conf.OPEN_LOOP_RAMP_RATE;

        config.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = conf.CLOSED_LOOP_RAMP_RATE;
        config.ClosedLoopRamps.TorqueClosedLoopRampPeriod = conf.CLOSED_LOOP_RAMP_RATE;
        config.ClosedLoopRamps.VoltageClosedLoopRampPeriod = conf.CLOSED_LOOP_RAMP_RATE;

        // TODO voltage compensation
//        talon.configVoltageCompSaturation(0.0, kTimeoutMs);
//        talon.configVoltageMeasurementFilter(32, kTimeoutMs);
//        talon.enableVoltageCompensation(false);


//        talon.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(config.ENABLE_SUPPLY_CURRENT_LIMIT, 20, 60, .2), kTimeoutMs);
//        talon.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(config.ENABLE_STATOR_CURRENT_LIMIT, 20, 60, .2), kTimeoutMs);
//      TODO triggerThreshold and TTLimit
        config.CurrentLimits.SupplyCurrentLimit = 60;
        config.CurrentLimits.StatorCurrentLimit = 60;
        config.CurrentLimits.SupplyCurrentLimitEnable = conf.ENABLE_SUPPLY_CURRENT_LIMIT;
        config.CurrentLimits.StatorCurrentLimitEnable = conf.ENABLE_STATOR_CURRENT_LIMIT;

//        talon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, kTimeoutMs);
//        talon.configIntegratedSensorInitializationStrategy(config.SENSOR_INITIALIZATION_STRATEGY, kTimeoutMs);
//        talon.configIntegratedSensorOffset(config.SENSOR_OFFSET_DEGREES, kTimeoutMs);

        // TODO confirm units
        config.Feedback.FeedbackRotorOffset = conf.SENSOR_OFFSET_DEGREES;
        config.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;

//        TODO set control and status frame periods
//        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General,
//                config.GENERAL_STATUS_FRAME_RATE_MS, kTimeoutMs);
//        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0,
//                config.FEEDBACK_STATUS_FRAME_RATE_MS, kTimeoutMs);
//
//        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature,
//                config.QUAD_ENCODER_STATUS_FRAME_RATE_MS, kTimeoutMs);
//        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_4_AinTempVbat,
//                config.ANALOG_TEMP_VBAT_STATUS_FRAME_RATE_MS, kTimeoutMs);
//        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_8_PulseWidth,
//                config.PULSE_WIDTH_STATUS_FRAME_RATE_MS, kTimeoutMs);
//
//        talon.setControlFramePeriod(ControlFrame.Control_3_General, config.CONTROL_FRAME_PERIOD_MS);

        talon.getConfigurator().apply(config, 0.05);
        return talon;
    }
}