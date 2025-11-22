package grupo3;
import base.Pedido;
import modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//con lo nuevo que se pide en la practica
public class PedidoTest {
    private Pedido pedido;
    private Producto baseProducto;

    @BeforeEach
    void setup() {
        pedido = new Pedido();
        baseProducto = new Producto(
                "Laptop", 1500.0, 10,
                "SKU123", "Tecnología",
                true, true
        );
    }

    @Test
    void testCantidadNoValida() {
        assertFalse(pedido.agregarProducto(baseProducto, 0));
        assertFalse(pedido.agregarProducto(baseProducto, -5));
    }

    @Test
    void testProductoDuplicadoPorSKU() {
        assertTrue(pedido.agregarProducto(baseProducto, 5));
        // Segundo intento con el mismo SKU debe fallar
        assertFalse(pedido.agregarProducto(baseProducto, 3));
    }

    @Test
    void testAgregadoCorrecto() {
        assertTrue(pedido.agregarProducto(baseProducto, 2));
        assertEquals(1, pedido.getDetallesPedido().size());
        assertEquals("SKU123", pedido.getDetallesPedido().get(0).getSku());
    }

    @Test
    void testRespetoDeAtributos() {
        pedido.agregarProducto(baseProducto, 4);

        Producto p = pedido.getDetallesPedido().get(0);

        assertEquals(baseProducto.getNombre(), p.getNombre());
        assertEquals(baseProducto.getPrecio(), p.getPrecio());
        assertEquals(baseProducto.getSku(), p.getSku());
        assertEquals(baseProducto.getCategoria(), p.getCategoria());
        assertEquals(true, p.isActivo());
        assertEquals(true, p.isDescuentoAplicable());
    }

    @Test
    void testProductoInactivo() {
        Producto inactivo = new Producto(
                "Mouse", 50.0, 10,
                "SKUX1", "Accesorios",
                false, false
        );
        assertFalse(pedido.agregarProducto(inactivo, 3));
    }

    @Test
    void testListaVacia() {
        assertTrue(pedido.validarStock());
    }

    @Test
    void testTodosConStockValido() {
        pedido.agregarProducto(baseProducto, 5);

        Producto p2 = new Producto(
                "Teclado", 80.0, 3,
                "SKU777", "Accesorios",
                true, false
        );
        pedido.agregarProducto(p2, 3);
        assertTrue(pedido.validarStock());
    }

    @Test
    void testUnProductoConStockCero() {
        Producto pZero = new Producto(
                "Headset", 100.0, 0,
                "SKUX2", "Accesorios",
                true, false
        );
        pedido.agregarProducto(pZero, 0);

        assertFalse(pedido.validarStock());
    }

    @Test
    void testCantidadNegativaEnProducto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("Tablet", 300.0, -1,
                    "SKUTAB", "Tecnología",
                    true, false);
        });
    }

    @Test
    void testValoresLimite() {
        Producto p1 = new Producto(
                "USB", 20.0, 1,
                "SKUUSB", "Accesorios",
                true, false
        );
        Producto p2 = new Producto(
                "Monitor", 800.0, 999,
                "SKUMON", "Tecnología",
                true, false
        );

        pedido.agregarProducto(p1, 1);
        pedido.agregarProducto(p2, 999);

        assertTrue(pedido.validarStock());
    }
}
