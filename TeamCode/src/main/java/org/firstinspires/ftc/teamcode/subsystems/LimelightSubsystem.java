package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LimelightSubsystem extends SubsystemBase {
    private final Limelight3A limelight;
    private final Telemetry telemetry;

    public LimelightSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        this.telemetry = telemetry;

        limelight.pipelineSwitch(3);
        limelight.start();

        register();
    }

    @Override
    public void periodic() {
        LLResult result = getLatestResult();
        if (result != null && result.isValid()) {
            telemetry.addData("Limelight Target", "tx: %.2f, ty: %.2f", result.getTx(), result.getTy());
        } else {
            telemetry.addData("Limelight Target", "None");
        }
    }

    public LLResult getLatestResult() {
        return limelight.getLatestResult();
    }

    public double getTy() {
        LLResult result = getLatestResult();
        if (result != null && result.isValid()) {
            return result.getTy();
        }
        return 0;
    }

    public double calculateHoodPositionTicks() {
        double h2 = 11.5;
        double hh2 = 29.5;

        double a2 = getTy();

        double angleRadians = Math.toRadians(a2);

        double distanceInches = (hh2 - h2) / Math.tan(angleRadians);

        return 0.0035434*Math.pow(1.04529, distanceInches) + 0.702259;
    }

    public boolean hasTarget() {
        LLResult result = getLatestResult();
        return result != null && result.isValid();
    }


    public void stop() {
        limelight.stop();
    }
}