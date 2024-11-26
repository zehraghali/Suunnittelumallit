package solutions.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiFacade {

    public String getAttributeValueFromJson(String urlString, String attributeName) throws IllegalArgumentException, IOException {
        String jsonResult = sendHttpRequest(urlString);
        return extractAttributeFromJson(jsonResult, attributeName);
    }

    private String sendHttpRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (IOException e) {
            throw new IOException("Error making HTTP request: " + e.getMessage(), e);
        } finally {
            con.disconnect();
        }
    }

    private String extractAttributeFromJson(String json, String attributeName) throws IllegalArgumentException {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(json);

            if (jsonObject.containsKey(attributeName)) {
                return (String) jsonObject.get(attributeName);
            } else {
                throw new IllegalArgumentException("Attribute '" + attributeName + "' not found in the response.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing JSON response: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        ApiFacade apiFacade = new ApiFacade();

        try {
            String joke = apiFacade.getAttributeValueFromJson("https://api.chucknorris.io/jokes/random", "value");
            System.out.println("Chuck Norris Joke: " + joke);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            String exchangeRate = apiFacade.getAttributeValueFromJson("https://api.fxratesapi.com/latest?base=USD&symbols=EUR", "rates");
            System.out.println("Exchange Rates (USD to EUR): " + exchangeRate);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
