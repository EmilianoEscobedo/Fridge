package dto.ingredient;

import entity.UserIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class UserIngredientDto {
    String name;
    Double quantity;

    public UserIngredientDto(String name){
       this.name = name;
    }

    @Override
    public String toString() {
        return "\n name='" + name + '\'' +
                ", quantity=" + quantity;
    }
}
