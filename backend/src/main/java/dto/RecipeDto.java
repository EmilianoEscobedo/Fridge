package dto;

import entity.Recipe;
import entity.RecipeIngredient;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    String name;
    Integer cokingTime;
    String description;
    String instructions;
    List<RecipeIngredient> ingredients;

    public RecipeDto(Recipe entity) {
        this.name = entity.getName();
        this.cokingTime = entity.getCookingTime();
        this.description = entity.getDescription();
        this.instructions = entity.getInstructions();
        this.ingredients = entity.getRecipeIngredients();
    }
}
