import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BowlProblemTest {

    private BowlProblemSetSolver mBowlSolver;

    @Before
    public void setUp() throws Exception {
        mBowlSolver = new BowlProblemSetSolver();
    }

    @Test
    public void simplestMixture() throws NumberFormatException, Exception {
        String input = "1\n" // 1 test case
                + recipes(1) + "SANDWICH 2 peanuts fruit\n";

        String output = "Case #1: 1\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void twoTestCases() throws NumberFormatException, IOException {
        String input = "2\n" // 1 test case
                + recipes(1) + "SANDWICH 2 peanuts fruit\n"
                + recipes(1)
                + "HANDWICH 2 peanuts fruit\n";

        String output = "Case #1: 1\n" + "Case #2: 1\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    /**
     * The solver must reset its recipe map and bowl count cache between each
     * recipe, otherwise it will give the wrong results if a subsequent test
     * case has a solution which is smaller than a previous test case.
     */
    @Test
    public void shouldResetBetweenCases() throws NumberFormatException, IOException {
        String input = "2\n" //
                + recipes(3) //
                + "SOUP 3 STOCK salt water\n"
                + "STOCK 2 chicken VEGETABLES\n"
                + "VEGETABLES 2 celery onions\n" //
                + "1\n" + "A 2 aa ab\n";

        String output = "Case #1: 2\n" + "Case #2: 1\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    private static String readFileAsString(String filePath) throws IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
        f.read(buffer);
        return new String(buffer);
    }

    @Test
    public void readWriteFromFiles() throws IOException {
        try {
            new File("A-sample.out").delete();
            assertFalse("Case #1: 2\nCase #2: 3\n".equals(readFileAsString("A-sample.out")));
        } catch (FileNotFoundException e) {
            // Okay, we don't want the file to be there
        }
        mBowlSolver.solveFile("A-sample");
        assertEquals("Case #1: 2\nCase #2: 3\n", readFileAsString("A-sample.out"));
        assertTrue("Case #1: 2\nCase #2: 3\n".equals(readFileAsString("A-sample.out")));
    }

    @Test
    public void mixtureWithSubmixture() throws NumberFormatException, IOException {
        String input = "1\n" // 1 test case
                + recipes(2) + "SANDWICH 2 meat FILLING\n" + "FILLING 2 bread cheese\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void submixtureWithTwoSubsubs() throws NumberFormatException, IOException {
        String input = "1\n" // 1 test case
                + recipes(4) + "SANDWICH 2 meat FILLING\n"
                + "FILLING 2 BREAD CHEESE\n"
                + "BREAD 2 wheat salt\n" + "CHEESE 2 milk mold\n";

        String output = "Case #1: 3\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void googleSample1() throws NumberFormatException, IOException {
        String input = "1\n" // 1 test case
                + recipes(3) + "SOUP 3 STOCK salt water\n"
                + "STOCK 2 chicken VEGETABLES\n"
                + "VEGETABLES 2 celery onions\n";

        String output = "Case #1: 2\n";
        assertEquals(output, mBowlSolver.solve(input));
    }

    @Test
    public void googleSample2() throws NumberFormatException, IOException {
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
    public void constrainedByIngredientMixingPre() throws NumberFormatException, IOException {
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
    public void constrainedByIngredientMixing() throws NumberFormatException, IOException {
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
