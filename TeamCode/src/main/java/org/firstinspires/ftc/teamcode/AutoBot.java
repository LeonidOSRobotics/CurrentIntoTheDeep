package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
public class AutoBot extends Robot {

    static final double Wheeldiameter_cm = 10.4;

    public AutoBot() {
    }

    public void moveToPosition(int targetPosition, double power, double timeoutS) {
        // Reset encoders after position movement

        int newLeftFrontTarget = leftFront.getCurrentPosition()+targetPosition;
        int newRightFrontTarget = rightFront.getCurrentPosition()+targetPosition;
        int newLeftBackTarget = leftBack.getCurrentPosition()+targetPosition;
        int newRightBackTarget = rightBack.getCurrentPosition()+targetPosition;


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
