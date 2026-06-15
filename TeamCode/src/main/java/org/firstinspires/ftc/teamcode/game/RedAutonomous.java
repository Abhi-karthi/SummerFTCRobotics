package org.firstinspires.ftc.teamcode.game;

import static org.firstinspires.ftc.teamcode.util.Constants.IMU_DIRECTION_OFFSET_RED;
import static org.firstinspires.ftc.teamcode.util.Constants.INTAKE_MOTOR_POWER;
import static org.firstinspires.ftc.teamcode.util.Constants.RED_AUTONOMOUS_INITIAL_POS;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.StartEndCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.commands.ShooterCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.util.RedPaths;
import org.firstinspires.ftc.teamcode.util.PoseStorage;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Red Auto")
@SuppressWarnings("unused")
public class RedAutonomous extends OpMode {
    private IntakeSubsystem intakeSubsystem;
    private Follower follower;

    private SequentialCommandGroup autonomousRoutine;

    @Override
    public void init() {
        LimelightSubsystem limelightSubsystem = new LimelightSubsystem(hardwareMap, telemetry);
        ShooterSubsystem shooterSubsystem = new ShooterSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);


        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(RED_AUTONOMOUS_INITIAL_POS);

        RedPaths paths = new RedPaths(follower);
        autonomousRoutine = new SequentialCommandGroup(
                new FollowPathCommand(follower, paths.INITIAL_TO_SCORE_1),
                new ShooterCommand(shooterSubsystem, limelightSubsystem, intakeSubsystem),
                new FollowPathCommand(follower, paths.SCORE_1_TO_INTAKE_START_1),
                intakeWithFollowerHelper(paths.INTAKE_START_1_TO_INTAKE_END_1),
                new FollowPathCommand(follower, paths.INTAKE_END_1_TO_SCORE_2),
                new ShooterCommand(shooterSubsystem, limelightSubsystem, intakeSubsystem),
                new FollowPathCommand(follower, paths.SCORE_2_INTAKE_START_2),
                intakeWithFollowerHelper(paths.INTAKE_START_2_TO_INTAKE_END_2),
                new FollowPathCommand(follower, paths.INTAKE_END_2_TO_SCORE_3),
                new ShooterCommand(shooterSubsystem, limelightSubsystem, intakeSubsystem),
                new FollowPathCommand(follower, paths.SCORE_3_TO_INTAKE_START_3),
                intakeWithFollowerHelper(paths.INTAKE_START_3_TO_INTAKE_END_3),
                new FollowPathCommand(follower, paths.INTAKE_END_3_TO_SCORE_4),
                new ShooterCommand(shooterSubsystem, limelightSubsystem, intakeSubsystem)
        );

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void start() {
        CommandScheduler.getInstance().schedule(autonomousRoutine);
    }

    @Override
    public void loop() {
        follower.update();
        CommandScheduler.getInstance().run();
        telemetry.update();
    }

    @Override
    public void stop() {
        PoseStorage.currentHeadingRadians = follower.getPose().getHeading() + IMU_DIRECTION_OFFSET_RED;
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().reset();
    }

    private Command intakeWithFollowerHelper(PathChain path) {
        return new ParallelDeadlineGroup(  // ParallelDeadlineGroup means when the path command finishes, the intake command ends. When the intake command ends, it is turned off.
                new FollowPathCommand(follower, path),
                new StartEndCommand( // When the command starts, the intake starts; when it ends, the intake stops.
                        () -> intakeSubsystem.intake(INTAKE_MOTOR_POWER, INTAKE_MOTOR_POWER),
                        () -> intakeSubsystem.intake(0, 0),
                        intakeSubsystem
                )
        );
    }
}
