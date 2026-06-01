package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.ScorerRotationSubsystem;

public class ScorerRotationCommand extends CommandBase {
    private final ScorerRotationSubsystem rotationSubsystem;
    private final GamepadEx gamepad;

    public ScorerRotationCommand(GamepadEx gamepad, ScorerRotationSubsystem rotationSubsystem) {
        this.gamepad = gamepad;
        this.rotationSubsystem = rotationSubsystem;
        addRequirements(rotationSubsystem);
    }

    @Override
    public void execute() {
        double stickX = gamepad.getRightX();
        double servoPosition = (stickX + 1.0) / 2.0;
        
        rotationSubsystem.setRotation(servoPosition);
    }
}
