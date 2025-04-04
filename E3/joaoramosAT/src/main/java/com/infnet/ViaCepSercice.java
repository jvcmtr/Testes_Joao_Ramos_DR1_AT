package com.infnet;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ViaCepSercice {
    private static final String BASE_URL = "https://viacep.com.br/ws/";

    private static final CloseableHttpClient client = HttpClients.createDefault();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Endereco consultaPorCep(String cep) {
        try {
            
            String url = getUrl( cep, "json/");
            
            CloseableHttpResponse response = client.execute(new HttpGet(url));
            
            int httpStatus = response.getStatusLine().getStatusCode();
            if (httpStatus != 200) {
                throw new RuntimeException("Erro na requisicao ao ViaCep:\n" + httpStatus + " " + url);
            }
            
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return objectMapper.readValue(jsonResponse, Endereco.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Endereco[] consultaPorEndereco(String uf, String cidade, String logradouro) {
        try {
            String url = getUrl( uf, cidade, logradouro , "json/");
            
            CloseableHttpResponse response = client.execute(new HttpGet(url));
            
            int httpStatus = response.getStatusLine().getStatusCode();
            if (httpStatus != 200) {
                throw new RuntimeException("Erro na requisição ao ViaCep:\n" + httpStatus + " " + url);
            }
            
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return objectMapper.readValue(jsonResponse, Endereco[].class);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Endereco[0];
        }
    }

    private static String formatUrl(String s ){
        return s.replace(" ", "%20");
    }

    private static String getUrl(String... strs){
        return formatUrl( 
            BASE_URL + String.join("/", strs) 
            );
    }
}

