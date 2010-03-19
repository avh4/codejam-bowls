import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BowlProblemTest {

    private BowlSolver mBowlSolver;

    @Before
    public void setUp() throws Exception {
        mBowlSolver = new BowlSolver();
    }

    @Test
    public void simplestMixture() {
        String input = "1\n" // 1 test case
                + "1" // 1 recipe
                + "SANDWICH 2 peanuts fruit\n";

        String output = "Case #1: 1\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void mixtureWithSubmixture() {
        String input = "1\n" // 1 test case
                + "2" // 2 recipes
                + "SANDWICH 2 meat FILLING\n" + "FILLING 2 bread cheese\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void submixtureWithTwoSubsubs() {
        String input = "1\n" // 1 test case
                + "4" // 2 recipes
                + "SANDWICH 2 meat FILLING\n" + "FILLING 2 BREAD CHEESE\n"
                + "BREAD 2 wheat salt\n"
                + "CHEESE 2 milk mold\n";

        String output = "Case #1: 3\n";
        assertEquals(output, mBowlSolver.solve(input));
    }
}
