package edith.gomez.cajadepandora.data.notas;
/*
 * Created by Edith on 18-May-19.
 */

public class Nota {
    private String titulo, contenido, fecha;
    private byte[] audio, imagen, estadoEmocional;

    public Nota(String titulo, String contenido, String fecha, byte[] imagen, byte[] audio, byte[] estadoEmocional) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.audio = audio;
        this.imagen = imagen;
        this.estadoEmocional = estadoEmocional;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public byte[] getEstadoEmocional() {
        return estadoEmocional;
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

    public void setEstadoEmocional(byte[] estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }
}
