package service;

import com.amazonaws.services.rekognition.model.Image;
import dto.ticket.ProcessedTicket;
import dto.ticket.TicketRequest;
import dto.ingredient.UserIngredientDto;

import java.util.List;

public interface RekognitionService {
    void handleRequest(TicketRequest request);
    boolean imageIsAValidTicket(Image ticket);
    ProcessedTicket extractInformationFromTicket(Image ticket);
}
