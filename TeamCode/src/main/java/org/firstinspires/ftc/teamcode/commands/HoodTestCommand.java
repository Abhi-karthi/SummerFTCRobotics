package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.util.Constants.DEGREE_OFFSET_TO_SERVO_TICKS;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_WAITING_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_CLOSED;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_CLOSED_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_OPEN;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_BOTTOM;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_CENTER;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_MID_1;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_MID_2;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_TOP;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_OPEN_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_RUNTIME;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class HoodTestCommand extends CommandBase {
    private ShooterSubsystem shooterSubsystem;
    private GamepadEx gamepad;
    private double[] positions;
    private int currentPosition;
    private IntakeSubsystem intakeSubsystem;
    private ElapsedTime motorTimer;
    private enum ShooterTestState {IDLE, MOTOR_RUN, INTAKE_START, GATE_OPEN, GATE_CLOSE};
    private ShooterTestState currentShooterState;
    private LimelightSubsystem limelightSubsystem;
    public HoodTestCommand(GamepadEx gamepad, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, LimelightSubsystem limelightSubsystem) {
        this.gamepad = gamepad;
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.currentShooterState = ShooterTestState.IDLE;
        this.limelightSubsystem = limelightSubsystem;
        motorTimer = new ElapsedTime();
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

        if (gamepad.wasJustPressed(GamepadKeys.Button.DPAD_LEFT)) {
            shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
        } else if (gamepad.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)) {
            shooterSubsystem.setShooterGateServoPosition(GATE_OPEN);
        }

        shooterSubsystem.setHoodServoPosition(positions[currentPosition]);

        if (gamepad.wasJustPressed(GamepadKeys.Button.X)) {
            currentShooterState = ShooterTestState.MOTOR_RUN;
            motorTimer.reset();
        }
        switch (currentShooterState) {
            case MOTOR_RUN:
                shooterSubsystem.setShooterMotorPower(FLYWHEEL_MOTOR_POWER);
                shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
                if (motorTimer.seconds() >= FLYWHEEL_MOTOR_WAITING_TIME) {
                    currentShooterState = ShooterTestState.INTAKE_START;
                    motorTimer.reset();
                }
                break;
            case INTAKE_START:
                shooterSubsystem.setShooterMotorPower(FLYWHEEL_MOTOR_POWER);
                intakeSubsystem.intake(INTAKE_MOTOR_POWER, INTAKE_MOTOR_POWER);
                if (motorTimer.seconds() >= INTAKE_RUNTIME) {
                    motorTimer.reset();
                    currentShooterState = ShooterTestState.GATE_OPEN;
                }
                break;
            case GATE_OPEN:
                shooterSubsystem.setShooterGateServoPosition(GATE_OPEN);
                if (motorTimer.seconds() >= GATE_OPEN_TIME) {
                    currentShooterState = ShooterTestState.GATE_CLOSE;
                    motorTimer.reset();
                }
                break;
            case GATE_CLOSE:
                shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
                if (motorTimer.seconds() >= GATE_CLOSED_TIME) {
                    currentShooterState = ShooterTestState.IDLE;
                    motorTimer.reset();
                }
                break;
            case IDLE:
                shooterSubsystem.setShooterMotorPower(0);
                intakeSubsystem.intake(0, 0);
                shooterSubsystem.setTurretServoPosition(DEGREE_OFFSET_TO_SERVO_TICKS(limelightSubsystem.getTy()));
        }
    }

}
