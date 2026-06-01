package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Constants {

    //Motor Declerations
    intake1 = hardwareMap.get(DcMotor.class, "intake1");
    intake2 = hardwareMap.get(DcMotor.class, "intake2");
    shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
    shooter2 = hardwareMap.get(DcMotor.class, "shooter2");

    intake1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    intake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    intake1.setDirection(DcMotor.Direction.REVERSE);
    shooter2.setDirection(DcMotor.Direction.REVERSE);

    //Servo Declerations
    servoHood = hardwareMap.get(Servo.class, "hood");
    servoTurret = hardwareMap.get(Servo.class, "turret");
    servoGate = hardwareMap.get(Servo.class, "gate");

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

}