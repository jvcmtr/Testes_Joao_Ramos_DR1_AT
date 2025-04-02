package infnet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.Positive ;


class MathFunctionsTest {

    @Property()
    void NumeroDobradoEhSemprePar(@ForAll int a) {
        assertTrue( MathFunctions.MultiplyByTwo( a )%2 == 0);
    }
    
    @Property
    void TabelaDeMultiplicacaoCorreta(@ForAll int a, @ForAll("SmallPositiveInt") int b ) {
       int[] table = MathFunctions.GenerateMultiplicationTable(a, b);
       for (int i = 0; i < table.length; i++) {
           assertEquals(0,  (a / table[i]) %1 );
       }
    }

    @Property
    void EhPrimo(@ForAll @Positive int a) {
        if(!MathFunctions.IsPrime(a)){
            return;
        }

        for (int i = 2; i < a/2; i++) {
            assertNotEquals( 0, a%i);
        }
    }

    @Property
    void MediaEstaDentroDosLimites(@ForAll("ListaDeNum") List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[] arr = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            int item = list.get(i);
            
            arr[i] = item;

            if(item < min){
                min = item;
            }
            if(item > max){
                max = item;
            }
        }

        double med = MathFunctions.CalculateAverage( arr);

        assertTrue( (med <= max ) && (med >= min)  );
    }

    @Provide
    Arbitrary<List<Integer>> ListaDeNum() {
        return Arbitraries.integers().list().ofMinSize(2).ofMaxSize(100);
    }

    @Provide
    Arbitrary<Integer> SmallPositiveInt(){
        return Arbitraries.integers().filter(i -> i > 0 &&  i < 1000);
    }
}
