package infnet;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Tag;


class CalculoIMCTest {

    @Test
    @Tag("RF1") // Requisito funcional 1
    void testIndiceDeMassa() {
        // Testa calculo
        assertEquals(20, CalculoIMC.calcularPeso(80, 2));
        assertEquals(40, CalculoIMC.calcularPeso(10, 0.5));
        assertEquals(70, CalculoIMC.calcularPeso(70, 1));

        // Testar valores Limitrofes
        assertEquals(0, CalculoIMC.calcularPeso(0, 1));
        assertThrows(InvalidParameterException.class, () -> CalculoIMC.calcularPeso(1, 0) );
    }

    @Test
    @Tag("RF2") // Requisito Funcional 2
    void testClassificacao() {

        // Testa valores validos
        // Testa os valores entre as classificações para garantir que não hà erros de maior ou igual
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(15.9));
        assertEquals("Magreza moderada", CalculoIMC.classificarIMC(16));
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(17));
        assertEquals("Saudável", CalculoIMC.classificarIMC(18.5));
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(25));
        assertEquals("Obesidade Grau I", CalculoIMC.classificarIMC(30.0));
        assertEquals("Obesidade Grau II", CalculoIMC.classificarIMC(35.0));
        assertEquals("Obesidade Grau III", CalculoIMC.classificarIMC(40.0));

        // Testa valores Limitrofes e Invalidos
        assertEquals("Magreza grave", CalculoIMC.classificarIMC( 0.00000000000000000000001 ));
        assertThrows(InvalidParameterException.class, () -> CalculoIMC.classificarIMC(0));
        assertThrows(InvalidParameterException.class, () -> CalculoIMC.classificarIMC(-1));
    }

    
    @Test
    @Tag("RF3") // Requisito Funcional 3
    void testClassificarIMC() {
        Exception ex1 = assertThrows(InvalidParameterException.class, () -> CalculoIMC.classificarIMC(-1));
        assertEquals("Por favor, utilize apenas numeros maiores que zero", ex1.getMessage() );
        
        Exception ex2 = assertThrows(InvalidParameterException.class, () -> CalculoIMC.classificarIMC(0));
        assertEquals("Por favor, utilize apenas numeros maiores que zero", ex2.getMessage() );
    }
}
