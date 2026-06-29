package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class RedPaths { // TODO: Fix red paths to not go over half way and to go slower when intaking (blue paths are updated)
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

    public RedPaths(Follower follower) {
        INITIAL_TO_SCORE_1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(86.166, 9.000),
                                new Pose(77.833, 23.167)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(70))
                .build();
        SCORE_1_TO_INTAKE_START_1 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(77.833, 23.167),
                                new Pose(100.000, 81.833)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(70), Math.toRadians(0))
                .build();
        INTAKE_START_1_TO_INTAKE_END_1 = follower
                .pathBuilder()
                .addParametricCallback(0.0, () -> follower.setMaxPower(0.3))
                .addPath(
                        new BezierLine(
                                new Pose(100.000, 81.833),
                                new Pose(121.167, 81.667)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        INTAKE_END_1_TO_SCORE_2 = follower
                .pathBuilder()
                .addParametricCallback(0.0, () -> follower.setMaxPower(1))
                .addPath(
                        new BezierLine(
                                new Pose(121.167, 81.667),
                                new Pose(81.841, 77.559)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(50))
                .build();
        SCORE_2_INTAKE_START_2 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(81.841, 77.559),
                                new Pose(99.833, 59.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(0))
                .build();
        INTAKE_START_2_TO_INTAKE_END_2 = follower
                .pathBuilder()
                .addParametricCallback(0.0, () -> follower.setMaxPower(0.3))
                .addPath(
                        new BezierLine(
                                new Pose(99.833, 59.000),
                                new Pose(121.470, 58.513)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        INTAKE_END_2_TO_SCORE_3 = follower
                .pathBuilder()
                .addParametricCallback(0.0, () -> follower.setMaxPower(1))
                .addPath(
                        new BezierLine(
                                new Pose(121.470, 58.513),
                                new Pose(70.673, 71.834)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(50))
                .build();
        SCORE_3_TO_INTAKE_START_3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(70.673, 71.834),
                                new Pose(100.333, 35.500)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(50), Math.toRadians(0))
                .build();
        INTAKE_START_3_TO_INTAKE_END_3 = follower
                .pathBuilder()
                .addParametricCallback(0.0, () -> follower.setMaxPower(0.3))
                .addPath(
                        new BezierLine(
                                new Pose(100.333, 35.500),
                                new Pose(119.259, 34.632)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        INTAKE_END_3_TO_SCORE_4 = follower
                .pathBuilder()
                .addParametricCallback(0.0, () -> follower.setMaxPower(1))
                .addPath(
                        new BezierLine(
                                new Pose(119.259, 34.632),
                                new Pose(81.688, 18.583)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(70))
                .build();
    }
}
