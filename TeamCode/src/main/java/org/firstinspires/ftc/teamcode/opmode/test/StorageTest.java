package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Storage;

public class StorageTest extends LinearOpMode {
    private Storage storage;
    public void runOpMode(){
        storage = new Storage(telemetry, hardwareMap);
        waitForStart();
        while (opModeIsActive() && !isStopRequested()){

        }
    }
}
