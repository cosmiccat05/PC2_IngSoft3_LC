package base;

import modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> detallesPedido;

    public Pedido() {
        this.detallesPedido = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto, int cantidad) {

        if (cantidad <= 0) {
            System.err.println("Error: La cantidad a agregar debe ser positiva.");
            return false;
        }
        if (!producto.isActivo()) {
            return false;
        }
        boolean existe = detallesPedido.stream()
                .anyMatch(p -> p.getSku().equals(producto.getSku()));

        if (existe) return false;
        Producto copia = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                cantidad,
                producto.getSku(),
                producto.getCategoria(),
                producto.isActivo(),
                producto.isDescuentoAplicable()
        );
        detallesPedido.add(copia);
        return true;
    }

    public boolean validarStock() {
        if (detallesPedido.isEmpty()) {
            // Política recomendada: si no hay productos, no hay stock inválido.
            return true;
        }
        for (Producto p : detallesPedido) {
            if (p.getCantidad() <= 0) {
                return false;
            }
        }

        return true;
    }
    public List<Producto> getDetallesPedido() {
        return detallesPedido;
    }
}

