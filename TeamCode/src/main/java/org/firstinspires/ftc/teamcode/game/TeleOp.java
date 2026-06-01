package org.firstinspires.ftc.teamcode.game;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.ShooterCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

@TeleOp(name = "TeleOp")
public class Teleop extends OpMode {
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private GamepadEx driver;
    private IMU imu;

    @Override
    public void init() {
        CommandScheduler.getInstance().reset();

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParams = new IMU.Parameters(
                new RevHubOrientationOnRobot (
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );

        imu.initialize(imuParams);

        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap, imu, telemetry);
        shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);


        driver = new GamepadEx(gamepad1);
        driveCommand = new DriveCommand(driver, mecanumDriveSubsystem, "red");

        DriveCommand driveCommand = new DriveCommand(driver, mecanumDriveSubsystem, "blue");
        ShooterCommand shooterCommand = new ShooterCommand(driver, shooterSubsystem);

        mecanumDriveSubsystem.setDefaultCommand(driveCommand);
        shooterSubsystem.setDefaultCommand(shooterCommand);
    }

    @Override
    public void loop() {
        driver.readButtons();
        CommandScheduler.getInstance().run();
        telemetry.update();
    }
}
