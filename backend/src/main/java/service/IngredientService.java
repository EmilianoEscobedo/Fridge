package service;

import dto.ingredient.UserIngredientRequest;
import dto.ingredient.UserIngredientResponse;

import java.util.List;

public interface IngredientService {
    void addUserIngredient(UserIngredientRequest dto);
    boolean IngredientDoesNotExistsByName(String name);
    List<UserIngredientResponse> getAllIngredientsByUserId(Long id);
    void deleteById(Long id);
}
