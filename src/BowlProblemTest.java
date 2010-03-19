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
                + recipes(1) + "SANDWICH 2 peanuts fruit\n";

        String output = "Case #1: 1\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void mixtureWithSubmixture() {
        String input = "1\n" // 1 test case
                + recipes(2) + "SANDWICH 2 meat FILLING\n" + "FILLING 2 bread cheese\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void submixtureWithTwoSubsubs() {
        String input = "1\n" // 1 test case
                + recipes(4) + "SANDWICH 2 meat FILLING\n"
                + "FILLING 2 BREAD CHEESE\n"
                + "BREAD 2 wheat salt\n" + "CHEESE 2 milk mold\n";

        String output = "Case #1: 3\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void googleSample1() {
        String input = "1\n" // 1 test case
                + recipes(3) + "SOUP 3 STOCK salt water\n"
                + "STOCK 2 chicken VEGETABLES\n"
                + "VEGETABLES 2 celery onions\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void googleSample2() {
        String input = "1\n" // 1 test case
                + recipes(5)
                + "MILKSHAKE 4 milk icecream FLAVOR FRUIT\n"
                + "FRUIT 2 banana berries\n"
                + "FLAVOR 2 SPICES CHOCOLATE\n"
                + "SPICES 2 nutmeg cinnamon\n" + "CHOCOLATE 2 cocoa syrup\n";

        String output = "Case #1: 3\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void constrainedByIngredientMixingPre() {
        String input = "1\n" // comment
                + recipes(4) //
                + "AA 3 AAA AAB AAC\n" //
                + "AAA 2 aaax aaay\n" //
                + "AAB 2 aabx aaby\n" //
                + "AAC 2 aacx aacy\n" //
        ;

        String output = "Case #1: 4\n";
        assertEquals(output, mBowlSolver.solve(input));

    }

    @Test
    public void constrainedByIngredientMixing() {
        String input = "1\n" // comment
                + recipes(9) //
                + "A 3 AA AB\n" //
                + "AA 3 AAA AAB AAC\n" //
                + "AB 3 ABA ABB ABC\n" //
                + "AAA 2 aaax aaay\n" //
                + "AAB 2 aabx aaby\n" //
                + "AAC 2 aacx aacy\n" //
                + "ABA 2 abax abay\n" //
                + "ABB 2 abbx abby\n" //
                + "ABC 2 abcx abcy\n" //
        ;

        String output = "Case #1: 5\n";
        assertEquals(output, mBowlSolver.solve(input));

    }

    private String recipes(final int n) {
        return n + "\n";
    }
}
