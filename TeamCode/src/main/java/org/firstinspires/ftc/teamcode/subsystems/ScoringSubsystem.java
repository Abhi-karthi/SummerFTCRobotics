package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ScoringSubsystem extends SubsystemBase {
    private final DcMotor shooter1, shooter2;
    private final Telemetry telemetry;

    public ScoringSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");

        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        shooter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.telemetry = telemetry;

        register();
    }

    @Override
    public void periodic() {
        telemetry.addData("shooter1MotorPower", getShooter1MotorPower());
        telemetry.addData("shooter2MotorPower", getShooter2MotorPower());
    }

    public void shoot(double shooter1Power, double shooter2Power) {
        shooter1.setPower(shooter1Power);
        shooter2.setPower(shooter2Power);
    }

    public double getShooter1MotorPower() { return shooter1.getPower(); }
    public double getShooter2MotorPower() { return shooter2.getPower(); }

}