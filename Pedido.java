public class Pedido {
    private String nombre;
    private String tipo;
    private String estado;
    public Pedido(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = "PENDIENTE";
    }
    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public String getEstado() {
        return estado;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String toString() {
        return "NOMBRE: " + nombre
                + " | TIPO: " + tipo
                + " | ESTADO: " + estado;
    }
}
