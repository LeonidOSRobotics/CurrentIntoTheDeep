package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
/**
 * MainTeleOp class for controlling the robot during teleoperated mode.
 * This class extends LinearOpMode and uses a Robot object for hardware control.
 */
@TeleOp(name="Main TeleOp", group="Linear OpMode")
public class MainTeleOp extends LinearOpMode {

    // Variables for movement input
    float forward = 0; // Stores the forward/backward input from the gamepad
    float strafe = 0;  // Stores the sideways movement input from the gamepad
    float turnRight = 0; // Stores the right-turn input from the gamepad triggers
    float turnLeft = 0;  // Stores the left-turn input from the gamepad triggers

    double grabberStartPosition = 0.3;
    double grabberTargetPosition = grabberStartPosition + (40.0/180.0);
    double grabberFullRotation = grabberStartPosition + (100.0/180.0);

    private boolean manualControl = false;
    boolean buttonReleased = true;

    // Create an instance of the Robot class for hardware control
    Robot robot = new Robot();  // Using Robot.Java class to interface with the robot's hardware


    //SLide Positions in cm

    RobotState robotState = RobotState.BASE;

    @Override
    public void runOpMode() {
        // Initialize the robot hardware by calling the init method from the Robot class
        robot.init(hardwareMap);

        // Wait for the user to start the TeleOp mode (e.g., pressing the "play" button in the FTC Driver Station)
        waitForStart();

        // Main control loop that runs while the OpMode is active
        while (opModeIsActive()) {

            // Reset the robot's yaw (orientation angle) if the "options" button is pressed
            // This functionality allows recalibration of the IMU during operation to correct orientation errors

            if (gamepad1.options) {
                robot.imu.resetYaw();
            }

            // Read inputs from the gamepad joysticks and triggers:
            forward = -gamepad1.left_stick_y; // Negative because pushing the joystick forward gives a negative value
            strafe = gamepad1.left_stick_x;  // Horizontal movement from the left joystick
            turnRight = gamepad1.right_trigger; // Trigger value for rightward rotation
            turnLeft = gamepad1.left_trigger;  // Trigger value for leftward rotation

            // Retrieve the robot's current heading (yaw) from the IMU sensor in radians
            // Subtracting Math.PI aligns the robot's coordinate system with the desired orientation
            double botHeading = robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - Math.PI;

            // Adjust the movement direction to account for the robot's current rotation:
            // The forward and strafe components are rotated by the negative of the robot's heading
            double rotStrafe = strafe * Math.cos(botHeading) - forward * Math.sin(-botHeading);
            double rotForward = strafe * Math.sin(-botHeading) + forward * Math.cos(botHeading);

            // Pass the adjusted movement values and turn inputs to the robot's drivetrain
            // The drive method in the Robot class handles motor power distribution for movement

            robot.drive(rotForward, rotStrafe, turnRight, turnLeft,gamepad1.left_stick_button);

            //Currently missing SPMN grab and collect
            //State Machine
            if (buttonReleased) {
                if (robotState == RobotState.BASE && gamepad2.dpad_up) {
                    robotState = RobotState.SMPL_SETUP;
                } else if (robotState == RobotState.BASE && gamepad2.dpad_down) {
                    robotState = RobotState.PRE_PICKUP_SMPL;
                } else if (robotState == RobotState.BASE && gamepad2.y) {
                    robotState = RobotState.HIGH_SPMN_SETUP;
                } else if (robotState == RobotState.BASE && gamepad2.a) {
                    robotState = RobotState.SPNM_GRAB;
                } else if (robotState == RobotState.BASE && gamepad2.x) {
                    robotState = RobotState.LOW_SPMN_SETUP;
                } else if (robotState == RobotState.HIGH_SPMN_SETUP) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.HIGH_SPMN_SCORE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.BASE;
                    }
                } else if (robotState == RobotState.SPNM_GRAB) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.SPMN_COLLECT;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.BASE;

                    }
                } else if (robotState == RobotState.SPMN_COLLECT) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.BASE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.SPNM_GRAB;
                    }
                } else if (robotState == RobotState.HIGH_SPMN_SCORE) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.BASE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.HIGH_SPMN_SETUP;
                    }
                } else if (robotState == RobotState.LOW_SPMN_SETUP) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.LOW_SPMN_SCORE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.BASE;
                    }
                } else if (robotState == RobotState.LOW_SPMN_SCORE) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.BASE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.LOW_SPMN_SETUP;
                    }
                } else if (robotState == RobotState.PRE_PICKUP_SMPL) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.PICKUP_SMPL;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.BASE;
                    }

                } else if (robotState == RobotState.PICKUP_SMPL) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.SMPL_LOAD;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.PRE_PICKUP_SMPL;
                    }

                } else if (robotState == RobotState.SMPL_SETUP) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.SMPL_SCORE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.BASE;
                    }
                } else if (robotState == RobotState.SMPL_SCORE) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.BASE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.SMPL_SETUP;
                    }
                } else if (robotState == RobotState.SMPL_LOAD) {
                    if (gamepad2.right_bumper) {
                        robotState = RobotState.BASE;
                    } else if (gamepad2.left_bumper) {
                        robotState = RobotState.PICKUP_SMPL;
                    }
                }
            }


            if (gamepad2.dpad_right) {
                robot.frictionBasedGrabber.setPosition(grabberTargetPosition);
            } else if(gamepad2.dpad_left){
                robot.frictionBasedGrabber.setPosition(grabberFullRotation);
            }




            if (gamepad2.right_bumper || gamepad2.left_bumper) {
                buttonReleased = false;
            } else {
                buttonReleased = true;
            }
            telemetry.addData("State", robotState.getName());
            telemetry.addData("Position", robotState.getSlidePosition());
            telemetry.update();


            if (gamepad1.back) {
                manualControl = !manualControl;
            }

            if (manualControl) {

                if (gamepad1.dpad_up) {
                    robot.linearSlide.setPower(.8);
                } else if (gamepad1.dpad_down) {
                    robot.linearSlide.setPower(-0.8);
                } else {
                    robot.linearSlide.setPower(0);
                }

                if (gamepad1.dpad_left) {
                    robot.intakeArm.setPower(.4);
                } else if (gamepad1.dpad_right) {
                    robot.intakeArm.setPower(-0.4);
                } else {
                    robot.intakeArm.setPower(0);
                }
            } else {

                robot.proportionalControlMotor(robot.linearSlide, robotState.getSlidePosition(), 0.001,0.1,0.008);
                robot.smplScoringBucket.setPosition(robotState.getBucketPosition());
                robot.proportionalControlMotor(robot.intakeArm, robotState.getArmPosition(), 0.001,0.1,0.008);
            }

            if (gamepad2.left_trigger>0) {
                robot.forward_s.setPower(0.8);
                robot.backward_s.setPower(-0.8);
            } else if (gamepad2.right_trigger>0) {
                robot.forward_s.setPower(-0.8);
                robot.backward_s.setPower(0.8);
            } else {
                robot.forward_s.setPower(0);
                robot.backward_s.setPower(0);
            }



           /* if (gamepad1.b) {
                robot.intakeArm.setPower(.4);
            } else if (gamepad1.a) {
                robot.intakeArm.setPower(-0.4);
            } else {
                robot.intakeArm.setPower(0);
            }*/
            /*
            //  Linear slide Overide
            if (gamepad1.dpad_up) {
                robot.linearSlide.setPower(.8);
            } else if (gamepad1.dpad_down) {
                robot.linearSlide.setPower(-0.8);
            } else {
                robot.linearSlide.setPower(0);
            }*/


/*
            if (gamepad1.x) {
                robot.bucket.setPosition(RobotState.SMPL_SCORE.getBucketPosition()); // Rotate bucket to a specific position when button X is pressed
            } else {
                robot.bucket.setPosition(RobotState.BASE.getBucketPosition()); // Default position
            }

*/

        }
    }

}
