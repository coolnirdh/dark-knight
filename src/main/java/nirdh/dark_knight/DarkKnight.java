package nirdh.dark_knight;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * Created by Nirdh on 22-01-2017.
 */
public class DarkKnight extends AdvancedRobot {
    private boolean running;

    public DarkKnight() {
        running = true;
    }

    protected void stopRunning() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            turnGunLeft(5);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        fire(1);
    }
}
