package service;

import dto.UserIngredientRequest;
import dto.UserIngredientResponse;

import java.util.List;

public interface IngredientService {
    void addUserIngredient(UserIngredientRequest dto);
    boolean IngredientDoesNotExistsByName(String name);
    List<UserIngredientResponse> getAllIngredientsByUserId(Long id);
}
