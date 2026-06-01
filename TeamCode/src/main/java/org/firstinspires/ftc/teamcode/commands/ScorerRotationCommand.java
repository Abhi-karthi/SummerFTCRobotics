package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.ScorerRotationSubsystem;

/**
 * Command to control the scorer's rotation using the gamepad's right stick X axis.
 */
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
        // Map stick input (-1 to 1) to servo range (0 to 1)
        // Stick left (-1) -> 0.0, Center (0) -> 0.5, Stick right (1) -> 1.0
        double stickX = gamepad.getRightX();
        double servoPosition = (stickX + 1.0) / 2.0;
        
        rotationSubsystem.setRotation(servoPosition);
    }
}
