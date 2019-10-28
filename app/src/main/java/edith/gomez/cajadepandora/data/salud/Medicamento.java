package edith.gomez.cajadepandora.data.salud;
/*
 * Created by Edith on 18-May-19.
 */

public class Medicamento {
    private String nombre;
    private int dosis, hora, minutos, cantidad;

    public Medicamento(String nombre, int dosis, int hora, int minutos, int cantidad) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.hora = hora;
        this.minutos = minutos;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDosis() {
        return dosis;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
