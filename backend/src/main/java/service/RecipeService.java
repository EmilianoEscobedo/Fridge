package service;

import dto.recipe.RecipeDto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface RecipeService {
    @WebMethod(operationName = "addRecipe")
    @WebResult(name = "addRecipeResult")
    void addRecipe(@WebParam(name = "recipe") RecipeDto recipe);

    @WebMethod(operationName = "getAllRecipes")
    @WebResult(name = "getAllRecipesResult")
    List<RecipeDto> getAllRecipes();

    @WebMethod(operationName = "getRecipesByUserId")
    @WebResult(name = "getRecipesByUserIdResult")
    List<RecipeDto> getRecipesByUserId(@WebParam(name = "userId") Long userId);

    @WebMethod(operationName = "deleteRecipeById")
    @WebResult(name = "deleteRecipeByIdResult")
    void deleteRecipeById(@WebParam(name = "recipeId") Long recipeId);

    boolean recipeDoesNotExistsByName(String name);
}
