package dto.ingredient;

import entity.Ingredient;
import entity.UserIngredient;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
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
