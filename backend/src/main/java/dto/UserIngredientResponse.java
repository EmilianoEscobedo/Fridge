package dto;

import entity.Ingredient;
import entity.UserIngredient;
import lombok.Data;

@Data
public class UserIngredientResponse {
    Ingredient ingredient;
    private Double quantity;
    private String boughtDate;

    public UserIngredientResponse(UserIngredient userIngredient) {
        this.ingredient = userIngredient.getIngredient();
        this.quantity = userIngredient.getQuantity();
        this.boughtDate = userIngredient.getBoughtDate();
    }
}
