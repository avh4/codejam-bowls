import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class BowlProblemSetSolver {

    public String solve(String input) throws NumberFormatException, IOException {
        BufferedReader r = new BufferedReader(new StringReader(input));
        int cases = Integer.parseInt(r.readLine());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < cases; i++) {

            final String recipeInput = readNextCase(r);
            final int answer = new BowlProblemSolver().solve(recipeInput);

            output.append("Case #" + (i + 1) + ": " + answer + "\n");
        }

        return output.toString();
    }

    private String readNextCase(BufferedReader r) throws IOException {
        String firstLine = r.readLine();
        int lines = Integer.parseInt(firstLine);
        StringBuilder s = new StringBuilder();
        s.append(firstLine + "\n");
        for (int l = 0; l < lines; l++) {
            s.append(r.readLine() + "\n");
        }

        final String recipeInput = s.toString();
        return recipeInput;
    }

    public void solveFile(String file) {
        try {
            String input = readFileAsString(file + ".in");
            String output = solve(input);
            BufferedWriter out = new BufferedWriter(new FileWriter(file + ".out"));
            out.write(output);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String readFileAsString(String filePath) throws java.io.IOException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
        f.read(buffer);
        return new String(buffer);
    }

}
