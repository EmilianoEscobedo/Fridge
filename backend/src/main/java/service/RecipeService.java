package service;

import dto.RecipeDto;
import entity.Recipe;

import java.util.List;

public interface RecipeService {
    void addRecipe(RecipeDto recipe);
    List<RecipeDto> getAllRecipes();
    List<RecipeDto> getRecipesByUserId(Long id);
    boolean recipeDoesNotExistsByName(String name);
    void deleteById(Long id);
}
