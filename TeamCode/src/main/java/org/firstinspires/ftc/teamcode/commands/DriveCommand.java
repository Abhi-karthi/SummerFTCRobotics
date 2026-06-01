package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class DriveCommand extends CommandBase{
    private final MecanumDriveSubsystem drive;
    private final GamepadEx gamepad;
    private String team;
    public DriveCommand(GamepadEx gamepad, MecanumDriveSubsystem drive, String team) {
        this.drive = drive;
        this.gamepad = gamepad;
        this.team = team;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double x = gamepad.getLeftX();
        double y = gamepad.getLeftY();
        double rotation = gamepad.getRightX();

        drive.drive(x, y, rotation);
    }

    @Override
    public void end(boolean interrupted) { drive.drive(0, 0, 0); }

    public void changeTeam(String team) {
        this.team = team;
    }
}
