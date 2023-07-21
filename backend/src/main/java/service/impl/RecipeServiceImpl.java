package service.impl;

import dto.RecipeDto;
import entity.Recipe;
import repository.impl.RecipeRepository;
import service.RecipeService;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository = new RecipeRepository();

    @Override
    public void addRecipe(RecipeDto dto) {
        Recipe newRecipe = new Recipe(dto);
        recipeRepository.save(newRecipe);
    }

    @Override
    public List<RecipeDto> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(RecipeDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> getRecipesByUserId(Long id) {
        return null;
    }

    @Override
    public boolean recipeDoesNotExistsByName(String name) {
        return !recipeRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
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
