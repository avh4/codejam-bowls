import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlSolver {

    Map<String, List<String>> mRecipeToSubmixtures = new HashMap<String, List<String>>();

    Map<String, Integer> mBowlsRequired = new HashMap<String, Integer>();

    public String solve(String input) {
        List<String> recipes = new ArrayList<String>(Arrays.asList(input.split("\n")));
        recipes.remove(1);
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
        return "Case #1: " + bowlsForRecipeList + "\n";
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

}
