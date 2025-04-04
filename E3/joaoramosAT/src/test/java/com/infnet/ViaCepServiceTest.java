package com.infnet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ViaCepServiceTest {

    private final String[][] infoValida = {
        // cep , uf, cid, bairro, logradouro
        {"01001000", "SP", "São Paulo", "Sé", "Praça da Sé"},  
        {"21235110", "RJ", "Rio de Janeiro", "Irajá", "Avenida Monsenhor Félix"}, 
        {"75084150", "GO", "Anapolis", "São Carlos", "Avenida Dona Elvira"}, 
        {"64217395", "PI", "Parnaíba", "São Vicente de Paula", "Rua José Ribamar de Lima"}
    };

    @Test
    public void EncontraCepPorEndereço() { // Verifica CEPs validos
        for (String[] EndValido : infoValida) {

            Endereco end = ViaCepSercice.consultaPorCep(EndValido[0]);
            assertAll("Endereço correto", 
                () -> assertEquals(EndValido[1], end.getUf()), 
                () -> assertEquals(EndValido[2], end.getLocalidade()), 
                () -> assertEquals(EndValido[3], end.getBairro()), 
                () -> assertEquals(EndValido[4], end.getLogradouro()));
        }
    }

    @Test
    public void EncontraEndereçoPorCep() {// Verifica endereços validos
        
        for (String[] EndValido : infoValida) {

            Endereco[] enderecos = ViaCepSercice.consultaPorEndereco(
                EndValido[1], 
                EndValido[2], 
                EndValido[4]
            );

            for (Endereco end : enderecos){
                assertEquals(end.getCep(), EndValido[0]);
            }
        }
    }

    @Test
    public void VerificaCepIncorreto() {
        
        // Entrada VALIDA:
        assertNotNull(ViaCepSercice.consultaPorCep( "01001000" ));

        // Entradas INVALIDAS:
        assertNull(ViaCepSercice.consultaPorCep( "010010009" )); // digito a mais
        assertNull(ViaCepSercice.consultaPorCep( "0100100" )); // digito a menos
        assertNull(ViaCepSercice.consultaPorCep( "L01001000" )); // letra
        assertNull(ViaCepSercice.consultaPorCep( "01001-000" )); // com separador
        assertNull(ViaCepSercice.consultaPorCep( "" )); // vazio
    }

    @Test
    public void VerificaUFIncorreta() {
        // Entrada VALIDA:
        assertNotEquals(0, ViaCepSercice.consultaPorEndereco("SP", "São Paulo", "Avenida Paulista" ).length);
        
        // Entradas INVALIDAS:
        assertEquals(0, ViaCepSercice.consultaPorEndereco("S", "São Paulo", "Avenida Paulista" )); // letra a menos
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SPP", "São Paulo", "Avenida Paulista" )); // letra a mais 
        assertEquals(0, ViaCepSercice.consultaPorEndereco("1P", "São Paulo", "Avenida Paulista" )); // com numeros
        assertEquals(0, ViaCepSercice.consultaPorEndereco("sp", "São Paulo", "Avenida Paulista" )); // sem capitalizacao
        assertEquals(0, ViaCepSercice.consultaPorEndereco("", "São Paulo", "Avenida Paulista" )); // vazia
    }

    @Test
    public void VerificaCidadeIncorreta() {
        // Entrada VALIDA:
        assertNotEquals(0, ViaCepSercice.consultaPorEndereco("SP", "Sao Paulo", "Avenida Paulista" ).length);
        
        // Entradas INVALIDAS:
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SP", "São Paulo", "Avenida Paulista" )); // com acento
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SP", "SaoPaulo", "Avenida Paulista" )); // sem espaço
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SP", "SAO PAULO", "Avenida Paulista" )); // com capitalizacao
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SP", "sao paulo", "Avenida Paulista" )); // sem capitalizacao
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SP", "Soa Paulo", "Avenida Paulista" )); // grafia errada
        assertEquals(0, ViaCepSercice.consultaPorEndereco("SP", "", "Avenida Paulista" )); // vazio
    }
}
