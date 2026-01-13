package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda6000DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LauncherData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.setPoints.LauncherSettings;

@Config
public class blackWheel extends Component {
    private final DcMotorEx pusher;
    private MotorData motorData = new GoBilda312DcMotorData();

    private double onPower = .3;
    private final int maxTicksPerSecond = motorData.maxTicksPerSec;

    public blackWheel(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        pusher = hardwareMap.get(DcMotorEx.class, "pusher");
        pusher.setDirection(DcMotorSimple.Direction.FORWARD);
        pusher.setTargetPositionTolerance(5);
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setRunMode(DcMotor.RunMode mode) {
        pusher.setMode(mode);
    }


    public void forward()
    {
        pusher.setPower(onPower);
    }

    public void runForwardInTicks(int ticks)
    {
        pusher.setTargetPosition(ticks);
        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        pusher.setPower(onPower);
    }


    public void reverse()
    {
        pusher.setPower(-onPower);
    }

    public void stop()
    {
        pusher.setPower(0.0);
    }



    public void log() {
        telemetry.addData("maxTicksPerSecond:  ", maxTicksPerSecond);
        telemetry.addData("Current Power:  ", onPower);

        telemetry.update();
    }

}