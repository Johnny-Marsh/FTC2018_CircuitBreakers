package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Jade: TankBot", group="Pushbot")

public class TankBotJade extends OpMode{ //Declare OpMode members (hardware & variables)
    private DcMotor left_drive = null;
    private DcMotor right_drive = null;
    private DcMotor arm_rotation = null;
    private DcMotor arm_linear = null;
    private Servo claw_left = null;
    private Servo claw_right = null;


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

        left = 0.0;
        right = 0.0;


        // IF trigger pulled
        if(gamepad1.right_trigger > 0){

            left = 0.50;
            right = 0.50;

        };

        drive(left, right);


        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y/3*2;
        right = gamepad1.right_stick_y/3*2;
        rotation = gamepad2.left_stick_y/4;
        linear = gamepad2.right_stick_y/3*2;


        telemetry.addData("Claw Left", claw_left.getPosition());
        telemetry.addData("Claw Right", claw_right.getPosition());
        telemetry.update();



        arm_linear.setPower(linear);
        arm_rotation.setPower(rotation);

        if (gamepad2.a){
            claw_right.setPosition(1);
            claw_left.setPosition(0);
        }
        if (gamepad2.b) {
            claw_right.setPosition(0);
            claw_left.setPosition(1);

        }

    }
    /*
    * This function drives the bot
    */
    public void drive(double leftDrive, double rightDrive){
        left_drive.setPower(leftDrive);

        right_drive.setPower(rightDrive);
    };
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}