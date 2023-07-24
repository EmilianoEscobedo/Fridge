

import service.AiService;
import service.impl.AiServiceImpl;
import service.RekognitionService;
import service.impl.IngredientServiceImpl;
import service.impl.RecipeServiceImpl;
import service.impl.RekognitionServiceImpl;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");


        /*Endpoint.publish("http://localhost:8080/users", new UserServiceImpl());
        Endpoint.publish("http://localhost:8080/ingredients", new IngredientServiceImpl());
        Endpoint.publish("http://localhost:8080/recipes", new RecipeServiceImpl());*/

        RekognitionServiceImpl rekognition = new RekognitionServiceImpl();
        rekognition.handleRequest(rekognition.mockRequest());

        AiService service = new AiServiceImpl();

        System.out.println(service.request("Lista de alimentos:" +
                "respóndeme a cada punto cuál es la palabra que corresponde a un alimento:" +
                "responde una sola palabra por punto, no hagás aclaraciones" +
                "en caso de que reconozcás una palabra pero esté incompleta, completalá" +
                "en caso de que no reconozcas un alimento, ignoralá" +
                "la lista es:" +
                "1. PAPA ECONIMICAxKG" +
                "2. CEBOLLA COMERCIA" +
                "3. ZANAHORIA COM X K" +
                "4. 344821 HUEVO BC" +
                "5. 714810 REBOZAD- CA-" +
                "6. 4/4 227163 ARROZ 0000" +
                "7. 000221 SAL CUESTA.." +
                "8. 034062 FIDEOS MOS" +
                "9. 009687 CHOCLO CRE-" +
                "10. 330548 TE CARREFO" +
                "11. 345194 PURE CARRE"));
        emf.close();
    }
}