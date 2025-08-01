package org.firstinspires.ftc.teamcode.common.vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Limelight extends VisionSensor {
    private Limelight3A limelight;
    private final double limelightMountAngleDegrees = 38.4;
    private final double limelightLensHeightInches = 13.25;     // Height of Limelight from the ground
    private LLResult result;
    private int farPipeline = 5;
    private int nearPipeline = 7;
    private double sampleLengthInches = 3.5;
    private double sampleWidthInches = 1.5;
    private double sampleAreaSqInches = sampleLengthInches * sampleWidthInches;
    private double minTargetAreaSqInches = sampleAreaSqInches * 0.20;
    private double maxTargetAreaSqInches = sampleAreaSqInches * 1.1;
    private double targetAreaSqInches = 0;
    private double tx, ty = 0;

    public Limelight(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        super(telemetry);
        targetHeightInches = 1.5;
        grabberLeftOffsetInches = 5.25;
        grabberForwardOffsetInches = 15;
        forwardMinInches = 4;
        forwardMaxInches = 15;
        leftMinInches = -10;
        leftMaxInches = 8;
        verticalHeightInches = limelightLensHeightInches - targetHeightInches;
        limelight = hardwareMap.get(Limelight3A.class, name);
        limelight.setPollRateHz(25);

        LLStatus status = limelight.getStatus();

        telemetry.setMsTransmissionInterval(50);

        telemetry.addData("Name", "%s", status.getName());
        telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d", status.getTemp(), status.getCpu(), (int) status.getFps());
        telemetry.addData("Pipeline", "Index: %d, Type: %s", status.getPipelineIndex(), status.getPipelineType());
        updateFilteredResult(250);
    }

    public boolean updateFilteredResult(double maxSenseTimeMS) {
        timer.reset();
        leftDistanceToTargetInches = 0;
        forwardDistanceToTargetInches = 0;
        updateResult();
        while (!resultAcceptable() && (timer.milliseconds() < maxSenseTimeMS)) {
            updateResult();
        }
//        log();
        return (resultAcceptable());
    }

    public void updateResult() {
        result = limelight.getLatestResult();
        if ((result != null) && (result.isValid())) {
            tx = result.getTx();
            ty = result.getTy();
            totalVerticalAngleDegrees = limelightMountAngleDegrees + ty;
            totalHorizontalAngleDegrees = tx;
            forwardDistanceToTargetInches = verticalHeightInches * Math.tan(Math.toRadians(totalVerticalAngleDegrees));
            leftDistanceToTargetInches = forwardDistanceToTargetInches * -Math.tan(Math.toRadians(totalHorizontalAngleDegrees));
        } else {
            forwardDistanceToTargetInches = 0;
            leftDistanceToTargetInches = 0;
        }
    //    log();
    }

    protected boolean resultAcceptable() {
        return (resultIsValid()
                && (isInRange(forwardDistanceToTargetInches, forwardMinInches, forwardMaxInches))
                && (isInRange(leftDistanceToTargetInches, leftMinInches, leftMaxInches)));
    }

    protected boolean resultIsValid() {
        if (result != null) {
            return result.isValid();
        } else {
            return false;
        }
    }

    public void start() {
        setFarPipeline();
        limelight.start();
    }

    public void setFarPipeline() {
        limelight.pipelineSwitch(farPipeline);
    }

    public void setNearPipeline() {
        limelight.pipelineSwitch(nearPipeline);
    }

    public void stop() {
        limelight.stop();
    }

    private void log() {
        telemetry.addData("tx: ", tx);
        telemetry.addData("ty: ", ty);
        telemetry.addData("result valid?: ", resultIsValid());
        telemetry.addData("forward distance: ", forwardDistanceToTargetInches);
        telemetry.addData("left distance: ", leftDistanceToTargetInches);
        telemetry.update();
    }
}
