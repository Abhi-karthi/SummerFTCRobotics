package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Constants {
    //Servo Positions
    //TODO: Test for servo positions
    public static final double CENTER_TURRET = 0.5;
    public static final double LEFT_LIMIT_TURRET = 0.75;
    public static final double RIGHT_LIMIT_TURRET = 0.25;

    public static final double HOOD_TOP = 0.5;
    public static final double HOOD_BOTTOM = 0;
    public static final double HOOD_CENTER = 0.25;

    public static final double GATE_OPEN = 0.5;
    public static final double GATE_CLOSED = 0;
    public static final double GATE_OPEN_TIME = 0.3;
    public static final double GATE_CLOSED_TIME = 0.25;

    public static final double INTAKE_RUNTIME = 0.3;

    public static final RevHubOrientationOnRobot.UsbFacingDirection controlHubUSBFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.DOWN;
    public static final RevHubOrientationOnRobot.LogoFacingDirection controlHubLogoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;

    // TODO: Correct motor power
    public static final double FLYWHEEL_MOTOR_POWER = 0.2;
    public static final double FLYWHEEL_MOTOR_WAITING_TIME = 0.5;  // seconds
    public static final double FLYWHEEL_MOTOR_WAITING_TIME_2 = 0.5;

    // TODO: TUNE LIMELIGHT SOMEHOW
    public static final double DEGREE_OFFSET_TO_SERVO_TICKS = 1.5;
    public static final double SHOOTER_ACCURACY_THRESHOLD_TICKS = 1.0;
}