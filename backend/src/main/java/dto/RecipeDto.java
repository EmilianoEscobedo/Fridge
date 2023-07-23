package dto;

import entity.RecipeIngredient;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeDto {
    String name;
    Integer cokingTime;
    String description;
    String instructions;
    List<RecipeIngredient> ingredients;
}
