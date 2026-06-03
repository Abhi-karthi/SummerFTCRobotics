package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_MOTOR_POWER;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private final GamepadEx gamepad;

    public IntakeCommand(GamepadEx gamepad, IntakeSubsystem intakeSubsystem) {
        this.gamepad = gamepad;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        double triggerValue = gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        if (triggerValue > 0.5) {
            intakeSubsystem.intake(INTAKE_MOTOR_POWER, INTAKE_MOTOR_POWER);
        } else {
            intakeSubsystem.intake(0, 0);
        }
    }

    @Override
    public void end(boolean interrupted) { intakeSubsystem.intake(0, 0); }

}
