package edith.gomez.cajadepandora.data.finanzas;
/*
 * Created by Edith on 18-May-19.
 */

public class CategoriaGasto {
    private String nombre;

    public CategoriaGasto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
