package org.firstinspires.ftc.teamcode.util;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@Configurable
public class Constants {
    //Servo Positions
    //TODO: Test for servo positions
    public static double CENTER_TURRET = 0.5;
    public static double LEFT_LIMIT_TURRET = 0.75;
    public static double RIGHT_LIMIT_TURRET = 0.25;

    public static double HOOD_TOP = 0.5;
    public static double HOOD_MID_1 = 0.125;
    public static double HOOD_MID_2 = 0.385;
    public static double HOOD_BOTTOM = 0;
    public static double HOOD_CENTER = 0.25;

    public static double GATE_OPEN = 0.5;
    public static double GATE_CLOSED = 0;
    public static double GATE_OPEN_TIME = 0.3;
    public static double GATE_CLOSED_TIME = 0.25;

    public static double INTAKE_RUNTIME = 0.3;

    public static RevHubOrientationOnRobot.UsbFacingDirection controlHubUSBFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.DOWN;
    public static RevHubOrientationOnRobot.LogoFacingDirection controlHubLogoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;

    // TODO: Correct motor power
    public static double FLYWHEEL_MOTOR_POWER = 0.2;
    public static double FLYWHEEL_MOTOR_WAITING_TIME = 0.5;  // seconds
    public static double FLYWHEEL_MOTOR_WAITING_TIME_2 = 0.5;

    // TODO: TUNE LIMELIGHT SOMEHOW
    public static double DEGREE_OFFSET_TO_SERVO_TICKS = 1.5;
    public static double SHOOTER_ACCURACY_THRESHOLD_TICKS = 1.0;
    public static double INTAKE_MOTOR_POWER = 0.3;

    public static Pose RED_AUTONOMOUS_INITIAL_POS = new Pose(86.166, 9, Math.toRadians(90));
    public static Pose BLUE_AUTONOMOUS_INITIAL_POS = new Pose(55.333, 9, Math.toRadians(90));
    public static double IMU_DIRECTION_OFFSET_BLUE = -Math.toRadians(90);
    public static double IMU_DIRECTION_OFFSET_RED = Math.toRadians(90);
}