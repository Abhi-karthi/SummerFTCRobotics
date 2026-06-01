package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;

public class Constants {



    intake1 = hardwareMap.get(DcMotor.class, "intake1");
    intake2 = hardwareMap.get(DcMotor.class, "intake2");
    shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
    shooter2 = hardwareMap.get(DcMotor.class, "shooter2");



    intake1.setDirection(DcMotor.Direction.REVERSE);
    intake1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    intake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    shooter2.setDirection(DcMotor.Direction.REVERSE);



    public static final double CENTER = 0.5;
    public static final double LEFT_LIMIT = 0.0;
    public static final double RIGHT_LIMIT = 1.0;

}