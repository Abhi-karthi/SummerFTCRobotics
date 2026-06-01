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
        gamepad.readButtons();
        double x = gamepad.getLeftX();
        double y = -gamepad.getLeftY();
        double rotation = gamepad.getRightX();

        if (gamepad.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)) {
            drive.resetIMU();
        }

        drive.drive(x, y, rotation);
    }

    @Override
    public void end(boolean interrupted) { drive.drive(0, 0, 0); }

    public void changeTeam(String team) {
        this.team = team;
    }
}
