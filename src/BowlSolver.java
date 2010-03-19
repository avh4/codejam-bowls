import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlSolver {

    Map<String, List<String>> mIngredientsMap = new HashMap<String, List<String>>();

    Map<String, Integer> mBowlsRequired = new HashMap<String, Integer>();

    public String solve(String input) {
        List<String> recipes = new ArrayList<String>(Arrays.asList(input.split("\n")));
        recipes.remove(1);
        recipes.remove(0);
        for (String recipe : recipes) {
            final String name = parseName(recipe);
            List<String> ingredients = parseIngredients(recipe);
            mIngredientsMap.put(name, ingredients);
        }
        int bowlsForRecipeList = -1;
        for (String recipe : mIngredientsMap.keySet()) {
            bowlsForRecipeList = Math.max(bowlsForRecipeList, numberOfBowlsForRecipe(recipe));
        }
        return "Case #1: " + bowlsForRecipeList + "\n";
    }

    private String parseName(String recipe) {
        return recipe.split(" ")[0];
    }

    private List<String> parseIngredients(String recipe) {
        List<String> ingredients = new ArrayList<String>(Arrays.asList(recipe.split(" ")));
        ingredients.remove(1);
        ingredients.remove(0);
        return ingredients;
    }

    private int numberOfBowlsForRecipe(String recipe) {
        // Check the cache
        if (mBowlsRequired.containsKey(recipe)) {
            return mBowlsRequired.get(recipe);
        }

        // Calculate if the value wasn't already cached
        int bowlsForRecipe = 1; // We need one bowl to hold the resulting
                                // mixture
        for (String word : mIngredientsMap.get(recipe)) {
            if (Character.isUpperCase(word.charAt(0))) {
                bowlsForRecipe += 1;
            }
        }
        mBowlsRequired.put(recipe, bowlsForRecipe);
        return bowlsForRecipe;
    }

}
