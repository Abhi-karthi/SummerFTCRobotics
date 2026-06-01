package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.ScorerRotationSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;

public class ScorerRotationCommand extends CommandBase {
    private final ScorerRotationSubsystem rotationSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private final GamepadEx gamepad;

    private static final double AUTO_AIM_KP = 0.01; 

    public ScorerRotationCommand(GamepadEx gamepad, 
                                 ScorerRotationSubsystem rotationSubsystem,
                                 LimelightSubsystem limelightSubsystem) {
        this.gamepad = gamepad;
        this.rotationSubsystem = rotationSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(rotationSubsystem);
    }

    @Override
    public void execute() {
        double servoPosition;

        if (limelightSubsystem.hasTarget()) {
            double tx = limelightSubsystem.getTx();
            
            servoPosition = ScorerRotationSubsystem.CENTER + (tx * AUTO_AIM_KP);
            
            servoPosition = Math.max(ScorerRotationSubsystem.LEFT_LIMIT, 
                            Math.min(ScorerRotationSubsystem.RIGHT_LIMIT, servoPosition));
        } else {
            double stickX = gamepad.getRightX();
            servoPosition = (stickX + 1.0) / 2.0;
        }
        
        rotationSubsystem.setRotation(servoPosition);
    }
}
