package concurrent.locks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MCSLockTest extends LockTest {


    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[100][0];
    }

    @Test
    public void mcsLockTest() throws InterruptedException {
        lockTest(new MCSLock());
    }
}