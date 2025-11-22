package grupo3;

import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {

    //probando
    //Tipo de prueba: "Valores limite" con funcionalidad 1 VERIFICAR LIMITE
    @Test
    void ValoresLimiteTest() {
        // Lista de productos cuyo total = 5000
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500.0, 2)   // 2500 * 2 = 5000
        );
        double total = Pedido.calcularTotalPedido(productos, 0);
        boolean resultado = Servicio3.verificarLimite(total);
        // Resultado esperado TRUE porque 5000 es el limite permitido
        assertTrue(resultado);
    }

    //Tipo de prueba: "Error en funcion base" con funcionalidad 2 VALIDAR DESCUENTO
    @Test
    void ErrorFuncionBaseTest() {
        List <Producto> productos = List.of(
                new Producto("Laptop", 0.0, 1), //precio invalido en la funcion base
                new Producto("Mouse", 0.0, 2) //precio invalido en la funcion base
        );

        double descuento = 10.0; //descuento valido

        //validamos que el descuento sea valido
        boolean descuentoValido = Servicio3.validarDescuento(descuento);
        assertTrue(descuentoValido);

        //la funcion base resulta en error porque el subtotal es 0
        assertThrows(IllegalArgumentException.class, () -> { Pedido.calcularTotalPedido((productos), descuento);});
    }

    //Tipo de prueba: "Caso exitoso" con funcionalidad 3 CALCULAR IGV
    @Test
    void FlujoCorrectoTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500, 1),
                new Producto("Mouse", 100, 2)
        );

        double total = Pedido.calcularTotalPedido(productos, 10);
        double totalIGV = Servicio3.calcularIGV(total);

        assertEquals(2867.4, totalIGV, 0.1);
    }

    //Tipo de prueba: "Combinacion de validaciones" con funcionalidad 4 VALIDAR CLIENTE
    @Test
    void Combinacion_TodosFallanTest(){
        List<Producto> productos = null;
        String nombreClienteInvalido = null;
        double descuento = 10.0;

        Exception exceptionPedido = assertThrows(IllegalArgumentException.class, () -> {
            Pedido.calcularTotalPedido(productos, descuento);
        });

        boolean clienteValido = Servicio3.validarCliente(nombreClienteInvalido);

        assertFalse(clienteValido);
        assertEquals("Error: no hay productos en el pedido", exceptionPedido.getMessage());
    }

    //Tipo de prueba: "Error en funcion secundaria" con funcionalidad 5 VERIFICAR STOCK
    @Test
    void ErrorFuncionSecundariaTest() {
        List<Producto> productos = List.of(
                new Producto("Laptop", 2500.0, 1),
                new Producto("Mouse", 100.0, 0)
        );
        double total = Pedido.calcularTotalPedido(productos, 0);
        boolean stockValido = Servicio3.verificarStock(productos);

        assertTrue(total > 0 && !stockValido);
    }

}
