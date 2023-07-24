package service;

import com.amazonaws.services.rekognition.model.Image;
import dto.ticket.TicketRequest;
import dto.ticket.UnprocessedTicket;

public interface RekognitionService {
    UnprocessedTicket handleRequest(TicketRequest request);
    boolean imageIsAValidTicket(Image ticket);
    UnprocessedTicket extractInformationFromTicket(Image ticket);
}
