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
    }

    @Override
    public void execute() {
        if (gamepad.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
            shooterSubsystem.setTurretServoPosition(ShooterSubsystem.);
        }
    }
}
