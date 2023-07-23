package dto.ticket;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketRequest {
    private byte[] image;
    private Long userId;
}
