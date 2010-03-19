public class BowlSolver {

    public String solve(String input) {
        int recipes = Math.min(3, input.split("\n").length - 1);
        return "Case #1: " + recipes + "\n";
    }

}
