import java.util.List;
public class TareaView {
    public void mostrarTareas(List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("NO HAY TAREAS REGISTRADAS.");
            return;
        }
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void mostrarCantidad(int cantidad) {
        System.out.println("TAREAS PENDIENTES: " + cantidad);
    }
}