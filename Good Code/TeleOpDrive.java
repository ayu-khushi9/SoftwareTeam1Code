package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOp_Omni")
public class TeleOpDrive extends LinearOpMode {

    private DcMotor motorFrontLeft = null;
    private DcMotor motorBackLeft = null;
    private DcMotor motorFrontRight = null;
    private DcMotor motorBackRight = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontL");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackL");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontR");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackR");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //
            /* boolean dpad_up = gamepad1.dpad_up;
            boolean dpad_down = gamepad1.dpad_down;
            boolean dpad_left = gamepad1.dpad_left;
            boolean dpad_right = gamepad1.dpad_right;
            boolean bumper_left = gamepad1.left_bumper;
            boolean bumper_right = gamepad1.right_bumper;
            */
            double frontLeftPower = 0;
            double frontRightPower = 0;
            double backLeftPower = 0;
            double backRightPower = 0;

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            // double lt = gamepad1.left_trigger;
            // double rt = gamepad1.right_trigger;
            if (y > 0.2 || y < -0.2) {
                frontLeftPower = (y) / 2;
                backRightPower = (y) / 2;
                if (x == 1 || x == -1) {
                    frontLeftPower = (y) / 1.5;
                    backRightPower = (y) / 1.5;
                }
                if (x < .25 || x > -.25) {
                    frontRightPower = 0;
                    backLeftPower = 0;
                }
            } else if(Math.abs(y)>Math.abs(x)) {
                frontLeftPower = (y) / 2;
                backRightPower = (y) / 2;

                backLeftPower = 0;
                frontRightPower = 0;
            }
            if (x > 0.25 || x < -0.25) {
                backLeftPower = (x) / 1.8;
                frontRightPower = (x) / 1.8;
                if (x == 1 || x == -1) {
                    backLeftPower = (x) / 1.5;
                    frontRightPower = (x) / 1.5;
                }
                if (y < .25 || y > -.25) {
                    frontLeftPower = 0;
                    backRightPower = 0;
                }
            } else if (Math.abs(x)>Math.abs(y)) {
                backLeftPower = (x) / 2;
                frontRightPower = (x) / 2;

                frontLeftPower = 0;
                backRightPower = 0;
            }
            //Right Joystick for turning
            if (rx > 0.05 || rx < -0.05) {
                frontLeftPower = (rx) / 3;
                backLeftPower = (-rx) / 2.5;
                frontRightPower = (rx) / 2.5;
                backRightPower = (-rx) / 3;
                if (rx == 1 || rx == -1) {
                    frontLeftPower = (rx) / 2.5;
                    backLeftPower = (-rx) / 1.5;
                    frontRightPower = (rx) / 1.5;
                    backRightPower = (-rx) / 2.5;
                }
            }


            //sets powers of motors
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }
}