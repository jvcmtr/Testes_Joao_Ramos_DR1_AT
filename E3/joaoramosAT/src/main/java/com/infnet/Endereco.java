package com.infnet;
import jdk.internal.joptsimple.internal.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    // Propriedades returadas do site: https://viacep.com.br/#:~:text=nos%20exemplos%20abaixo.-,Formatos%20de%20Retorno,-Veja%20exemplos%20de

    @Override
    public String toString(){
        return String.join(" - ", cep, logradouro, complemento, bairro, localidade, uf);
    }
}