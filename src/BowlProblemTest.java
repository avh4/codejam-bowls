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
                + "1\n" // 1 recipe
                + "SANDWICH 2 peanuts fruit\n";

        String output = "Case #1: 1\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void mixtureWithSubmixture() {
        String input = "1\n" // 1 test case
                + "2\n" // 2 recipes
                + "SANDWICH 2 meat FILLING\n" + "FILLING 2 bread cheese\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void submixtureWithTwoSubsubs() {
        String input = "1\n" // 1 test case
                + "4\n" // 2 recipes
                + "SANDWICH 2 meat FILLING\n" + "FILLING 2 BREAD CHEESE\n"
                + "BREAD 2 wheat salt\n"
                + "CHEESE 2 milk mold\n";

        String output = "Case #1: 3\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void googleSample1() {
        String input = "1\n" // 1 test case
                + "3\n" // 2 recipes
                + "SOUP 3 STOCK salt water\n"
                + "STOCK 2 chicken VEGETABLES\n"
                + "VEGETABLES 2 celery onions\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }
}
