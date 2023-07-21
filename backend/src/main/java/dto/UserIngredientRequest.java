package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIngredientRequest {
    Long userId;
    String name;
    Double quantity;
    String boughtDate;
}
