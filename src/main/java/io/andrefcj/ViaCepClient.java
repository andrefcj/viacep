package io.andrefcj;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.andrefcj.dto.CepDto;
import io.andrefcj.exception.CepException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepClient {
    public CepDto find(String cep) {
        try {
            // TODO: validate cep
            final String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), CepDto.class);
        } catch (Exception e) {
            throw new CepException(e.getMessage());
        }
    }
}
