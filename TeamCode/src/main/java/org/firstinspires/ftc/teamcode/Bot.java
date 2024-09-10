package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Bot {

    //OpMode Declaration
    private LinearOpMode opMode;

    //Motor Declaration
    private DcMotor leftMotorFront;
    private DcMotor rightMotorFront;
    private DcMotor leftMotorBack;
    private DcMotor rightMotorBack;

    //Statistics for measurements
    static final double WHEEL_DIAMETER_INCHES = 1; // For circumference / distance measurements


    private ElapsedTime runtime = new ElapsedTime();

    /**
     * Constructor for Bot object
     * @param opMode
     */
    public Bot(LinearOpMode opMode) {
        this.opMode = opMode;
        init(opMode.hardwareMap);
    }

    /**
     * Initialize hardware mapping for robot
     * @param map
     */
    public void init(HardwareMap map){

        //Connecting declared motors to classes of DcMotors and respected names
        leftMotorFront = map.get(DcMotor.class, 'left_front');
        leftMotorBack = map.get(DcMotor.class, 'left-back');
        rightMotorFront = map.get(DcMotor.class, 'right-front');
        rightMotorBack = map.get(DcMotor.class, 'right-back');

        //Set RunModes for Encoder Usage
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set Direction of each Motors
        // switch REVERSE and FORWARD if controls are opposite
        // This is set for Mechanum drive
        leftMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /**
     * Set drive train power for mechanum drive
     * @param frontLeftPower
     * @param backLeftPower
     * @param frontRightPower
     * @param backRightPower
     */
    public setDriveTrain(
            double frontLeftPower, double backLeftPower,
            double frontRightPower, double backRightPower
    ) {
        leftMotorFront.setPower(frontLeftPower);
        leftMotorBack.setPower(backLeftPower);
        rightMotorFront.setPower(frontRightPower);
        rightMotorBack.setPower(backRightPower);
    }


}