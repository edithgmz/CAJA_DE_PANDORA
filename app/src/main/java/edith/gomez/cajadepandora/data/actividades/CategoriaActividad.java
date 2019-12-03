package edith.gomez.cajadepandora.data.actividades;
/*
 * Created by Edith on 18-May-19.
 */

public class CategoriaActividad {
    private String color, nombre;

    public CategoriaActividad(String color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
