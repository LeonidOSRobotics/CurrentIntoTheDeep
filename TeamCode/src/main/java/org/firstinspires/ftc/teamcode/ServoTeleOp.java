package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

//This code is to mount the Friction based grabber onto the pusher circle.
//The numbers and comment might have to be adjusted after testing
@TeleOp(name="Install/Remove Friction Grabber" , group="Linear OpMode")
public class ServoTeleOp extends LinearOpMode {

    Robot robot = new Robot();  //Using Robot.Java class
    //TODO: make robot.java

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
/*
            if (gamepad1.x) { //Pre-Mount
                robot.backward_s.setPosition(Servo.MAX_POSITION);
                robot.forward_s.setPosition(Servo.MAX_POSITION);
            }
            else if(gamepad1.b){
                robot.backward_s.setPosition(Servo.MAX_POSITION-0.2);
                robot.forward_s.setPosition(Servo.MAX_POSITION-0.2);
            }
            else if (gamepad1.a) {//Push Block
                robot.backward_s.setPosition(Servo.MIN_POSITION);
                robot.forward_s.setPosition(Servo.MIN_POSITION);
            }
            */
        }
    }
}
