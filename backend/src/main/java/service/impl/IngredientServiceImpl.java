package service.impl;

import dto.UserIngredientRequest;
import dto.UserIngredientResponse;
import entity.Ingredient;
import entity.User;
import entity.UserIngredient;
import jakarta.inject.Inject;
import repository.impl.IngredientRepository;
import repository.impl.UserRepository;
import service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientServiceImpl implements IngredientService {
    @Inject
    IngredientRepository ingredientRepository;
    @Inject
    UserRepository userRepository;

    @Override
    public void addUserIngredient(UserIngredientRequest dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new RuntimeException("Don't exists user with the provided id:" + dto.getUserId()));

        Ingredient ingredient = new Ingredient(dto.getName());
        if (IngredientDoesNotExistsByName(dto.getName())) {
            ingredientRepository.save(ingredient);
        }
        ingredient.setId(ingredientRepository.findIdByName(ingredient.getName()));
        UserIngredient userIngredient = new UserIngredient(user, ingredient, dto.getQuantity(), dto.getBoughtDate());
        ingredientRepository.saveUserIngredient(userIngredient);
    }

    @Override
    public boolean IngredientDoesNotExistsByName(String name) {
        return !ingredientRepository.existsByName(name);
    }

    @Override
    public List<UserIngredientResponse> getAllIngredientsByUserId(Long id) {
        return ingredientRepository.findUserIngredientsByUserId(1L)
                .stream()
                .map(UserIngredientResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {

    }
}
