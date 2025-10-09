package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Launcher extends Component{
    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTag;


    public Launcher(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry);
        visionPortal = hardwareMap.get(VisionPortal.class, "camera");

    }

    void InitAprilTag(){
        
    }




}
