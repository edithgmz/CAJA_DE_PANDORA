package edith.gomez.cajadepandora.data.salud;
/*
 * Created by Edith on 18-May-19.
 */

public class Medicamento {
    private String medicamento, categoria;
    private int dosis, hora, minutos, cantidad;

    public Medicamento(String medicamento, String categoria, int dosis, int hora, int minutos, int cantidad) {
        this.medicamento = medicamento;
        this.categoria = categoria;
        this.dosis = dosis;
        this.hora = hora;
        this.minutos = minutos;
        this.cantidad = cantidad;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
