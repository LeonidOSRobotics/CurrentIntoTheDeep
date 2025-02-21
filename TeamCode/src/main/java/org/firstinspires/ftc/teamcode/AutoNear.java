package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Near Net", group="Autonomous", preselectTeleOp="Main TeleOp")
public class AutoNear extends LinearOpMode {
    AutoBot bot = new AutoBot();
    @Override
    public void runOpMode() {

        bot.init(hardwareMap);

        waitForStart();

        bot.drive(-1, 0, 0, 0, false);
        sleep(1000);
        bot.drive(0, -1, 0, 0, false);
        sleep(500);
        bot.drive(1, 0, 0, 0, false);
        sleep(2000);
        bot.drive(0, 1, 0, 0, false);
        sleep(700);
        bot.stopDriveTrain();

    }
}
