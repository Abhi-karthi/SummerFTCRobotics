package org.firstinspires.ftc.teamcode.game;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.Constants.*;
import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.subsystems.*;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends OpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ScoringSubsystem scoringSubsystem;
    private ScorerRotationSubsystem scorerRotationSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private GamepadEx driver;
    private IMU imu;
    private DriveCommand driveCommand;
    private IntakeCommand intakeCommand;
    private ScoringCommand scoringCommand;
    private ScorerRotationCommand scorerRotationCommand;
    private LimelightCommand limelightCommand;
    @Override
    public void init() {
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParams = new IMU.Parameters(
                new RevHubOrientationOnRobot (
                        Constants.controlHubLogoFacingDirection,
                        Constants.controlHubUSBFacingDirection
                )
        );

        imu.initialize(imuParams);

        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, imu, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        scoringSubsystem = new ScoringSubsystem(hardwareMap, telemetry);
        scorerRotationSubsystem = new ScorerRotationSubsystem(hardwareMap, telemetry);
        limelightSubsystem = new LimelightSubsystem(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);
        driveCommand = new DriveCommand(driver, mecanumDriveSubsystem, "red");
        intakeCommand = new IntakeCommand(driver, intakeSubsystem);
        scoringCommand = new ScoringCommand(driver, scoringSubsystem);
        scorerRotationCommand = new ScorerRotationCommand(driver, scorerRotationSubsystem);
        limelightCommand = new LimelightCommand(limelightSubsystem, scorerRotationSubsystem);

        mecanumDriveSubsystem.setDefaultCommand(
                driveCommand
        );

        intakeSubsystem.setDefaultCommand(
                intakeCommand
        );

        scoringSubsystem.setDefaultCommand(
                scoringCommand
        );

        scorerRotationSubsystem.setDefaultCommand(
                scorerRotationCommand
        );

        driver.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(limelightCommand);
    }

    @Override
    public void init_loop() {
        driver.readButtons();
        if (driver.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
            driveCommand.changeTeam("red");
        } else if (driver.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
            driveCommand.changeTeam("blue");
        }
    }

    @Override
    public void loop() {
        driver.readButtons();
        CommandScheduler.getInstance().run();
        telemetry.update();
    }

    @Override
    public void stop() {
        if (limelightSubsystem != null) {
            limelightSubsystem.stop();
        }
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().reset();
    }
}
