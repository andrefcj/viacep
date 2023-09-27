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
            final String rawCep = setRawCep(cep);
            final String url = "https://viacep.com.br/ws/" + rawCep + "/json/";

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

    private String setRawCep(String cep) {
        if (!isValid(cep)) {
            throw new CepException("Invalid CEP.");
        }
        return cep.replaceAll("-?\\D", "");
    }

    private Boolean isValid(String cep) {
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        return cep.matches("\\d{5}[-.\\s]?\\d{3}");
    }
}
