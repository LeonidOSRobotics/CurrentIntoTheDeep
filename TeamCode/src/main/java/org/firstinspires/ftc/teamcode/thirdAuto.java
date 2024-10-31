package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import java.util.HashMap;

@Autonomous(name="thirdAuto", group="Auto")
public class thirdAuto extends OpMode {
    private AutonomousHandler autoHandler;

    @Override
    public void init() {
        // Motors & Sensors
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "leftBack");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "rightBack");
        DcMotor spindle = hardwareMap.get(DcMotor.class, "spindle");
        SparkFunOTOS odometry = hardwareMap.get(SparkFunOTOS.class, "odometry");
        DcMotor cap = hardwareMap.get(DcMotor.class, "cap");
        // Servos
        ServoImplEx LSLower = hardwareMap.get(ServoImplEx.class, "LSLower");
        ServoImplEx LSTop = hardwareMap.get(ServoImplEx.class, "LSTop");
        RevTouchSensor lswitch = hardwareMap.get(RevTouchSensor.class, "Lswitch");

        // Define Servo range
        LSLower.setPwmEnable();
        LSTop.setPwmEnable();
        LSLower.scaleRange(0, 1);
        LSTop.scaleRange(0, 1);
        LSLower.setPosition(0.1);
        LSTop.setPosition(0.1);

        // Set motor directions
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set motor modes
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Breaking mode
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        cap.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spindle.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // State 1
        SystemState stateOne = new SystemState();
        stateOne.clawPosition = clawState.CLOSED;
        stateOne.wristPosition = wristState.DOWN;
        stateOne.armPosition = armPose.AUTO_1;
        stateOne.drivePose = new SparkFunOTOS.Pose2D(0, 0, 0);

        // State 1A
        SystemState stateOneA = new SystemState();
        stateOneA.clawPosition = clawState.CLOSED;
        stateOneA.wristPosition = wristState.DOWN;
        stateOneA.armPosition = armPose.BASKET_PREP;
        stateOneA.drivePose = new SparkFunOTOS.Pose2D(5, 9, -45);

        // State 2
        SystemState stateTwo = new SystemState();
        stateTwo.clawPosition = clawState.CLOSED;
        stateTwo.wristPosition = wristState.UP;
        stateTwo.armPosition = armPose.BASKET;
        stateTwo.drivePose = new SparkFunOTOS.Pose2D(10, 9, -90);

        // State 3
        SystemState stateThree = new SystemState();
        stateThree.clawPosition = clawState.CLOSED;
        stateThree.wristPosition = wristState.UP;
        stateThree.armPosition = armPose.BASKET;
        stateThree.drivePose = new SparkFunOTOS.Pose2D(30, 9, -135);

        // State 4
        SystemState stateFour = new SystemState();
        stateFour.clawPosition = clawState.CLOSED;
        stateFour.wristPosition = wristState.DOWN;
        stateFour.armPosition = armPose.BASKET;
        stateFour.drivePose = new SparkFunOTOS.Pose2D(30, 9, -135);

        // State 4A
        SystemState stateFourA = new SystemState();
        stateFourA.clawPosition = clawState.OPENED;
        stateFourA.wristPosition = wristState.UP;
        stateFourA.armPosition = armPose.BASKET;
        stateFourA.drivePose = new SparkFunOTOS.Pose2D(30, 9, -135);

        // State 5PrePre1
        SystemState stateFivePrePre1 = new SystemState();
        stateFivePrePre1.clawPosition = clawState.OPENED;
        stateFivePrePre1.wristPosition = wristState.DOWN;
        stateFivePrePre1.armPosition = armPose.REST;
        stateFivePrePre1.drivePose = new SparkFunOTOS.Pose2D(15.75, 18, 0);

        // State 5Pre1
        SystemState stateFivePre1 = new SystemState();
        stateFivePre1.clawPosition = clawState.OPENED;
        stateFivePre1.wristPosition = wristState.DOWN;
        stateFivePre1.armPosition = armPose.REST;
        stateFivePre1.drivePose = new SparkFunOTOS.Pose2D(15.75, 22, 0);

        // State 5
        SystemState stateFive = new SystemState();
        stateFive.clawPosition = clawState.CLOSED;
        stateFive.wristPosition = wristState.DOWN;
        stateFive.armPosition = armPose.PICKUP;
        stateFive.drivePose = new SparkFunOTOS.Pose2D(15.75, 25, 0); // 27

        // State 5A
        SystemState stateFiveA = new SystemState();
        stateFiveA.clawPosition = clawState.CLOSED;
        stateFiveA.wristPosition = wristState.DOWN;
        stateFiveA.armPosition = armPose.PICKUP;
        stateFiveA.drivePose = new SparkFunOTOS.Pose2D(15.75, 27, 0);

        // State 5B
        SystemState stateFiveB = new SystemState();
        stateFiveB.clawPosition = clawState.CLOSED;
        stateFiveB.wristPosition = wristState.DOWN;
        stateFiveB.armPosition = armPose.BASKET_PREP;
        stateFiveB.drivePose = new SparkFunOTOS.Pose2D(15.75, 27, 0);

        // State Second Line Pre
        SystemState stateSecondPre = new SystemState();
        stateSecondPre.clawPosition = clawState.OPENED;
        stateSecondPre.wristPosition = wristState.DOWN;
        stateSecondPre.armPosition = armPose.REST;
        stateSecondPre.drivePose = new SparkFunOTOS.Pose2D(27, 20, 0);

        // State Second Line
        SystemState stateSecond = new SystemState();
        stateSecond.clawPosition = clawState.OPENED;
        stateSecond.wristPosition = wristState.DOWN;
        stateSecond.armPosition = armPose.REST;
        stateSecond.drivePose = new SparkFunOTOS.Pose2D(27, 24, 0);

        // State Second Line A
        SystemState stateSecondA = new SystemState();
        stateSecondA.clawPosition = clawState.CLOSED;
        stateSecondA.wristPosition = wristState.DOWN;
        stateSecondA.armPosition = armPose.PICKUP;
        stateSecondA.drivePose = new SparkFunOTOS.Pose2D(27, 26, 0);

        // State Second Line B
        SystemState stateSecondB = new SystemState();
        stateSecondB.clawPosition = clawState.CLOSED;
        stateSecondB.wristPosition = wristState.DOWN;
        stateSecondB.armPosition = armPose.BASKET;
        stateSecondB.drivePose = new SparkFunOTOS.Pose2D(27, 26, 0);

        // State 6
        SystemState endState = new SystemState();
        endState.clawPosition = clawState.OPENED;
        endState.wristPosition = wristState.UP;
        endState.armPosition = armPose.ZERO;
        endState.drivePose = new SparkFunOTOS.Pose2D(0, 0, 0);

        // Generate Path
        HashMap<Integer, SystemState> instructions = new HashMap<>();
        instructions.put(0, stateOne); // Arm Up
        instructions.put(1, stateOneA); // Move Forward & Arm Up More
        instructions.put(2, stateTwo); // Move forward
        instructions.put(3, stateThree); // Go to basket
        instructions.put(4, stateFour); // Wrist up
        instructions.put(5, stateFourA); // Claw Open
        instructions.put(6, stateFivePrePre1); // Prep pickup 1
        instructions.put(7, stateFivePre1); // Prep pickup 2
        instructions.put(8, stateFive); // Go to pickup line 1
        instructions.put(9, stateFiveA); // Close Claw
        instructions.put(10, stateFiveB); // Raise Arm
        instructions.put(11, stateOneA); // Move forward
        instructions.put(12, stateTwo); // Move forward
        instructions.put(13, stateThree); // Go to basket
        instructions.put(14, stateFour); // Wrist up
        instructions.put(15, stateFourA); // Claw Open
        instructions.put(16, stateSecondPre); // Go to pickup line 2
        instructions.put(17, stateSecond); // Go to pickup line 1
        instructions.put(18, stateSecondA); // Close Claw
        instructions.put(19, stateSecondB); // Raise Arm
        instructions.put(20, stateOneA); // Move forward
        instructions.put(21, stateTwo); // Move forward
        instructions.put(22, stateThree); // Go to basket
        instructions.put(23, stateFour); // Wrist up
        instructions.put(24, stateFourA); // Claw Open
        instructions.put(25, endState); // End at (0, 0)

        // ACS & DBS & Handler
        ArmSubSystem armControlSubsystem = new ArmSubSystem(armPose.ZERO, cap, spindle, lswitch, LSTop, LSLower, telemetry);
        DriveBaseSubsystem driveBaseSystem = new DriveBaseSubsystem(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, odometry, telemetry);
        autoHandler = new AutonomousHandler(instructions, armControlSubsystem, driveBaseSystem, 0, telemetry);

        LSLower.setPosition(0.1);
        LSTop.setPosition(0.1);

    }

    @Override
    public void loop() {
        autoHandler.periodicFunction();
    }
}
