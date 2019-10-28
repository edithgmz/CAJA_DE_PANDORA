package edith.gomez.cajadepandora.data.notas;
/*
 * Created by Edith on 18-May-19.
 */

public class Nota {
    private String titulo, contenido, fecha, audio;
    private int alarmas, salud, actividades, edoemocional, imagen;

    public Nota(String titulo, String contenido, String fecha, String audio, int alarmas, int salud, int actividades, int imagen, int edoemocional) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.audio = audio;
        this.alarmas = alarmas;
        this.salud = salud;
        this.actividades = actividades;
        this.imagen = imagen;
        this.edoemocional = edoemocional;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public int getAlarmas() {
        return alarmas;
    }

    public int getSalud() {
        return salud;
    }

    public int getActividades() {
        return actividades;
    }

    public int getImagen() {
        return imagen;
    }

    public int getEdoemocional() {
        return edoemocional;
    }

    public String getAudio() {
        return audio;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setAlarmas(int alarmas) {
        this.alarmas = alarmas;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setActividades(int actividades) {
        this.actividades = actividades;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setEdoemocional(int edoemocional) {
        this.edoemocional = edoemocional;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
