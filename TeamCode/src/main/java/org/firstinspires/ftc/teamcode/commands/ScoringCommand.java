package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ScoringSubsystem;

public class ScoringCommand extends CommandBase{
    private final ScoringSubsystem shooterSubsystem;
    private final GamepadEx gamepad;

    public ScoringCommand(GamepadEx gamepad, ScoringSubsystem shoot) {
        this.gamepad = gamepad;
        shooterSubsystem = shoot;
        addRequirements(shoot);
    }

    @Override
    public void execute() {
        gamepad.readButtons();

        if (gamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
            shooterSubsystem.shoot(0.2, 0.2);
        } else {
            shooterSubsystem.shoot(0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) { shooterSubsystem.shoot(0, 0); }

}
