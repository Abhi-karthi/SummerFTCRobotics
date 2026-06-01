package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HoodSubsystem extends SubsystemBase {
    private final CRServo hoodServo;

    private final Telemetry telemetry;

    public HoodSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hoodServo = hardwareMap.get(CRServo.class, "hoodServo");

        register();
    }

    @Override
    public void periodic() {
        telemetry.addData("HoodServoPower:", getHoodServoPower());
    }

    public double getHoodServoPower() {
        return hoodServo.getPower();
    }

    public void setHoodServoPower(double power) {
        hoodServo.setPower(power);
    }
}
