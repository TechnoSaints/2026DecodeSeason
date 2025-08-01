package org.firstinspires.ftc.teamcode.common.vision;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.SortOrder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.RotatedRect;

import java.util.Collections;
import java.util.List;

public class Webcam extends VisionSensor {
    private final ColorBlobLocatorProcessor colorLocatorBlue, colorLocatorYellow, colorLocatorRed;
    private final VisionPortal portal;
    private List<ColorBlobLocatorProcessor.Blob> blobs;
    private RotatedRect box;
    private final int resolutionWidth = 320;
    private final int widthCenter = (int) (resolutionWidth / 2.0);
    private int pixelsForwardOfCenter;
    private final int resolutionHeight = 240;
    private final int heightCenter = (int) (resolutionHeight / 2.0);
    private int pixelsLeftOfCenter;
    double heightPixelsPerInch = 25;
    double widthPixelsPerInch = 25;

    ColorBlobLocatorProcessor.BlobFilter areaFilter, densityFilter, ratioFilter;

    ColorBlobLocatorProcessor.BlobSort areaSort, densitySort, ratioSort;

    public Webcam(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        grabberLeftOffsetInches = 0;
        grabberForwardOffsetInches = 3.5;
        forwardMinInches = -5;
        forwardMaxInches = 5;
        leftMinInches = -5;
        leftMaxInches = 5;

        colorLocatorBlue = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.BLUE)         // use a predefined color match
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)    // exclude blobs inside blobs
                .setRoi(ImageRegion.asUnityCenterCoordinates(-1.0, 1.0, 1.0, -1.0))
                .setDrawContours(true)                        // Show contours on the Stream Preview
                .setBlurSize(5)                               // Smooth the transitions between different colors in image
                .setErodeSize(3)
                .setDilateSize(3)
                .build();

        areaFilter = new ColorBlobLocatorProcessor.BlobFilter(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 100, 20000);
        densityFilter = new ColorBlobLocatorProcessor.BlobFilter(ColorBlobLocatorProcessor.BlobCriteria.BY_DENSITY, 0.5, 1.0);
        ratioFilter = new ColorBlobLocatorProcessor.BlobFilter(ColorBlobLocatorProcessor.BlobCriteria.BY_ASPECT_RATIO, 1.0, 10.0);

        colorLocatorBlue.addFilter(areaFilter);
        colorLocatorBlue.addFilter(densityFilter);
        colorLocatorBlue.addFilter(ratioFilter);

//        areaSort = new ColorBlobLocatorProcessor.BlobSort(ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, SortOrder.ASCENDING);
//        densitySort = new ColorBlobLocatorProcessor.BlobSort(ColorBlobLocatorProcessor.BlobCriteria.BY_DENSITY, SortOrder.ASCENDING);
//        ratioSort = new ColorBlobLocatorProcessor.BlobSort(ColorBlobLocatorProcessor.BlobCriteria.BY_ASPECT_RATIO, SortOrder.ASCENDING);

//        colorLocator.setSort(areaSort);
//        colorLocator.setSort(densitySort);
//        colorLocator.setSort(ratioSort);

        colorLocatorYellow = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.YELLOW)         // use a predefined color match
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)    // exclude blobs inside blobs
                .setRoi(ImageRegion.asUnityCenterCoordinates(-1.0, 1.0, 1.0, -1.0))
                .setDrawContours(true)                        // Show contours on the Stream Preview
                .setBlurSize(5)                               // Smooth the transitions between different colors in image
                .setErodeSize(3)
                .setDilateSize(3)
                .build();

        colorLocatorYellow.addFilter(areaFilter);
        colorLocatorYellow.addFilter(densityFilter);
        colorLocatorYellow.addFilter(ratioFilter);


        colorLocatorRed = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.RED)         // use a predefined color match
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)    // exclude blobs inside blobs
                .setRoi(ImageRegion.asUnityCenterCoordinates(-1.0, 1.0, 1.0, -1.0))
                .setDrawContours(true)                        // Show contours on the Stream Preview
                .setBlurSize(5)                               // Smooth the transitions between different colors in image
                .setErodeSize(3)
                .setDilateSize(3)
                .build();

        colorLocatorRed.addFilter(areaFilter);
        colorLocatorRed.addFilter(densityFilter);
        colorLocatorRed.addFilter(ratioFilter);

        portal = new VisionPortal.Builder()
                .addProcessor(colorLocatorBlue)
                .addProcessor(colorLocatorYellow)
                .addProcessor(colorLocatorRed)
                .setCameraResolution(new Size(resolutionWidth, resolutionHeight))
                .setCamera(hardwareMap.get(WebcamName.class, "armcam"))
                .build();

        inactivateAll();
    }

    public void activateBlue()
    {
        inactivateAll();
        portal.setProcessorEnabled(colorLocatorBlue, true);
    }
    public void activateYellow()
    {
        inactivateAll();
        portal.setProcessorEnabled(colorLocatorYellow, true);
    }
    public void activateRed()
    {
        inactivateAll();
        portal.setProcessorEnabled(colorLocatorYellow, true);
    }

    public void inactivateAll()
    {
        portal.setProcessorEnabled(colorLocatorBlue, false);
        portal.setProcessorEnabled(colorLocatorYellow, false);
        portal.setProcessorEnabled(colorLocatorRed, false);
    }
    public RotatedRect getBestBox() {
        updateFilteredResult(100);
        return box;
    }

    protected boolean updateFilteredResult(double maxSenseTimeMS) {
        timer.reset();
        updateResult();
        while (((!resultIsValid()))
                && (timer.milliseconds() < maxSenseTimeMS)) {
            updateResult();
        }
        if (resultIsValid()) {
            Collections.sort(blobs, new BlobComparator());
            box = blobs.get(0).getBoxFit();
            pixelsForwardOfCenter = heightCenter - (int) box.center.y;
            pixelsLeftOfCenter = widthCenter - (int) box.center.x;
            forwardDistanceToTargetInches = pixelsForwardOfCenter / heightPixelsPerInch;
            leftDistanceToTargetInches = pixelsLeftOfCenter / widthPixelsPerInch;
            telemetry.addData("box center x: ", box.center.x);
            telemetry.addData("box center y: ", box.center.y);
            telemetry.addData("box angle: ", box.angle);

            telemetry.addData("pixels forward of center: ", pixelsForwardOfCenter);
            telemetry.addData("pixels left of center: ", pixelsLeftOfCenter);
            telemetry.update();

        }
        return (resultIsValid());
    }

    protected void updateResult() {
//        blobs = colorLocator.getBlobs();
    }
    protected boolean resultIsValid() {
        return (!blobs.isEmpty());
    }

    protected int distanceFromCenter(RotatedRect box) {
        return (Math.abs((int)(box.center.x) - widthCenter) + Math.abs((int)(box.center.y) - heightCenter));
    }

   private class BlobComparator implements java.util.Comparator<ColorBlobLocatorProcessor.Blob> {
        @Override
        public int compare(ColorBlobLocatorProcessor.Blob a, ColorBlobLocatorProcessor.Blob b) {
            return (distanceFromCenter(a.getBoxFit()) - distanceFromCenter(b.getBoxFit()));
        }
    }
}




