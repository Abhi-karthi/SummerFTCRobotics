package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ScorerRotationSubsystem extends SubsystemBase {
    private final Servo rotationServo;
    private final Telemetry telemetry;

    public static final double CENTER = 0.5;
    public static final double LEFT_LIMIT = 0.0;
    public static final double RIGHT_LIMIT = 1.0;

    public ScorerRotationSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.rotationServo = hardwareMap.get(Servo.class, "scorerRotationServo");
        this.telemetry = telemetry;
        
        // Initialize at center
        setRotation(CENTER);
        
        register();
    }

    @Override
    public void periodic() {
        telemetry.addData("Scorer Rotation Position", rotationServo.getPosition());
    }

    public void setRotation(double position) {
        rotationServo.setPosition(position);
    }

    public void center() {
        setRotation(CENTER);
    }
}
