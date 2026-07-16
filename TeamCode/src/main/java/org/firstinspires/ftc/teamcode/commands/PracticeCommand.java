package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.PracticeSubsystem;

public class PracticeCommand extends CommandBase {
    private final PracticeSubsystem practiceSubsystem;
    private final GamepadEx gamepad;

    public PracticeCommand(GamepadEx gamepad, PracticeSubsystem practiceSubsystem) {
        this.practiceSubsystem = practiceSubsystem;
        this.gamepad = gamepad;
        addRequirements(practiceSubsystem);
    }

    @Override
    public void execute() {
        double power = gamepad.getLeftY();

        practiceSubsystem.setExampleMotorPower(power);
    }

    @Override
    public void end(boolean interrupted) {
        practiceSubsystem.setExampleMotorPower(0);
    }
}
