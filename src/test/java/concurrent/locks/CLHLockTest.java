package concurrent.locks;

import org.junit.Test;

public class CLHLockTest extends LockTest {

    @Test
    public void clhLockTest() throws InterruptedException {
        lockTest(new CLHLock());
    }
}