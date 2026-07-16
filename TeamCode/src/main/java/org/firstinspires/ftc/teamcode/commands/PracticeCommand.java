package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.PracticeSubsystem;

public class PracticeCommand extends CommandBase {
    private final PracticeSubsystem practiceSubsystem;

    public PracticeCommand(PracticeSubsystem practiceSubsystem) {
        this.practiceSubsystem = practiceSubsystem;
    }

    @Override
    public void execute() {
        practiceSubsystem.setExampleMotorPower(1);
    }

    @Override
    public void end(boolean interrupted) {
        practiceSubsystem.setExampleMotorPower(0);
    }
}
