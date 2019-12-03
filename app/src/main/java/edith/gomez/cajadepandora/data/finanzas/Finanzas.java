package edith.gomez.cajadepandora.data.finanzas;
/*
 * Created by Edith on 23-Nov-19.
 */

public class Finanzas {
    private float cantidad;
    private String categoria;

    public Finanzas(float cantidad, String categoria) {
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
