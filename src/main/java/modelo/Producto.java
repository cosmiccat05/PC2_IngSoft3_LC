package modelo;
import java.util.Objects;

public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String sku;
    private String categoria;
    private boolean esActivo;
    private boolean descuentoAplicable;

    public Producto(String nombre, double precio, int cantidad, String sku, String categoria, boolean esActivo, boolean descuentoAplicable) {
        if (precio < 0) throw new IllegalArgumentException("Precio inválido");
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad inválida");
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("SKU inválido");

        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.sku = sku;
        this.categoria = categoria;
        this.esActivo = esActivo;
        this.descuentoAplicable = descuentoAplicable;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public String getSku() { return sku; }
    public String getCategoria() { return categoria; }
    public boolean isActivo() { return esActivo; }
    public boolean isDescuentoAplicable() { return descuentoAplicable; }

    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("Precio inválido");
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("Cantidad inválida");
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto otro = (Producto) o;
        return Objects.equals(this.sku, otro.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return nombre + " | SKU=" + sku + " | Precio=" + precio + " | Cant=" + cantidad;
    }
}