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

        bot.frictionBasedGrabber.setPosition(110/180);
        sleep(500);
        bot.smplScoringBucket.setPosition(RobotState.BASE.getBucketPosition());
        sleep(1000);

        bot.drive(-1, 0, 0, 0, false);
        sleep(1200);
        bot.drive(0, 0, 0, 1, false);
        sleep(300);
        bot.drive(0, -1, 0, 0, false);
        sleep(300);
        bot.stopDriveTrain();
        while(bot.linearSlide.getCurrentPosition()<RobotState.SMPL_SETUP.getSlidePosition() ){
            bot.proportionalControlMotor(bot.linearSlide, RobotState.SMPL_SETUP.getSlidePosition(), 0.008);
        }
        sleep(500);
        bot.smplScoringBucket.setPosition(RobotState.SMPL_SCORE.getBucketPosition());
        sleep(500);
        while(bot.linearSlide.getCurrentPosition()>RobotState.BASE.getSlidePosition() ){
            bot.proportionalControlMotor(bot.linearSlide, RobotState.BASE.getSlidePosition(), 0.008);
        }
        sleep(500);
        bot.drive(0, 1, 0, 0, false);
        sleep(300);
        bot.drive(0, 0, 1,0 , false);
        sleep(230);
        bot.drive(1, 0, 0, 0, false);
        sleep(3400);
        bot.stopDriveTrain();

    }
}
