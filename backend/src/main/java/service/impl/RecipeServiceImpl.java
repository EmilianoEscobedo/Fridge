package service.impl;

import dto.recipe.RecipeDto;
import entity.Recipe;
import repository.impl.RecipeRepository;
import service.RecipeService;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "service.RecipeService")
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository = new RecipeRepository();


    public void addRecipe(RecipeDto recipe) {
        Recipe newRecipe = new Recipe(recipe);
        recipeRepository.save(newRecipe);
    }

    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(RecipeDto::new)
                .collect(Collectors.toList());
    }


    public List<RecipeDto> getRecipesByUserId(Long userId) {
        return null;
    }

    public void deleteRecipeById(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    public boolean recipeDoesNotExistsByName(String name) {
        return !recipeRepository.existsByName(name);
    }

    public void handleRequest(List<RecipeDto> recipeList) {
        recipeList.forEach(this::saveRecipeIfNotExists);
    }

    private void saveRecipeIfNotExists(RecipeDto dto) {
        if (recipeDoesNotExistsByName(dto.getName())){
            addRecipe(dto);
        }
    }
}
