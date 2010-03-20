import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlSolver {

    Map<String, List<String>> mRecipeToSubmixtures = new HashMap<String, List<String>>();

    Map<String, Integer> mBowlsRequired = new HashMap<String, Integer>();

    public String solve(String input) throws NumberFormatException, IOException {
        BufferedReader r = new BufferedReader(new StringReader(input));
        int cases = Integer.parseInt(r.readLine());

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < cases; i++) {

            final String recipeInput = readNextCase(r);
            final int answer = solveCase(recipeInput);

            output.append("Case #" + (i + 1) + ": " + answer + "\n");
        }

        return output.toString();
    }

    private int solveCase(final String recipeInput) {
        List<String> recipes = new ArrayList<String>(Arrays.asList(recipeInput.split("\n")));
        recipes.remove(0);
        for (String recipe : recipes) {
            final String name = parseName(recipe);
            List<String> ingredients = parseSubmixtures(recipe);
            mRecipeToSubmixtures.put(name, ingredients);
        }
        int bowlsForRecipeList = -1;
        for (String recipe : mRecipeToSubmixtures.keySet()) {
            bowlsForRecipeList = Math.max(bowlsForRecipeList, numberOfBowlsForRecipe(recipe));
        }
        return bowlsForRecipeList;
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

    private String parseName(String recipe) {
        return recipe.split(" ")[0];
    }

    private List<String> parseSubmixtures(String recipe) {
        String[] ingredients = recipe.split(" ");
        List<String> submixtures = new ArrayList<String>();
        for (int i = 2; i < ingredients.length; i++) {
            if (Character.isUpperCase(ingredients[i].charAt(0))) {
                submixtures.add(ingredients[i]);
            }
        }
        return submixtures;
    }

    private int numberOfBowlsForRecipe(String recipe) {
        // Check the cache
        if (mBowlsRequired.containsKey(recipe)) {
            return mBowlsRequired.get(recipe);
        }

        RecipePlan bowlPlan = createPlan(recipe);
        int bowls = executeRecipe(bowlPlan);

        // Store the result in the cache
        mBowlsRequired.put(recipe, bowls);
        return bowls;
    }

    /**
     * Create an execution plan for the given recipe. The execution plan
     * includes information about how many bowls will be required to create each
     * of the mixtures required by the recipe.
     * 
     * @see executeRecipe
     */
    private RecipePlan createPlan(String recipe) {
        // Find out how many bowls it takes to make each of the mixtures
        List<String> mixtures = mRecipeToSubmixtures.get(recipe);
        RecipePlan bowlPlan = new RecipePlan();
        int[] bowlsForMixture = bowlPlan.plan = new int[mixtures.size() + 1];
        // This last bowl is for the recipe we are currently making
        bowlsForMixture[mixtures.size()] = 1;
        for (int i = 0; i < mixtures.size(); i++) {
            bowlsForMixture[i] = numberOfBowlsForRecipe(mixtures.get(i));
        }
        Arrays.sort(bowlsForMixture);
        return bowlPlan;
    }

    private static int executeRecipe(RecipePlan bowlPlan) {
        // Make the mixtures: we start with the "most difficult" mixtures and
        // work backwards. After each mixture, we will need one bowl reserved to
        // hold it while we make the remaining mixtures
        int[] bowlsForStep = new int[bowlPlan.plan.length];
        final int length = bowlsForStep.length;
        for (int i = 0; i < length; i++) {
            bowlsForStep[i] = bowlPlan.plan[length - 1 - i] + i;
        }

        // Take the max from bowlsForStep
        Arrays.sort(bowlsForStep);
        int bowls = bowlsForStep[bowlsForStep.length - 1];
        return bowls;
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
