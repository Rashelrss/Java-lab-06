import java.util.List;
import java.util.Scanner;
public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }
    public String solicitarNombrePlato() {
        System.out.print("Introduce el nombre del plato: ");
        return scanner.nextLine();
    }
    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            System.out.println("\n--- Lista de pedidos ---");

            for (Pedido pedido : pedidos) {
                System.out.println("- " + pedido.getNombrePlato());
            }
        }
    }
    public void mostrarMenu() {
        System.out.println("\nOpcion");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Ver pedidos");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }
    public String solicitarOpcion() {
        return scanner.nextLine();
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void cerrarScanner() {
        scanner.close();
    }
}