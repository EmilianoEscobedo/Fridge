package dto;

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
}
