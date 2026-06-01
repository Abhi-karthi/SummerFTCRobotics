package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase{
    private final IntakeSubsystem intakeSubsystem;
    private final GamepadEx gamepad;

    public IntakeCommand(GamepadEx gamepad, IntakeSubsystem shoot) {
        this.gamepad = gamepad;
        intakeSubsystem = shoot;
        addRequirements(shoot);
    }

    @Override
    public void execute() {
        gamepad.readButtons();

        if (gamepad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            intakeSubsystem.intake(1.0, 1.0);
        } else {
            intakeSubsystem.intake(0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) { intakeSubsystem.intake(0, 0); }

}
