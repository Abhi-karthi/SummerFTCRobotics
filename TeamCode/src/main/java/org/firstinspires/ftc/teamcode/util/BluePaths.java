package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class BluePaths {
    public PathChain INITIAL_TO_SCORE_1;
    public PathChain SCORE_1_TO_INTAKE_START_1;
    public PathChain INTAKE_START_1_TO_INTAKE_END_1;
    public PathChain INTAKE_END_1_TO_SCORE_2;
    public PathChain SCORE_2_INTAKE_START_2;
    public PathChain INTAKE_START_2_TO_INTAKE_END_2;
    public PathChain INTAKE_END_2_TO_SCORE_3;
    public PathChain SCORE_3_TO_INTAKE_START_3;
    public PathChain INTAKE_START_3_TO_INTAKE_END_3;
    public PathChain INTAKE_END_3_TO_SCORE_4;

    public BluePaths(Follower follower) {
        INITIAL_TO_SCORE_1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(56.000, 8.000),
                                new Pose(70.500, 25.333)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(120))
                .build();
        SCORE_1_TO_INTAKE_START_1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(70.500, 25.333),
                                new Pose(41.051, 82.724)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(180))
                .build();
        INTAKE_START_1_TO_INTAKE_END_1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(41.051, 82.724),
                                new Pose(22.366, 82.604)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();
        INTAKE_END_1_TO_SCORE_2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(22.366, 82.604),
                                new Pose(54.946, 79.903)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(120))
                .build();
        SCORE_2_INTAKE_START_2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(54.946, 79.903),
                                new Pose(41.173, 59.501)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(180))
                .build();
        INTAKE_START_2_TO_INTAKE_END_2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(41.173, 59.501),
                                new Pose(21.500, 59.167)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();
        INTAKE_END_2_TO_SCORE_3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(21.500, 59.167),
                                new Pose(65.100, 67.581)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(120))
                .build();
        SCORE_3_TO_INTAKE_START_3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(65.100, 67.581),
                                new Pose(46.126, 35.918)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(180))
                .build();
        INTAKE_START_3_TO_INTAKE_END_3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(46.126, 35.918),
                                new Pose(21.993, 35.380)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();
        INTAKE_END_3_TO_SCORE_4 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(21.993, 35.380),
                                new Pose(63.745, 21.202)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(120))
                .build();
    }
}
