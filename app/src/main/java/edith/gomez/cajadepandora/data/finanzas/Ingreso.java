package edith.gomez.cajadepandora.data.finanzas;
/*
 * Created by Edith on 18-May-19.
 */

public class Ingreso {
    private float cantidadIngreso;
    private String fechaIngreso, categoriaIngreso, notaIngreso;

    public Ingreso(float cantidadIngreso, String fechaIngreso, String categoriaIngreso, String notaIngreso) {
        this.cantidadIngreso = cantidadIngreso;
        this.fechaIngreso = fechaIngreso;
        this.categoriaIngreso = categoriaIngreso;
        this.notaIngreso = notaIngreso;
    }

    public float getCantidadIngreso() {
        return cantidadIngreso;
    }

    public void setCantidadIngreso(float cantidadIngreso) {
        this.cantidadIngreso = cantidadIngreso;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCategoriaIngreso() {
        return categoriaIngreso;
    }

    public void setCategoriaIngreso(String categoriaIngreso) {
        this.categoriaIngreso = categoriaIngreso;
    }

    public String getNotaIngreso() {
        return notaIngreso;
    }

    public void setNotaIngreso(String notaIngreso) {
        this.notaIngreso = notaIngreso;
    }
}
