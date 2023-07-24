package dto.ticket;

import dto.ingredient.UserIngredientDto;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessedTicket {
    private Long userId;
    private String boughtDate;
    List<UserIngredientDto> ingredients;

    @Override
    public String toString() {
        return "ProcessedTicket{" +
                "\nuserId=" + userId +
                ",\nboughtDate='" + boughtDate + '\'' +
                ",\ningredients=" + ingredients +
                '}';
    }
}
