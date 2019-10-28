package edith.gomez.cajadepandora.data.salud;
/*
 * Created by Edith on 18-May-19.
 */

public class CategoriaMedicamento {
    private String nombre;
    private final String tipo = "medicamento";

    public CategoriaMedicamento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
