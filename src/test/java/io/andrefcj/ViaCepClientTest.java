package io.andrefcj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViaCepClientTest {
    private ViaCepClient viaCepClient;

    @BeforeEach
    void setup() {
        viaCepClient = new ViaCepClient();
    }

    @Test
    @DisplayName("Request with correct data should work")
    void testCorrectRequest() {
        var cep = "89202295";
        var response = viaCepClient.find(cep);

        assertEquals("4209102", response.ibge());
        assertEquals("89202-295", response.cep());
        assertEquals("Joinville", response.localidade());
        assertEquals("Avenida Getúlio Vargas", response.logradouro());
    }
}
