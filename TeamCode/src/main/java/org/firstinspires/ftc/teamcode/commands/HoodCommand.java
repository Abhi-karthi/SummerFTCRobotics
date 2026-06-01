package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.HoodSubsystem;

public class HoodCommand extends CommandBase {
    private final HoodSubsystem hoodSubsystem;
    private final GamepadEx gamepad;

    public HoodCommand(GamepadEx gamepad, HoodSubsystem hoodSubsystem) {
        this.gamepad = gamepad;
        this.hoodSubsystem = hoodSubsystem;
        addRequirements(hoodSubsystem);
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        hoodSubsystem.setHoodServoPower(0);
    }
}
