package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
public class Robot {

    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;

    DcMotor linearSlide = null;
    DcMotor intakeArm = null;


    CRServo forward_s = null;
    CRServo backward_s = null;

    Servo smplScoringBucket = null;
    Servo frictionBasedGrabber = null;

    IMU imu = null;

    static final double Ticksperrev = 537.7;
    static final double PULLEY_CIRCUMFERENCE_CM = 14.2; // Circumference of the wheel in cm
    static final int NUMBER_OF_WHEELS = 3; // Three wheels in the system

   // private double kP = 0.01; // Proportional control constant (switched out for a constant in method

    /* local OpMode members.*/
    HardwareMap hwMap = null; //hardware map
    protected ElapsedTime period = new ElapsedTime();
    public ElapsedTime getPeriod() {
        return period;
    }
    /* Constructor */
    public Robot() {
    }


    public void init(HardwareMap ahwMap) {
// Save reference to hardware map
        hwMap = ahwMap;

        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");

        linearSlide = hwMap.get(DcMotor.class, "slide");
        intakeArm = hwMap.get(DcMotor.class, "intakeArm");

        forward_s = hwMap.get(CRServo.class, "forward_s");
        backward_s = hwMap.get(CRServo.class, "backward_s");

        smplScoringBucket = hwMap.get(Servo.class, "bucket");
        frictionBasedGrabber = hwMap.get(Servo.class, "intake");

        imu = hwMap.get(IMU.class, "imu");

        // Retrieve the IMU from the hardware map
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Temporary Directions for drive train, change after testing if needed.
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        linearSlide.setDirection(DcMotor.Direction.REVERSE);
        intakeArm.setDirection(DcMotor.Direction.REVERSE);

        forward_s.setDirection(CRServo.Direction.FORWARD);
        backward_s.setDirection(CRServo.Direction.FORWARD);

        smplScoringBucket.setDirection(Servo.Direction.FORWARD);
        frictionBasedGrabber.setDirection(Servo.Direction.FORWARD);


        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlide.setDirection(DcMotor.Direction.REVERSE);

        // Set up the slide motor for encoder-based control
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frictionBasedGrabber.setPosition(.8);

        // set all motors to zero power
        stopDriveTrain();

        linearSlide.setPower(0);
        intakeArm.setPower(0);
        imu.resetYaw();
    }

    public void proportionalControlMotor(DcMotor motor, int targetPosition, double kP) {
        double error =targetPosition-motor.getCurrentPosition();

        // Calculate the proportional output
        double output = kP * error;

        // Set motor power with proportional control
        if (motor.getCurrentPosition() < targetPosition+5 && motor.getCurrentPosition() > targetPosition-5)
        { motor.setPower(0);
        }else{
            motor.setPower(output);
        }

    }

    public void drive(double forward, double strafe, double rotateLeft, double rotateRight) {
        double y = forward;
        double x = strafe;
        double rotate = rotateRight - rotateLeft;
        //Slows speed of wheels
        double dampening = .6;

        //Calculating the power for the wheels
        double frontLeftPower = (y + x + rotate) * dampening;
        double backLeftPower = (y - x + rotate) * dampening;
        double frontRightPower = (y - x - rotate) * dampening;
        double backRightPower = (y + x - rotate) * dampening;

        //Set Power
        leftFront.setPower(frontLeftPower);
        rightFront.setPower(frontRightPower);
        leftBack.setPower(backLeftPower);
        rightBack.setPower(backRightPower);
    }



    public void stopDriveTrain() {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

    public int getLinearSlideTicksPerCm(double cm) {
        return (int) ((Ticksperrev / PULLEY_CIRCUMFERENCE_CM) * NUMBER_OF_WHEELS* cm); // New formula considering multiple wheels
    }





}
