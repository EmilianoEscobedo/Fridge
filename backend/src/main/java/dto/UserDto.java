package dto;

import entity.User;
import entity.UserIngredient;
import lombok.Data;

@Data
public class UserDto {
    Long id;
    String name;
    String profileImage;
}
