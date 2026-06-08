package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_WAITING_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_CLOSED;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_OPEN;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_BOTTOM;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_CENTER;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_MID_1;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_MID_2;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_TOP;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_MOTOR_POWER;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class HoodTestCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;
    private GamepadEx gamepad;
    private double[] positions;
    private double currentPosition;
    private IntakeSubsystem intakeSubsystem;
    private ElapsedTime motorTimer;
    private enum ShooterTestState {IDLE, MOTOR_RUN, INTAKE_START, OPEN_GATE, GATE_OPEN, GATE_CLOSE};

    public HoodTestCommand(GamepadEx gamepad, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.gamepad = gamepad;
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        positions = new double[]{HOOD_BOTTOM, HOOD_MID_1, HOOD_CENTER, HOOD_MID_2, HOOD_TOP};
        currentPosition = 0;
        motorTimer.reset();
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
        motorTimer.reset();
    }

    @Override
    public void execute() {
        if (gamepad.wasJustPressed(GamepadKeys.Button.Y)) {
            if (currentPosition < 4) currentPosition++;
            else currentPosition = 0;
        }
        if (gamepad.wasJustPressed(GamepadKeys.Button.X)) {
            shooterSubsystem.setShooterMotorPower(FLYWHEEL_MOTOR_POWER);
            intakeSubsystem.intake(INTAKE_MOTOR_POWER, INTAKE_MOTOR_POWER);
            motorTimer.reset();
        }
//        if (motorTimer.seconds() >= FLYWHEEL_MOTOR_WAITING_TIME) {
//            shooterSubsystem.setShooterGateServoPosition(GATE_OPEN);
//
//        }

    }

}
