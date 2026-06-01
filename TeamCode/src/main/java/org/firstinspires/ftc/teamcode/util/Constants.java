package org.firstinspires.ftc.teamcode.util;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.LogoFacingDirection;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot.UsbFacingDirection;

public class Constants {
    DcMotor intake1 = hardwareMap.get(DcMotor.class, "intake1");
    DcMotor intake2 = hardwareMap.get(DcMotor.class, "intake2");
    DcMotor shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
    DcMotor shooter2 = hardwareMap.get(DcMotor.class, "shooter2");


    public static final double CENTER = 0.5;
    public static final double LEFT_LIMIT = 0.0;
    public static final double RIGHT_LIMIT = 1.0;

}