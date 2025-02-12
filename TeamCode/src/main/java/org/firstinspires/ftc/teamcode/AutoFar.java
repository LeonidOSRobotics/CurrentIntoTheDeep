package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Far From Net" , group="Autonomous", preselectTeleOp="Main TeleOp")
public class AutoFar extends LinearOpMode {
    AutoBot bot = new AutoBot();
    @Override



    public void runOpMode() {

        bot.init(hardwareMap);

        waitForStart();


        //bot.moveToPosition(1, 0.6);



        sleep(5000);
        bot.drive(-1, 0, 0, 0);
        sleep(1800);
        bot.stopDriveTrain();
        sleep(150);
        bot.drive(0, -1, 0, 0);
        sleep(200);
        bot.stopDriveTrain();
        sleep(150);
        bot.drive(1, 0, 0, 0);
        sleep(2500);
        bot.stopDriveTrain();

    }
}

