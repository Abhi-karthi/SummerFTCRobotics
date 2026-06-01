package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ScorerRotationSubsystem;

public class LimelightCommand extends CommandBase {
    private final LimelightSubsystem limelightSubsystem;
    private final ScorerRotationSubsystem rotationSubsystem;

    private static final double AUTO_AIM_KP = 0.01;

    public LimelightCommand(LimelightSubsystem limelightSubsystem, ScorerRotationSubsystem rotationSubsystem) {
        this.limelightSubsystem = limelightSubsystem;
        this.rotationSubsystem = rotationSubsystem;
        addRequirements(limelightSubsystem, rotationSubsystem);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasTarget()) {
            double tx = limelightSubsystem.getTx();
            double servoPosition = ScorerRotationSubsystem.CENTER + (tx * AUTO_AIM_KP);

            servoPosition = Math.max(ScorerRotationSubsystem.LEFT_LIMIT,
                            Math.min(ScorerRotationSubsystem.RIGHT_LIMIT, servoPosition));

            rotationSubsystem.setRotation(servoPosition);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
