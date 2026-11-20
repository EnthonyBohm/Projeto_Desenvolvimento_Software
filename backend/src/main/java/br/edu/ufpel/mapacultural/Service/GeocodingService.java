
package br.edu.ufpel.mapacultural.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class GeocodingService {

    private final RestClient restClient;

    public GeocodingService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .defaultHeader("User-Agent", "MapaCultural/1.0")
                .build();
    }

    public Optional<Coordinates> buscarCoordenadas(String endereco) {
        GeocodingResult[] resultados = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", endereco)
                        .queryParam("format", "jsonv2")
                        .queryParam("limit", 1)
                        .queryParam("countrycodes", "br")
                        .build())
                .retrieve()
                .body(GeocodingResult[].class);

        if (resultados == null || resultados.length == 0) {
            return Optional.empty();
        }

        Double latitude = Double.parseDouble(resultados[0].lat());
        Double longitude = Double.parseDouble(resultados[0].lon());

        return Optional.of(new Coordinates(latitude, longitude));
    }

    public record Coordinates(Double latitude, Double longitude) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record GeocodingResult(String lat, String lon) {}
}
