public class Tarea {
    private String descripcion;
    private boolean completada;
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public boolean isCompletada() {
        return completada;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void completar() {
        completada = true;
    }
    public String toString() {
        String estado = completada ? "COMPLETADA" : "PENDIENTE";
        return "Tarea: " + descripcion + " | Estado: " + estado;
    }
}