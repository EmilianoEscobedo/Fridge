package service.impl;

import dto.ingredient.UserIngredientRequest;
import dto.ingredient.UserIngredientResponse;
import entity.Ingredient;
import entity.User;
import entity.UserIngredient;
import jakarta.inject.Inject;
import repository.impl.IngredientRepository;
import repository.impl.UserRepository;
import service.IngredientService;

import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "service.IngredientService")
public class IngredientServiceImpl implements IngredientService {
//    @Inject
    IngredientRepository ingredientRepository = new IngredientRepository();
//    @Inject
    UserRepository userRepository = new UserRepository();

    public void addUserIngredient(UserIngredientRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("Don't exists user with the provided id:" + request.getUserId()));

        Ingredient ingredient = new Ingredient(request.getName());
        if (IngredientDoesNotExistsByName(request.getName())) {
            ingredientRepository.save(ingredient);
        }
        ingredient.setId(ingredientRepository.findIdByName(ingredient.getName()));
        UserIngredient userIngredient = new UserIngredient(user, ingredient, request.getQuantity(), request.getBoughtDate());
        ingredientRepository.saveUserIngredient(userIngredient);
    }

    public boolean IngredientDoesNotExistsByName(String name) {
        return !ingredientRepository.existsByName(name);
    }

    public List<UserIngredientResponse> getAllIngredientsByUserId(Long userId) {
        return ingredientRepository.findUserIngredientsByUserId(userId)
                .stream()
                .map(UserIngredientResponse::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long ingredientId) {

    }
}
