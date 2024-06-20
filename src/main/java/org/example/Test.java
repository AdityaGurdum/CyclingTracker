package org.example;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
 class Test {
    private static final String BING_MAPS_API_KEY = "AuhfPFF7T_cAB4f1vkfsVVHlviRj79Pm5f2J05tECaVSTweVUdD25Op2kyomgqcM";
    public static double getRouteDistanceBetweenPlaces(String origin, String destination) throws Exception {
        String encodedOrigin = URLEncoder.encode(origin, StandardCharsets.UTF_8);
        String encodedDestination = URLEncoder.encode(destination, StandardCharsets.UTF_8);

        String url = String.format("https://dev.virtualearth.net/REST/V1/Routes?wp.0=%s&wp.1=%s&key=%s",
                encodedOrigin, encodedDestination, BING_MAPS_API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());

        JSONArray resourceSets = jsonResponse.getJSONArray("resourceSets");
        JSONObject resourceSet = resourceSets.getJSONObject(0);
        JSONArray resources = resourceSet.getJSONArray("resources");
        JSONObject resource = resources.getJSONObject(0);

        // Route distance in kilometers
        return resource.getDouble("travelDistance");
    }
}



