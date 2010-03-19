import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BowlSolver {

    public String solve(String input) {
        List<String> recipes = new ArrayList<String>(Arrays.asList(input.split("\n")));
        recipes.remove(1);
        recipes.remove(0);
        int bowlsForRecipeList = -1;
        for (String recipe : recipes) {
            int bowlsForRecipe = numberOfBowlsForRecipe(recipe);
            bowlsForRecipeList = Math.max(bowlsForRecipeList, bowlsForRecipe);
        }
        return "Case #1: " + bowlsForRecipeList + "\n";
    }

    private int numberOfBowlsForRecipe(String recipe) {
        String[] words = recipe.split(" ");
        int bowlsForRecipe = 0;
        for (String word : words) {
            if (Character.isUpperCase(word.charAt(0))) {
                bowlsForRecipe++;
            }
        }
        return bowlsForRecipe;
    }

}
