package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Marsh: TankBot", group="Pushbot")

public class TankBot extends OpMode{ //Declare OpMode members (hardware & variables)
    private DcMotor left_drive = null;
    private DcMotor right_drive = null;
    private DcMotor arm_rotation = null;
    private DcMotor arm_linear = null;
    private Servo claw_left = null;
    private Servo claw_right = null;

    private boolean claw_open = false;
    @Override
    public void init() { // Code to run ONCE when the driver hits INIT
        // Initialize the hardware variables.
        left_drive = hardwareMap.get(DcMotor.class, "leftDrive"); //Map left_drive to hardware configuration "leftDrive"
        right_drive = hardwareMap.get(DcMotor.class, "rightDrive"); //Map right_drive to hardware configuration "rightDrive"
        arm_rotation = hardwareMap.get(DcMotor.class, "rotation");
        arm_linear = hardwareMap.get(DcMotor.class, "linear");
        claw_right = hardwareMap.get(Servo.class, "servo_right");
        claw_left = hardwareMap.get(Servo.class, "servo_left");

        left_drive.setDirection(DcMotor.Direction.REVERSE);
        right_drive.setDirection(DcMotor.Direction.FORWARD);
        arm_rotation.setDirection(DcMotor.Direction.FORWARD);
        arm_linear.setDirection(DcMotor.Direction.FORWARD);
        arm_rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm_linear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        claw_left.setPosition(0.5);
        claw_right.setPosition(0.5);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Begin Initialisation");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }
        //telemetry.addData("Say", "Initialising");
        //telemetry.Line("Say","Running")
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;
        double rotation;
        double linear;
        //boolean claw_open;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y/3*2;
        right = gamepad1.right_stick_y/3*2;
        rotation = gamepad2.left_stick_y/4;
        linear = gamepad2.right_stick_y/3*2;


        telemetry.addData("Claw Left", claw_left.getPosition());
        telemetry.addData("Claw Right", claw_right.getPosition());
        telemetry.addData("Arm Rotation", arm_rotation.getCurrentPosition());
        telemetry.addData("Arm Linear", arm_linear.getCurrentPosition());
        telemetry.update();



        arm_linear.setPower(linear);
        arm_rotation.setPower(rotation);
        left_drive.setPower(left);
        right_drive.setPower(right);

        if (gamepad2.a){
            if (claw_open) {
                claw_right.setPosition(0.75);
                claw_left.setPosition(0.25);
                claw_open = false;
            }
            else {
                claw_right.setPosition(0.25);
                claw_left.setPosition(0.75);
                claw_open = true;
            }
        }
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}