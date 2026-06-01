package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterSubsystem extends SubsystemBase {
    IMU imu;
    DcMotor shooter1, shooter2, backLeft, backRight;


    private Telemetry telemetry;

    public ShooterSubsystem(HardwareMap hardwareMap, IMU imu_) {
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");

        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        shooter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.imu = imu_;
        this.telemetry = telemetry;

        register();
    }

    @Override
    public void periodic() {
        telemetry.addData("shooter1MotorPower", getShooter1MotorPower());
        telemetry.addData("shooter2MotorPower", getShooter2MotorPower());
    }

    public void resetIMU() {
        imu.resetYaw();
    }

    public double getShooter1MotorPower() { return shooter1.getPower(); }
    public double getShooter2MotorPower() { return shooter2.getPower(); }

}