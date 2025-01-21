package poo.booksync.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {

    private static final HttpClient client = HttpClient.newBuilder().build();

    private static String sendRequest(String url, String body, Boolean withToken, String method) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json");

        if (withToken) {
            requestBuilder.header("Authorization", "Bearer " + TokenExtractor.getCurrentToken());
        }

        if ("POST".equalsIgnoreCase(method) && body != null) {
            requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
        } else if ("GET".equalsIgnoreCase(method)) {
            requestBuilder.GET();
        }

        HttpRequest request = requestBuilder.build();

        System.out.println(request.method() + " " + request.uri()+ " " + request.headers()+" "+body);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("HTTP Error: " + response.statusCode() + " - " + response.body());
        }
    }

    public static String sendPostRequest(String url, String body) throws IOException, InterruptedException {
        return sendRequest(url, body, false, "POST");
    }

    public static String sendGetRequest(String url) throws IOException, InterruptedException {
        return sendRequest(url, null, false, "GET");
    }

    public static String sendPostRequest(String url, String body, Boolean withToken) throws IOException, InterruptedException {
        return sendRequest(url, body, withToken, "POST");
    }

    public static String sendGetRequest(String url, Boolean withToken) throws IOException, InterruptedException {
        return sendRequest(url, null, withToken, "GET");
    }
}
