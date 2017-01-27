package nirdh.dark_knight;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import robocode.ScannedRobotEvent;
import robocode.robotinterfaces.peer.IBasicRobotPeer;

import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Nirdh on 22-01-2017.
 */
public class DarkKnightTest {

    @Spy
    private DarkKnight darkKnight;

    @Mock
    private IBasicRobotPeer peer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        darkKnight.setPeer(peer);
    }

    @Test
    public void scansForOtherRobots() throws Exception {
        runDarkKnight();
        verify(darkKnight, atLeastOnce()).turnGunLeft(anyDouble());
    }

    @Test
    public void firesBulletWhenRobotIsScanned() throws Exception {
        darkKnight.onScannedRobot(new ScannedRobotEvent("enemy", 100.0, 0, 100.0, 0, 0, false));
        verify(darkKnight).fire(anyDouble());
    }

    private void runDarkKnight() throws InterruptedException {
        Thread battle = new Thread() {
            @Override
            public void run() {
                darkKnight.run();
            }
        };
        battle.start();
        TimeUnit.SECONDS.sleep(1);
        darkKnight.stopRunning();
    }
}
