package org.example;

/*
*
* Tabela de decisão:
*
*
*/

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ViaCEP {
    public static Optional<Address> getAddressByCEP(String cep) throws IllegalArgumentException {

        if (cep.matches("[a-zA-Z]+") || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP inválido. Não pode ter letras ou ser vazio.");
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return Optional.of(new Gson().fromJson(response.body(), Address.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Address[] getAddressesByAddress(String uf, String cidade, String logradouro) throws IllegalArgumentException {

        if (uf.length() != 2) {
            throw new IllegalArgumentException("UF deve ter apenas dois caracteres.");
        }

        if (cidade.isEmpty() || logradouro.isEmpty()) {
            throw new IllegalArgumentException("Cidade ou logradouro não podem estar vazios.");
        }

        if (cidade.length() > 50) {
            throw new IllegalArgumentException("Cidade não pode ter mais de 50 letras.");
        }

        if (logradouro.length() > 100) {
            throw new IllegalArgumentException("Logradouro não pode ter mais de 100 letras.");
        }

        String url = String.format("https://viacep.com.br/ws/%s/%s/%s/json/", uf, cidade, logradouro);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), Address[].class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Address[0];
    }
}