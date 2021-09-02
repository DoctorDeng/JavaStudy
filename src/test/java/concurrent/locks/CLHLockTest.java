package concurrent.locks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CLHLockTest extends LockTest {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[10][0]; // repeat count which you want
    }

    @Test
    public void clhLockTest() throws InterruptedException {
        lockTest(new CLHLock());
    }
}