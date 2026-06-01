package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase{
    private final ShooterSubsystem shooterSubsystem;
    private final GamepadEx gamepad;

    public ShooterCommand(GamepadEx gamepad, ShooterSubsystem shoot) {
        this.gamepad = gamepad;
        shooterSubsystem = shoot;
        addRequirements(shoot);
    }

    @Override
    public void execute() {
        gamepad.readButtons();

        if (gamepad.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            shooterSubsystem.shoot(1.0, 1.0);
        } else {
            shooterSubsystem.shoot(0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) { shooterSubsystem.shoot(0, 0); }

}
