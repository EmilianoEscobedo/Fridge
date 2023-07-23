package service;

import dto.ingredient.UserIngredientRequest;
import dto.ingredient.UserIngredientResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
@WebService
public interface IngredientService {

    @WebMethod(operationName = "createIngredient")
    @WebResult(name = "createIngredientResult")
    void addUserIngredient(@WebParam(name="request") UserIngredientRequest request);

    @WebMethod(operationName = "getAllIngredients")
    @WebResult (name = "getAllIngredientsResult")
    List<UserIngredientResponse> getAllIngredientsByUserId(@WebParam(name="userId") Long userId);

    @WebMethod(operationName = "deleteIngredientById")
    @WebResult (name = "deleteIngredientByIdResult")
    void deleteById(@WebParam(name="ingredientId") Long ingredientId);

    boolean IngredientDoesNotExistsByName(String name);
}
