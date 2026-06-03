package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.util.Constants.DEGREE_OFFSET_TO_SERVO_TICKS;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_WAITING_TIME;
import static org.firstinspires.ftc.teamcode.util.Constants.FLYWHEEL_MOTOR_WAITING_TIME_2;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_ACCURACY_THRESHOLD_TICKS;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_CLOSED;
import static org.firstinspires.ftc.teamcode.util.Constants.GATE_OPEN;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_ACCURACY_THRESHOLD_TICKS;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_BOTTOM;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_CENTER;
import static org.firstinspires.ftc.teamcode.util.Constants.HOOD_TOP;
import static org.firstinspires.ftc.teamcode.util.Constants.SHOOTER_ACCURACY_THRESHOLD_TICKS;

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
    private enum ShooterState { RUN_MOTOR, RUN_INTAKE, OPEN_GATE, CLOSE_GATE, STOP_INTAKE, RUN_MOTOR_2, STOP}
    private ShooterState currentShooterState;
    private int repetitions;

    public ShooterCommand(ShooterSubsystem shooterSubsystem, LimelightSubsystem limelightSubsystem, IntakeSubsystem intakeSubsystem) {
        currentShooterState = ShooterState.RUN_MOTOR;
        shooterElapsedTime = new ElapsedTime();

        this.shooterSubsystem = shooterSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        repetitions = 0;
    }

    @Override
    public void execute() {
        switch (currentShooterState) {
            case RUN_MOTOR: // aiming turret and running shooter motors
                shooterSubsystem.setShooterMotorPower(FLYWHEEL_MOTOR_POWER);
                shooterSubsystem.setTurretServoPosition(limelightSubsystem.getTx()*DEGREE_OFFSET_TO_SERVO_TICKS);
                shooterSubsystem.setHoodServoPosition(limelightSubsystem.calculateHoodPositionTicks());
                if (shooterElapsedTime.seconds() >= FLYWHEEL_MOTOR_WAITING_TIME && Math.abs(shooterSubsystem.getTurretServoPosition()-limelightSubsystem.getTx()*DEGREE_OFFSET_TO_SERVO_TICKS) <= SHOOTER_ACCURACY_THRESHOLD_TICKS && Math.abs(shooterSubsystem.getHoodServoPosition()-limelightSubsystem.calculateHoodPositionTicks()) <= HOOD_ACCURACY_THRESHOLD_TICKS) {
                    shooterElapsedTime.reset();
                    repetitions = 0;
                    currentShooterState = ShooterState.RUN_INTAKE;
                }
                break;
            case RUN_INTAKE:
                intakeSubsystem.intake(1, 1);
                if (shooterElapsedTime.seconds() > 0.5) {
                    shooterElapsedTime.reset();
                    repetitions++;
                    currentShooterState = ShooterState.OPEN_GATE;
                }
                break;
            case OPEN_GATE:
                shooterSubsystem.setShooterGateServoPosition(GATE_OPEN);
                if (Math.abs(shooterSubsystem.getShooterGateServoPosition()-GATE_OPEN) <= GATE_ACCURACY_THRESHOLD_TICKS) {
                    shooterElapsedTime.reset();
                    currentShooterState = ShooterState.CLOSE_GATE;
                }
                break;
            case CLOSE_GATE:
                shooterSubsystem.setShooterGateServoPosition(GATE_CLOSED);
                if (Math.abs(shooterSubsystem.getShooterGateServoPosition()-GATE_CLOSED) <= GATE_ACCURACY_THRESHOLD_TICKS) {
                    shooterElapsedTime.reset();
                    currentShooterState = ShooterState.STOP_INTAKE;
                }
                break;
            case STOP_INTAKE:
                intakeSubsystem.intake(0, 0);
                shooterElapsedTime.reset();
                if (repetitions >= 3) {
                    currentShooterState = ShooterState.STOP;
                } else {
                    currentShooterState = ShooterState.RUN_MOTOR_2;
                }
                break;
            case RUN_MOTOR_2:
                shooterSubsystem.setShooterMotorPower(FLYWHEEL_MOTOR_POWER);
                shooterSubsystem.setTurretServoPosition(limelightSubsystem.getTx()*DEGREE_OFFSET_TO_SERVO_TICKS);
                if (shooterElapsedTime.seconds() >= FLYWHEEL_MOTOR_WAITING_TIME_2 && Math.abs(shooterSubsystem.getTurretServoPosition()-limelightSubsystem.getTx()*DEGREE_OFFSET_TO_SERVO_TICKS) <= SHOOTER_ACCURACY_THRESHOLD_TICKS) {
                    shooterElapsedTime.reset();
                    currentShooterState = ShooterState.RUN_INTAKE;
                }
                break;
            case STOP:
                shooterSubsystem.setShooterMotorPower(0);
                intakeSubsystem.intake(0, 0);
                shooterSubsystem.setShooterGateServoPosition(HOOD_CENTER);
                break;
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.setShooterMotorPower(0);
        intakeSubsystem.intake(0, 0);
        shooterSubsystem.setShooterGateServoPosition(HOOD_CENTER);
    }
}
