package edith.gomez.cajadepandora.data.finanzas;
/*
 * Created by Edith on 18-May-19.
 */

public class Gasto {
    private float cantidadGasto;
    private String fechaGasto, categoriaGasto;

    public Gasto(float cantidadGasto, String fechaGasto, String categoriaGasto) {
        this.cantidadGasto = cantidadGasto;
        this.fechaGasto = fechaGasto;
        this.categoriaGasto = categoriaGasto;
    }

    public float getCantidadGasto() {
        return cantidadGasto;
    }

    public String getFechaGasto() {
        return fechaGasto;
    }

    public String getCategoriaGasto() {
        return categoriaGasto;
    }

    public void setCantidadGasto(float cantidadGasto) {
        this.cantidadGasto = cantidadGasto;
    }

    public void setFechaGasto(String fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public void setCategoriaGasto(String categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
    }
}
