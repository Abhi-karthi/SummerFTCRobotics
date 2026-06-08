package org.firstinspires.ftc.teamcode.game;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.RunCommand;
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
    private LimelightSubsystem limelightSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private GamepadEx driver;
    private IMU imu;
    private DriveCommand driveCommand;
    private TurretTestCommand turretTestCommand;
    private HoodTestCommand hoodTestCommand;

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
        shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
        limelightSubsystem = new LimelightSubsystem(hardwareMap, telemetry);

        driver = new GamepadEx(gamepad1);
        driveCommand = new DriveCommand(driver, mecanumDriveSubsystem, "red");
        turretTestCommand = new TurretTestCommand(driver, shooterSubsystem);
        hoodTestCommand = new HoodTestCommand(driver, shooterSubsystem, intakeSubsystem, limelightSubsystem);

        mecanumDriveSubsystem.setDefaultCommand(
                driveCommand
        );

        intakeSubsystem.setDefaultCommand(new RunCommand(
                () -> {
                    if (driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5) {
                        intakeSubsystem.intake(Constants.INTAKE_MOTOR_POWER, Constants.INTAKE_MOTOR_POWER);
                    } else {
                        intakeSubsystem.intake(0, 0);
                    }
                },
                intakeSubsystem
        ));

        shooterSubsystem.setDefaultCommand(
                hoodTestCommand
        );

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(() -> mecanumDriveSubsystem.resetIMU());
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
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().reset();
        if (limelightSubsystem == null) return;
        limelightSubsystem.stop();
    }
}
