package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PracticeSubsystem extends SubsystemBase {
    private DcMotor exampleMotor;
    private Telemetry telemetry;

    public PracticeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        exampleMotor = hardwareMap.get(DcMotor.class, "exampleMotor");
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("Motor Power", getExampleMotorPower());
    }

    public double getExampleMotorPower() {
        return exampleMotor.getPower();
    }

    public void setExampleMotorPower(double power) {
        exampleMotor.setPower(power);
    }
}
