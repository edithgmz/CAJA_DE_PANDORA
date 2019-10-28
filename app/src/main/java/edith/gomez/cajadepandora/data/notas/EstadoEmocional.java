package edith.gomez.cajadepandora.data.notas;
/*
 * Created by Edith on 18-May-19.
 */

public class EstadoEmocional {
    private int img;
    private int id;
    private String nombre;

    public EstadoEmocional(int img, int id, String nombre) {
        this.img = img;
        this.id = id;
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
