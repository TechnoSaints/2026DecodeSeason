package org.firstinspires.ftc.teamcode.common.vision;

import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Component;
import org.opencv.core.RotatedRect;

abstract public class VisionSensor extends Component {
    protected double targetHeightInches, verticalHeightInches;          // Distance from the ground for calculation
    protected double totalVerticalAngleDegrees, totalHorizontalAngleDegrees;
    protected double forwardDistanceToTargetInches, leftDistanceToTargetInches, leftRotationToTargetDegrees;
    protected double forwardMinInches;
    protected double forwardMaxInches;
    protected double leftMaxInches;
    protected double leftMinInches;
    protected final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    protected double grabberLeftOffsetInches;
    protected double grabberForwardOffsetInches;

    public VisionSensor(Telemetry telemetry) {
        super(telemetry);
        //start();
        timer.reset();
    }

    abstract protected boolean updateFilteredResult(double maxSenseTimeMS);

    protected boolean isInRange(double value, double lower, double upper) {
        return ((value > lower) && (value < upper));
    }

    public double getForwardDistanceToTargetInches() {
        return forwardDistanceToTargetInches;
    }

    public double getLeftDistanceToTargetInches() {
        return leftDistanceToTargetInches;
    }

    public double getLeftRotationToTargetDegrees() {
        return leftRotationToTargetDegrees;
    }

    abstract protected void updateResult();

    abstract protected boolean resultIsValid();

    public Pose getOffsetPose(Pose currentPose) {
        Pose newPose = new Pose();

        newPose.setX(currentPose.getX() + forwardDistanceToTargetInches - grabberForwardOffsetInches);
        telemetry.addData("Forward offset - forwardtarget: ", forwardDistanceToTargetInches - grabberForwardOffsetInches);
        newPose.setY(currentPose.getY() + leftDistanceToTargetInches - grabberLeftOffsetInches);
        telemetry.addData("left offset - lefttarget: ", leftDistanceToTargetInches - grabberLeftOffsetInches);
        newPose.setHeading(Math.toDegrees(currentPose.getHeading()) + leftRotationToTargetDegrees);

        return (newPose);
    }
}




