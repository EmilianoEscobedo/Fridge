package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIngredientRequest {
    Long userId;
    String name;
    Double quantity;
    String boughtDate;
}
