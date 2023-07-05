package es.ufv.dis.final2022.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Service
public class APIfront {
    public String urlPrefix = "http://localhost:8888"; // Dirección donde se aloja el back

    // GET request

    public List<Persona> getAllProductos() throws URISyntaxException, IOException, InterruptedException {
        String urlFull = urlPrefix + "/ObtenerProductos";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlFull))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Type tipo = new TypeToken<List<Persona>>(){}.getType();

        return gson.fromJson(response.body(),tipo);
    }

    public Persona getPersona(String nombre) throws URISyntaxException, IOException, InterruptedException {
        String urlFull = urlPrefix + "/EncontrarProducto/" + nombre;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlFull))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Type tipo = new TypeToken<Persona>(){}.getType();

        return gson.fromJson(response.body(),tipo);
    }

    // POST request

    public Persona putPersona(String nombre, String  categoria, int precio, String ean13) throws IOException, InterruptedException {
        String urlFull = urlPrefix + "/addProducto";

        var objectMapper = new ObjectMapper();

        String requestBody = objectMapper
                .writeValueAsString(new Persona(nombre, categoria, precio, ean13));


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlFull))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();


        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Type tipo = new TypeToken<Persona>(){}.getType();

        return gson.fromJson(response.body(),tipo);
    }

    // DELETE request

    public void deleteProdcuto(String nombre) throws IOException, InterruptedException {
        String urlFull = urlPrefix + "/BorrarProducto/" + nombre;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlFull))
                .DELETE()
                .build();

        HttpResponse response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
}
