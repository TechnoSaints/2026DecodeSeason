package org.firstinspires.ftc.teamcode.vision.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@TeleOp(name = "OpenCVExampleOldWay", group = "Vision")
@Disabled
public class OpenCVExampleOldWay extends OpMode {
    OpenCvWebcam webcam = null;

    @Override
    public void init() {
        Servo led = hardwareMap.get(Servo.class, "headlight");
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        webcam.setPipeline(new testPipeline());

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    @Override
    public void loop() {
    }

    class testPipeline extends OpenCvPipeline {
        Mat Hsv = new Mat();
        Mat leftCrop;
        Mat rightCrop;
        double leftAvgFin;
        double rightAvgFin;
        Mat output = new Mat();
        Scalar rectColor = new Scalar(255.0, 255.0, 255.0);

        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, Hsv, Imgproc.COLOR_RGB2HSV);
            telemetry.addLine("Pipeline Running");
            Rect leftRect = new Rect(1,1, 319,479);
            Rect rightRect = new Rect(320,1,319,479);

            input.copyTo(output);
            Imgproc.rectangle(output, leftRect, rectColor, 2);
            Imgproc.rectangle(output, rightRect, rectColor, 2);

            return (output);
        }
    }

}