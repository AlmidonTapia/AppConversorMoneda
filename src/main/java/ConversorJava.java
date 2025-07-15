import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorJava {
    private final String API_KEY = System.getenv("API_KEY");
    private final String BASEURL = "https://v6.exchangerate-api.com/v6/";
    private final String monedaBase = "PEN";

    public double obtenerTasa(String moneda) {
        String peticion = BASEURL + API_KEY + "/latest/" + monedaBase;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(peticion)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
            return rates.get(moneda.toUpperCase()).getAsDouble();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consultar la API: " + e.getMessage());
        }
    }

    public double convertir(String monedaDesde, String monedaHasta, double cantidad) {
        double tasaDesde = obtenerTasa(monedaDesde);
        double tasaHasta = obtenerTasa(monedaHasta);

        if (tasaDesde > 0 && tasaHasta > 0) {
            return (cantidad / tasaDesde) * tasaHasta;
        } else {
            throw new IllegalArgumentException("Moneda no soportada o error al obtener tasas");
        }
    }
}