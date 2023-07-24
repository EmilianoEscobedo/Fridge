package service.impl;

import lombok.NoArgsConstructor;
import service.AiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@NoArgsConstructor
public class AiServiceImpl implements AiService {

    private final String prompt = "Lista de alimentos:" +
            " respóndeme a cada punto cuál es la palabra que corresponde a un alimento:" +
            " responde una sola palabra por punto, no hagás aclaraciones" +
            " en caso de que no reconozcas facilmente amplia coincidencia con un alimento, ignorala, es muy importante." +
            " en caso de que reconozcás amplia coincidencia con una palabra de alimento pero esté incompleta, completalá" +
            " la lista es: ";

    @Override
    public String request(String content) {
        try {

            URL serviceUrl = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();

            connection.setRequestMethod("POST");
            String API_KEY = "${API_KEY}";
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");

            String MODEL = "gpt-3.5-turbo";
            System.out.println(prompt + content);
            String body = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + content + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }

}


