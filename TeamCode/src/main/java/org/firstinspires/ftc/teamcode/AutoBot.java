package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
public class AutoBot extends Robot {

    static final double Wheeldiameter_cm = 10.4;

    public AutoBot() {
    }

    public void moveToPosition(int targetPosition, double power) {
        // Reset encoders after position movement
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(targetPosition);
        leftBack.setTargetPosition(targetPosition);
        rightFront.setTargetPosition(targetPosition);
        rightBack.setTargetPosition(targetPosition);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        while (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) {
            // Wait until the motors reach the target position
        }

        stopDriveTrain();


    }

    public int getTicksPerCm(double cm) {
        double circumference = Math.PI * Wheeldiameter_cm; // Wheel circumference in cm
        return (int) ((Ticksperrev / circumference) * cm); // Ticks per cm
    }



}
