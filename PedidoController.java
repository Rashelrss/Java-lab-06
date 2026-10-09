import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class PedidoController {
    private List<Pedido> pedidos;
    private List<String> historial;
    private PedidoView view;
    public PedidoController(PedidoView view) {
        this.pedidos = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.view = view;
    }
    public void agregarPedido(String nombre, String tipo) {
        Pedido pedido = new Pedido(nombre, tipo);
        pedidos.add(pedido);
        view.mostrarMensaje("PEDIDO AGREGADO.");
    }
    public void eliminarPedido(String nombre) {
        Iterator<Pedido> iterador = pedidos.iterator();
        while (iterador.hasNext()) {
            Pedido pedido = iterador.next();
            if (pedido.getNombre().equalsIgnoreCase(nombre)) {
                historial.add("ELIMINADO: " + pedido);
                iterador.remove();
                view.mostrarMensaje("PEDIDO ELIMINADO.");
                return;
            }
        }
        view.mostrarMensaje("PEDIDO NO ENCONTRADO.");
    }
    public void actualizarPedido(String nombre, String nuevoNombre) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNombre().equalsIgnoreCase(nombre)) {
                pedido.setNombre(nuevoNombre);
                view.mostrarMensaje("PEDIDO ACTUALIZADO.");
                return;
            }
        }
        view.mostrarMensaje("PEDIDO NO ENCONTRADO.");
    }
    public void buscarPedido(String dato) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNombre().equalsIgnoreCase(dato)
                    || pedido.getTipo().equalsIgnoreCase(dato)) {
                view.mostrarPedido(pedido);
                return;
            }
        }
        view.mostrarMensaje("PEDIDO NO ENCONTRADO.");
    }
    public void mostrarPedidos() {
        view.mostrarPedidos(pedidos);
    }
    public void completarPedido(String nombre) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNombre().equalsIgnoreCase(nombre)) {
                if (pedido.getEstado().equals("COMPLETO")) {
                    view.mostrarMensaje("EL PEDIDO YA ESTA COMPLETO.");
                    return;
                }
                pedido.setEstado("COMPLETO");
                historial.add("COMPLETADO: " + pedido);
                view.mostrarMensaje("PEDIDO MARCADO COMO COMPLETO.");
                return;
            }
        }
        view.mostrarMensaje("PEDIDO NO ENCONTRADO.");
    }
    public void mostrarPorEstado(String estado) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equalsIgnoreCase(estado)) {
                filtrados.add(pedido);
            }
        }
        view.mostrarPedidos(filtrados);
    }
    public void contarPendientes() {
        int cantidad = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equals("PENDIENTE")) {
                cantidad++;
            }
        }
        view.mostrarCantidad(cantidad);
    }
    public void mostrarHistorial() {
        view.mostrarHistorial(historial);
    }
}