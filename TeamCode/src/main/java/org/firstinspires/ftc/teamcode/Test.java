package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Test TeleOp" , group="Linear OpMode")
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
             if (gamepad1.b){
                 target = 1000;
             }
            robot.proportionalControlMotor(robot.intakeArm,1000);

        }
    }
}
