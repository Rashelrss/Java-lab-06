import java.util.List;
public class PedidoView {
    public void mostrarPedido(Pedido pedido) {
        System.out.println(pedido);
    }
    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("NO HAY PEDIDOS.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
    public void mostrarHistorial(List<String> historial) {
        if (historial.isEmpty()) {
            System.out.println("EL HISTORIAL ESTA VACIO.");
            return;
        }
        System.out.println("\n--- HISTORIAL DE PEDIDOS ---");
        for (String registro : historial) {
            System.out.println(registro);
        }
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void mostrarCantidad(int cantidad) {
        System.out.println("PEDIDOS PENDIENTES: " + cantidad);
    }
}