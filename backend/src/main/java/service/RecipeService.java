package service;

import dto.RecipeDto;
import entity.Recipe;

import java.util.List;

public interface RecipeService {
    void addRecipe(Recipe recipe);
    List<RecipeDto> getAllRecipes();
    List<RecipeDto> getRecipesByUserId(Long id);
    boolean recipeExistsByName(String name);
    void deleteById();
}
