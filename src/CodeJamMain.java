public class CodeJamMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BowlSolver solver = new BowlSolver();

        solveFile(solver, "A-small-practice");
        solveFile(solver, "A-large-practice");
    }

    private static void solveFile(BowlSolver solver, final String file) {
        solver.solveFile(file);
        System.out.println("Solved " + file);
    }

}
