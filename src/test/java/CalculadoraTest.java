import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    @Test
    void testSuma() {
        assertEquals(5, Calculadora.sumar(2, 3));
    }
}


