package edith.gomez.cajadepandora.data.notas;
/*
 * Created by Edith on 18-May-19.
 */

public class EstadoEmocional {
    private int estadoEmocional;
    private int id;
    private String nombre;

    public EstadoEmocional(int estadoEmocional, int id, String nombre) {
        this.estadoEmocional = estadoEmocional;
        this.id = id;
        this.nombre = nombre;
    }

    public int getEstadoEmocional() {
        return estadoEmocional;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEstadoEmocional(int estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
