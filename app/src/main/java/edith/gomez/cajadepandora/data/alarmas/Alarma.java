package edith.gomez.cajadepandora.data.alarmas;
/*
 * Created by Edith on 18-May-19.
 */

public class Alarma {
    private int hora, minutos, posponer_minutos, posponer_veces;
    private String nombre, periodo, tono;
    private boolean repetir, activa;
    private String[] dias;

    public Alarma(int hora, int minutos, int posponer_minutos, int posponer_veces, String nombre,
                  String periodo, String tono, boolean repetir, boolean activa, String[] dias) {
        this.hora = hora;
        this.minutos = minutos;
        this.posponer_minutos = posponer_minutos;
        this.posponer_veces = posponer_veces;
        this.nombre = nombre;
        this.periodo = periodo;
        this.tono = tono;
        this.repetir = repetir;
        this.activa = activa;
        this.dias = dias;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getPosponer_minutos() {
        return posponer_minutos;
    }

    public int getPosponer_veces() {
        return posponer_veces;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getTono() {
        return tono;
    }

    public boolean isRepetir() {
        return repetir;
    }

    public boolean isActiva() {
        return activa;
    }

    public String[] getDias() {
        return dias;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setPosponer_minutos(int posponer_minutos) {
        this.posponer_minutos = posponer_minutos;
    }

    public void setPosponer_veces(int posponer_veces) {
        this.posponer_veces = posponer_veces;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setTono(String tono) {
        this.tono = tono;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }
}
