package grupo3;
import java.util.List;
import modelo.Producto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Servicio3Test {

    //Pruebas Unitarias para funcionalidad secundaria 1
    @Test
    void verificarLimiteValidoTest() {
        double totalValido = 5000.00;
        assertTrue(Servicio3.verificarLimite(totalValido));
    }

    @Test
    void verificarLimiteExcedidoTest() {
        double totalExcedido = 5000.01;
        assertFalse(Servicio3.verificarLimite(totalExcedido));
    }

    //Pruebas unitarias para funcionalidad secundaria 2
    @Test
    void descuentoValidoTest(){
        double descuento = 49.0; //Dentro del rango (0 - 50)
        boolean resultado = Servicio3.validarDescuento(descuento);

        assertTrue(resultado);
    }

    @Test
    void descuentoInvalidoTest(){
        double descuento = 51.0; //Fuera del rango (0 - 50)
        boolean resultado = Servicio3.validarDescuento(descuento);

        assertFalse(resultado);
    }

    //Pruebas unitarias para funcionalidad secundaria 3
    @Test
    void testIGVConProductoSimulado() {
        Producto producto = new Producto("Laptop", 200.0, 1);
        double total = producto.getPrecio() * producto.getCantidad();
        double totalConIGV = Servicio3.calcularIGV(total);
        assertEquals(236.0, totalConIGV);
    }

    //Pruebas unitarias para funcionalidad secundaria 4
    @Test
    void validarCliente_NombreValidoTest(){
        String nombreValido = " Juan Pérez ";
        boolean resultado = Servicio3.validarCliente(nombreValido);
        assertTrue(resultado);
    }

    @Test
    void validarCliente_NombreVacioTest(){
        String nombreVacio = "  ";
        boolean resultado = Servicio3.validarCliente(nombreVacio);
        assertFalse(resultado);
    }


    //Pruebas unitarias para funcionalidad secundaria 5
    @Test
    void verificarStock_cantidadCeroTest() {
        List<Producto> productosConUnoEnCero = List.of(
                new Producto("Mouse", 100.0, 2),
                new Producto("Teclado", 50.0, 0),
                new Producto("Monitor", 20.0, 1)
        );
        boolean resultado = Servicio3.verificarStock(productosConUnoEnCero);
        assertFalse(resultado);
    }

    @Test
    void verificarStock_cantidadValidaTest() {
        List<Producto> productosConStockValido = List.of(
                new Producto("Mouse", 100.0, 2),
                new Producto("Teclado", 50.0, 1),
                new Producto("Monitor", 20.0, 3)
        );
        boolean resultado = Servicio3.verificarStock(productosConStockValido);
        assertTrue(resultado);
    }
}