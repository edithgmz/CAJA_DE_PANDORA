package edith.gomez.cajadepandora.data.actividades;
/*
 * Created by Edith on 18-May-19.
 */

public class Actividad {
    private String nombre, descripcion, fecha, categoria;
    private int hora, minutos;

    public Actividad(String nombre, String descripcion, String fecha, String categoria, int hora, int minutos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
        this.hora = hora;
        this.minutos = minutos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
}
