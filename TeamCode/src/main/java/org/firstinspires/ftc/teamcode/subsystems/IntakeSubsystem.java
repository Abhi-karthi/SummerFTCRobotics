package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotor intake1, intake2;
    private final Telemetry telemetry;

    public IntakeSubsystem(@NonNull HardwareMap hardwareMap, Telemetry telemetry) {
        intake1 = hardwareMap.get(DcMotor.class, "intake1");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");

        intake1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake1.setDirection(DcMotor.Direction.REVERSE);

        this.telemetry = telemetry;

        register();
    }

    @Override
    public void periodic() {

        telemetry.addData("intake1MotorPower", getIntake1MotorPower());
        telemetry.addData("intake2MotorPower", getIntake2MotorPower());
    }

    public void intake(double intake1Power, double intake2Power) {
        intake1.setPower(intake1Power);
        intake2.setPower(intake2Power);
    }

    public double getIntake1MotorPower() {
        if (intake1 == null) return -1;
        return intake1.getPower();
    }
    public double getIntake2MotorPower() {
        if (intake2 == null) return -1;
        return intake2.getPower();
    }

}