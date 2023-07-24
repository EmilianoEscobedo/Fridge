package dto.ticket;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class UnprocessedTicket {
    private Long userId;
    private String boughtDate;
    private List<String> ingredients;

    public UnprocessedTicket(){}

    public UnprocessedTicket(String boughtDate, List<String> ingredients){
        this.boughtDate = boughtDate;
        this.ingredients = ingredients;
    }
}