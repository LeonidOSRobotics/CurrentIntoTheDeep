package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="IntakeArmTest", group="Autonomous", preselectTeleOp="Main TeleOp")
public class newAutoTest extends LinearOpMode {
    AutoBot bot = new AutoBot();
    @Override

public void runOpMode () {

        bot.moveToPosition(15, 0.5, 3.0);
        while(bot.intakeArm.getCurrentPosition() < 500) {
            bot.proportionalControlMotor(bot.intakeArm, 500, 0.002);
        }
        bot.intakeArm.setPower(0);


    }
}


