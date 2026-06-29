package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.util.Constants.DEGREE_OFFSET_TO_SERVO_TICKS;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_WAITING_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_WAITING_TIME_2;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_CLOSED;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_CLOSED_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_OPEN;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_OPEN_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_BOTTOM;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_MOTOR_POWER_2;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_RUNTIME;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_RUNTIME_2;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private final IntakeSubsystem intakeSubsystem;

    private final ElapsedTime shooterElapsedTime;
    private enum ShooterState { RUN_MOTOR, RUN_INTAKE, OPEN_GATE, RUN_INTAKE_2, CLOSE_GATE, STOP_INTAKE, RUN_MOTOR_2, STOP}
    private ShooterState currentShooterState;
    private int repetitions;
    private double hoodPosition;
    public ShooterCommand(ShooterSubsystem shooterSubsystem, LimelightSubsystem limelightSubsystem, IntakeSubsystem intakeSubsystem) {
        currentShooterState = ShooterState.RUN_MOTOR;
        shooterElapsedTime = new ElapsedTime();

        this.shooterSubsystem = shooterSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        repetitions = 0;
        addRequirements(shooterSubsystem, intakeSubsystem, limelightSubsystem);
    }

    @Override
    public void initialize() {
        shooterElapsedTime.reset();
        currentShooterState = ShooterState.RUN_MOTOR;
        repetitions = 0;

        shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
        shooterSubsystem.setHoodServoPosition(HOOD_BOTTOM);
        hoodPosition = limelightSubsystem.calculateHoodPositionTicks();
//        hoodPosition = shooterSubsystem.getHoodServoPosition();
    }

    @Override
    public void execute() {
        switch (currentShooterState) {
            case RUN_MOTOR: // aiming turret and running shooter motors at the same time and hood
                shooterSubsystem.setShooterMotorPower(FLYWHEEL_MOTOR_POWER);
                shooterSubsystem.setTurretServoPosition(DEGREE_OFFSET_TO_SERVO_TICKS(limelightSubsystem.getTy()));
                shooterSubsystem.setHoodServoPosition(hoodPosition);
                if (shooterElapsedTime.seconds() >= FLYWHEEL_MOTOR_WAITING_TIME) {
                    shooterElapsedTime.reset();
                    currentShooterState = ShooterState.RUN_INTAKE;
                }
                break;
            case RUN_INTAKE:
                intakeSubsystem.intake(INTAKE_MOTOR_POWER, INTAKE_MOTOR_POWER);
                shooterSubsystem.setHoodServoPosition(hoodPosition);
                if (shooterElapsedTime.seconds() >= INTAKE_RUNTIME) {
                    shooterElapsedTime.reset();
                    repetitions++;
                    intakeSubsystem.intake(0, 0);
                    currentShooterState = ShooterState.OPEN_GATE;
                }
                break;
            case OPEN_GATE:
                shooterSubsystem.setShooterGateServoPosition(GATE_OPEN);
                shooterSubsystem.setHoodServoPosition(hoodPosition);
                if (shooterElapsedTime.seconds() >= GATE_OPEN_TIME) {
                    shooterElapsedTime.reset();
                    currentShooterState = ShooterState.RUN_INTAKE_2;
                }
                break;
            case RUN_INTAKE_2:
                intakeSubsystem.intake(INTAKE_MOTOR_POWER_2, INTAKE_MOTOR_POWER_2);
                shooterSubsystem.setHoodServoPosition(hoodPosition);
                if (shooterElapsedTime.seconds() >= INTAKE_RUNTIME_2) {
                    shooterElapsedTime.reset();
                    intakeSubsystem.intake(0, 0);
                    currentShooterState = ShooterState.STOP_INTAKE;
                }
                break;
            case STOP_INTAKE:
                shooterSubsystem.setHoodServoPosition(hoodPosition);
                intakeSubsystem.intake(0, 0);
                shooterElapsedTime.reset();
                currentShooterState = ShooterState.STOP;
                break;
            case STOP:
                shooterSubsystem.setHoodServoPosition(hoodPosition);
                shooterSubsystem.setShooterMotorPower(0);
                intakeSubsystem.intake(0, 0);
                shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.setShooterMotorPower(0);
        intakeSubsystem.intake(0, 0);
        shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
    }

    @Override
    public boolean isFinished() {
        return currentShooterState == ShooterState.STOP;
    }
}
