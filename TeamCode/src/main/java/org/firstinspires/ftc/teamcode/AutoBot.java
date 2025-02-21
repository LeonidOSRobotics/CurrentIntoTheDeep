package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
public class AutoBot extends Robot {

    static final double Wheeldiameter_cm = 10.4;

    public AutoBot() {
    }

    //                          in cm
    public void moveToPosition(double cm, double power, double timeoutS) {
        // Reset encoders after position movement

        int newLeftFrontTarget = leftFront.getCurrentPosition()+getTicksPerCm(cm);
        int newRightFrontTarget = rightFront.getCurrentPosition()+getTicksPerCm(cm);
        int newLeftBackTarget = leftBack.getCurrentPosition()+getTicksPerCm(cm);
        int newRightBackTarget = rightBack.getCurrentPosition()+getTicksPerCm(cm);


        leftFront.setTargetPosition(newLeftFrontTarget);
        leftBack.setTargetPosition(newRightFrontTarget);
        rightFront.setTargetPosition(newLeftBackTarget);
        rightBack.setTargetPosition(newRightBackTarget);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        super.period.reset();
        leftFront.setPower(Math.abs(power));
        leftBack.setPower(Math.abs(power));
        rightFront.setPower(Math.abs(power));
        rightBack.setPower(Math.abs(power));

        while (period.seconds() < timeoutS &&
                leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {

            // Telemetry for debugging
            /*telemetry.addData("Target Positions", "%7D :%7D",
                    newLeftFrontTarget, newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
            telemetry.addData("Current Positions", "%7D :%7D ",
                    leftFront.getCurrentPosition(), rightFront.getCurrentPosition(),
                    leftBack.getCurrentPosition(), rightBack.getCurrentPosition());
            telemetry.addData("Time Elapsed", "", period.seconds());
            telemetry.update();*/
        }

        stopDriveTrain();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    /*
   This function uses the encoders to strafe left or right.
   Negative input for inches results in left strafing.
    */
    public void strafeToPosition(double cm, double speed, double timeoutS){


        int newLeftFrontTarget = leftFront.getCurrentPosition()+getTicksPerCm(cm);
        int newRightFrontTarget = rightFront.getCurrentPosition()-getTicksPerCm(cm);
        int newLeftBackTarget = leftBack.getCurrentPosition()-getTicksPerCm(cm);
        int newRightBackTarget = rightBack.getCurrentPosition()+getTicksPerCm(cm);
        leftFront.setTargetPosition(newLeftFrontTarget);
        leftBack.setTargetPosition(newRightFrontTarget);
        rightFront.setTargetPosition(newLeftBackTarget);
        rightBack.setTargetPosition(newRightBackTarget);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        super.period.reset();
        leftFront.setPower(Math.abs(speed));
        leftBack.setPower(Math.abs(speed));
        rightFront.setPower(Math.abs(speed));
        rightBack.setPower(Math.abs(speed));

        while (period.seconds() < timeoutS &&
                leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {

            // Telemetry for debugging
            /*telemetry.addData("Target Positions", "%7D :%7D",
                    newLeftFrontTarget, newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
            telemetry.addData("Current Positions", "%7D :%7D ",
                    leftFront.getCurrentPosition(), rightFront.getCurrentPosition(),
                    leftBack.getCurrentPosition(), rightBack.getCurrentPosition());
            telemetry.addData("Time Elapsed", "", period.seconds());
            telemetry.update();*/
        }

        stopDriveTrain();
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveArmToPosition(int targetPosition, double power, double timeoutS) {
        intakeArm.setTargetPosition(targetPosition);
        intakeArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intakeArm.setPower(Math.abs(power));

        super.period.reset();
        while (period.seconds() < timeoutS && intakeArm.isBusy()) {

        }

        intakeArm.setPower(0);
        intakeArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public int getTicksPerCm(double cm) {
        double circumference = Math.PI * Wheeldiameter_cm; // Wheel circumference in cm
        return (int) ((Ticksperrev / circumference) * cm); // Ticks per cm
    }

}
