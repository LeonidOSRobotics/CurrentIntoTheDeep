package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Reset Arm" , group="Linear OpMode")
public class Test extends LinearOpMode {
    Robot robot = new Robot();  // Using Robot.Java class to interface with the robot's hardware
    int target = 0;

    @Override
    public void runOpMode() {
        // Initialize the robot hardware by calling the init method from the Robot class
        robot.init(hardwareMap);

        // Wait for the user to start the TeleOp mode (e.g., pressing the "play" button in the FTC Driver Station)
        waitForStart();

        // Main control loop that runs while the OpMode is active
        while (opModeIsActive()) {
            // New setup allows for repeat calls of the function
            /* if (gamepad1.b){
                 target = robot.getLinearSlideTicksPerCm(10);
             }
            robot.proportionalControlMotor(robot.linearSlide,target, 0.01);

*/
            telemetry.addData("output", robot.proportionalControlMotor(robot.intakeArm, robot.getAngleTicksPerCm(90),0.05,0.05,0.05));
            /*

            if (gamepad1.dpad_up) {
                robot.proportionalControlMotor(robot.intakeArm, robot.getAngleTicksPerCm(90),0,0,0);
            } else {
                robot.linearSlide.setPower(0);
            }


             if (gamepad1.b) {
                robot.intakeArm.setPower(.4);
            } else if (gamepad1.a) {
                robot.intakeArm.setPower(-0.4);
            } else {
                robot.intakeArm.setPower(0);
            }

             */


            telemetry.addData("Test Angle Value for 10 degrees: ",robot.getAngleTicksPerCm(10));
            telemetry.addData("Test Angle Value for 45 degrees: ",robot.getAngleTicksPerCm(45));
            telemetry.addData("Test Angle Value for 90 degrees: ",robot.getAngleTicksPerCm(90));

            telemetry.addData("EncoderValue",robot.intakeArm.getCurrentPosition());
            telemetry.update();

        }
    }
}
