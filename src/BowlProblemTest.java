import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlProblemTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void simplestMixture() {

        String input = "1\n" // 1 test case
                + "1" // 1 recipe
                + "SANDWICH 2 peanuts fruit\n";

        String output = "Case #1: 2\n";

        BowlSolver bowlSolver = new BowlSolver();
        assertEquals(output, bowlSolver.solve(input));
    }
}
