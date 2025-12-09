package org.firstinspires.ftc.teamcode.opmode.auto;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

public abstract class ArtifactAutoOpMode extends AutoOpMode {

    /**
     * This method is called once at the init of the OpMode.
     **/
    @Override
    public void init() {
        FieldLocations.startPose = FieldLocations.startToLaunchPosition;
        Paths.buildSamplePaths(bot.getFollower());
        super.init();

    }

    // Happens once after play button is pressed
    @Override
    public void start() {

    }
}
