package controller;

import dto.RegisterRequest;
import dto.UserDto;
import dto.UserIngredientRequest;
import dto.UserIngredientResponse;
import service.IngredientService;
import service.impl.IngredientServiceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class IngredientController {

    IngredientService ingredientService = new IngredientServiceImpl();

    @WebMethod(operationName = "createIngredient")
    @WebResult(name = "createIngredientResult")
    public void createIngredient(@WebParam(name="request") UserIngredientRequest request) {
        ingredientService.addUserIngredient(request);
    }

    @WebMethod(operationName = "getAllIngredients")
    @WebResult (name = "getAllIngredientsResult")
    public List<UserIngredientResponse> getAllIngredientsByUserId(@WebParam(name="userId") Long userId) {
        return ingredientService.getAllIngredientsByUserId(userId);
    }

    @WebMethod(operationName = "deleteIngredientById")
    @WebResult (name = "deleteIngredientByIdResult")
    public void deleteIngredientById(@WebParam(name="ingredientId") Long ingredientId) {
        ingredientService.deleteById(ingredientId);
    }
}
