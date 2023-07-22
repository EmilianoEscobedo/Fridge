package dto.ticket;

import dto.ingredient.UserIngredientDto;
import lombok.Data;

import java.util.List;

@Data
public class ProcessedTicket {
    private Long userId;
    private String boughtDate;
    List<UserIngredientDto> ingredients;
}
