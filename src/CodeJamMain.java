public class CodeJamMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BowlProblemSetSolver solver = new BowlProblemSetSolver();

        solveFile(solver, "A-small-practice");
        solveFile(solver, "A-large-practice");
    }

    private static void solveFile(BowlProblemSetSolver solver, final String file) {
        solver.solveFile(file);
        System.out.println("Solved " + file);
    }

}
