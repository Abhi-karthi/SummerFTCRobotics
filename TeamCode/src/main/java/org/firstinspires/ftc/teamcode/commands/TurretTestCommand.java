package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class TurretTestCommand extends CommandBase {
    private GamepadEx gamepad;
    private ShooterSubsystem shooterSubsystem;

    public TurretTestCommand(GamepadEx gamepad, ShooterSubsystem shooterSubsystem) {
        this.gamepad = gamepad;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        if (gamepad.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
            shooterSubsystem.setTurretServoPosition(shooterSubsystem.getTurretServoPosition() - 0.01);
        }
        if (gamepad.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
            shooterSubsystem.setTurretServoPosition(shooterSubsystem.getTurretServoPosition() + 0.01);
        }
    }
}
