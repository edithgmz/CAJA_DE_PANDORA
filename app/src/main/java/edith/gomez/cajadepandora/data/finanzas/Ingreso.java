package edith.gomez.cajadepandora.data.finanzas;
/*
 * Created by Edith on 18-May-19.
 */

public class Ingreso {
    private float cantidad;
    private String fecha, categoria;

    public Ingreso(float cantidad, String fecha, String categoria) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public float getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
