package org.firstinspires.ftc.teamcode.util;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@Configurable
@SuppressWarnings("unused")
public class Constants {
    //Servo Positions
    //TODO: Test for servo positions
    public static double CENTER_TURRET = 0.5;
    public static double LEFT_LIMIT_TURRET = 0.75;
    public static double RIGHT_LIMIT_TURRET = 0.25;

    public static double HOOD_TOP = 1;
    public static double HOOD_MID_1 = 0.25;
    public static double HOOD_MID_2 = 0.75;
    public static double HOOD_BOTTOM = 0;
    public static double HOOD_CENTER = 0.5;

    public static double GATE_OPEN = 0.5;
    public static double GATE_CLOSED = 0.25;
    public static double GATE_OPEN_TIME = 0.1;
    public static double GATE_CLOSED_TIME = 0.1;

    public static double INTAKE_RUNTIME = 1.4;
    public static double INTAKE_RUNTIME_2 = 0.2;
    public static RevHubOrientationOnRobot.UsbFacingDirection controlHubUSBFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.DOWN;
    public static RevHubOrientationOnRobot.LogoFacingDirection controlHubLogoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;

    // TODO: Correct motor power
    public static double FLYWHEEL_MOTOR_POWER = 0.75;
    public static double FLYWHEEL_MOTOR_WAITING_TIME = 1.5;  // seconds
    public static double FLYWHEEL_MOTOR_WAITING_TIME_2 = 0.5;

    // TODO: TUNE LIMELIGHT SOMEHOW


    public static double B_VALUE = 0.48;
    public static double M_VALUE = 0.0029948;
    public static double DEGREE_OFFSET_TO_SERVO_TICKS(double degrees) {
        return (M_VALUE) * degrees + B_VALUE;
    }
    public static double SHOOTER_ACCURACY_THRESHOLD_TICKS = 1.0;
    public static double INTAKE_MOTOR_POWER = 0.6;

    public static Pose RED_AUTONOMOUS_INITIAL_POS = new Pose(86.166, 9, Math.toRadians(90));
    public static Pose BLUE_AUTONOMOUS_INITIAL_POS = new Pose(55.333, 9, Math.toRadians(90));
    public static double IMU_DIRECTION_OFFSET_BLUE = -Math.toRadians(90);
    public static double IMU_DIRECTION_OFFSET_RED = Math.toRadians(90);
}