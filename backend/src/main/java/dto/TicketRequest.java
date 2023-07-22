package dto;

import lombok.Data;

@Data
public class TicketRequest {
    private byte[] image;
    private Long userId;
}
