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

        limelight.pipelineSwitch(0);
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

    public double getTx() {
        LLResult result = getLatestResult();
        if (result != null && result.isValid()) {
            return result.getTx();
        }
        return 0;
    }

    public boolean hasTarget() {
        LLResult result = getLatestResult();
        return result != null && result.isValid();
    }
    
    public void stop() {
        limelight.stop();
    }
}
