package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestUtil {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static <T> T sendGetRequest(String url, Class<T> responseType)
      throws IOException, InterruptedException {

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .GET()
            .build(); // object

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    T userResponse = objectMapper.readValue(response.body(), responseType);

    return userResponse;
  }

  public static <T> T sendPostRequest(String url, Class<T> responseType, Object userPostRequest)
      throws IOException, InterruptedException { // Reusable

    String jsonRequestAsString = objectMapper.writeValueAsString(userPostRequest);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonRequestAsString))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    T userResponse = objectMapper.readValue(response.body(), responseType);

    //		System.out.println("Response is : " + response.body());

    return userResponse;
  }

  public static <T> T sendDeleteRequest(String url, Class<T> responseType)
      throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .DELETE()
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    T userResponse = objectMapper.readValue(response.body(), responseType);

    return userResponse;
  }

  public static <T> T sendPutRequest(String url, Object request, Class<T> responseType)
      throws IOException, InterruptedException {
    String jsonRequestAsString = objectMapper.writeValueAsString(request);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest httpRequest =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(jsonRequestAsString))
            .build();

    HttpResponse<String> httpResponse =
        client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

    T response = objectMapper.readValue(httpResponse.body(), responseType);

    return response;
  }
}
