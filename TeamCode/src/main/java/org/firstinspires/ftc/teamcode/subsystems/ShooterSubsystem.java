package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterSubsystem extends SubsystemBase {
    private final DcMotor shooter1, shooter2;
    private final Telemetry telemetry;
    private final Servo shooterGateServo;
    private final Servo hoodServo;
    private final Servo turretServo;

    public ShooterSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");
        shooterGateServo = hardwareMap.get(Servo.class, "shooterGateServo");
        hoodServo = hardwareMap.get(Servo.class, "hoodServo");
        turretServo = hardwareMap.get(Servo.class, "turretServo");

        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setDirection(DcMotor.Direction.REVERSE);
        shooter1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.telemetry = telemetry;

        register();
    }

    @Override
    public void periodic() {
        telemetry.addData("shooter1MotorPower", getShooter1MotorPower());
        telemetry.addData("shooter2MotorPower", getShooter2MotorPower());
        telemetry.addData("Scorer Rotation Position", shooterGateServo.getPosition());
        telemetry.addData("HoodServoPower:", getHoodServoPosition());
        telemetry.addData("TurretServoPosition:", getTurretServoPosition());
        telemetry.addData("Shooter gate servo position: ", getShooterGateServoPosition());
    }

    public double getShooter1MotorPower() { return shooter1.getPower(); }
    public double getShooter2MotorPower() { return shooter2.getPower(); }
    public void setShooterMotorPower(double power) { shooter1.setPower(power); shooter2.setPower(power); }
    public void setShooterGateServoPosition(double position) { shooterGateServo.setPosition(position); }
    public double getShooterGateServoPosition () { return shooterGateServo.getPosition(); }
    public double getHoodServoPosition() {
        return hoodServo.getPosition();
    }
    public void setHoodServoPosition(double power) {
        hoodServo.setPosition(power);
    }
    public void setTurretServoPosition(double position) { turretServo.setPosition(position); }
    public double getTurretServoPosition() { return turretServo.getPosition(); }
}
