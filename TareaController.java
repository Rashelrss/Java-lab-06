import java.util.ArrayList;
import java.util.List;
public class TareaController {
    private List<Tarea> tareas;
    private TareaView vista;
    public TareaController(TareaView vista) {
        this.vista = vista;
        this.tareas = new ArrayList<>();
    }
    public void agregarTarea(String descripcion) {
        if (descripcion.trim().isEmpty()) {
            vista.mostrarMensaje("LA DESCRIPCION NO PUEDE ESTAR VACIA.");
            return;
        }
        tareas.add(new Tarea(descripcion));
        vista.mostrarMensaje("TAREA AGREGADA.");
    }
    public void mostrarTareas() {
        vista.mostrarTareas(tareas);
    }
    public void completarTarea(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.get(indice).completar();
            vista.mostrarMensaje("TAREA COMPLETADA.");
        } else {
            vista.mostrarMensaje("NUMERO DE TAREA INVALIDO.");
        }
    }
    public void eliminarTarea(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.remove(indice);
            vista.mostrarMensaje("TAREA ELIMINADA.");
        } else {
            vista.mostrarMensaje("NUMERO DE TAREA INVALIDO.");
        }
    }
    public void contarPendientes() {
        int cantidad = 0;

        for (Tarea tarea : tareas) {
            if (!tarea.isCompletada()) {
                cantidad++;
            }
        }
        vista.mostrarCantidad(cantidad);
    }
}