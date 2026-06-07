package org.firstinspires.ftc.teamcode.commands.tests;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class HoodCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;
    private GamepadEx gamepad;
    private int positions;
    public HoodCommand(GamepadEx gamepad, ShooterSubsystem shooterSubsystem) {
        this.gamepad = gamepad;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        if (gamepad.getButton())
    }
}
