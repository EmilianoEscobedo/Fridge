package dto;

import entity.User;
import entity.UserIngredient;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {
    Long id;
    String name;
    String profileImage;
}
