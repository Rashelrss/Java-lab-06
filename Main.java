import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoView view = new PedidoView();
        PedidoController controller = new PedidoController(view);
        int opcion;
        do {
            System.out.println("\n===== GESTION DE PEDIDOS =====");
            System.out.println("1. AGREGAR PEDIDO");
            System.out.println("2. ELIMINAR PEDIDO");
            System.out.println("3. ACTUALIZAR PEDIDO");
            System.out.println("4. BUSCAR PEDIDO");
            System.out.println("5. MOSTRAR TODOS LOS PEDIDOS");
            System.out.println("6. MARCAR PEDIDO COMO COMPLETO");
            System.out.println("7. MOSTRAR PEDIDOS POR ESTADO");
            System.out.println("8. CONTAR PEDIDOS PENDIENTES");
            System.out.println("9. MOSTRAR HISTORIAL");
            System.out.println("10. SALIR");
            System.out.print("OPCION: ");
            while (!sc.hasNextInt()) {
                System.out.println("INGRESE UN NUMERO VALIDO.");
                sc.nextLine();
                System.out.print("OPCION: ");
            }
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("NOMBRE DEL PEDIDO: ");
                    String nombre = sc.nextLine();

                    System.out.print("TIPO DEL PEDIDO: ");
                    String tipo = sc.nextLine();

                    controller.agregarPedido(nombre, tipo);
                    break;
                case 2:
                    System.out.print("NOMBRE DEL PEDIDO A ELIMINAR: ");
                    nombre = sc.nextLine();
                    controller.eliminarPedido(nombre);
                    break;
                case 3:
                    System.out.print("NOMBRE ACTUAL: ");
                    nombre = sc.nextLine();
                    System.out.print("NUEVO NOMBRE: ");
                    String nuevoNombre = sc.nextLine();
                    controller.actualizarPedido(nombre, nuevoNombre);
                    break;
                case 4:
                    System.out.print("INGRESE NOMBRE O TIPO: ");
                    String dato = sc.nextLine();
                    controller.buscarPedido(dato);
                    break;
                case 5:
                    controller.mostrarPedidos();
                    break;
                case 6:
                    System.out.print("NOMBRE DEL PEDIDO COMPLETADO: ");
                    nombre = sc.nextLine();
                    controller.completarPedido(nombre);
                    break;
                case 7:
                    System.out.println("1. PENDIENTES");
                    System.out.println("2. COMPLETOS");
                    System.out.print("SELECCIONE ESTADO: ");
                    String estado = sc.nextLine();
                    if (estado.equals("1")) {
                        controller.mostrarPorEstado("PENDIENTE");
                    } else if (estado.equals("2")) {
                        controller.mostrarPorEstado("COMPLETO");
                    } else {
                        System.out.println("ESTADO NO VALIDO.");
                    }
                    break;
                case 8:
                    controller.contarPendientes();
                    break;
                case 9:
                    controller.mostrarHistorial();
                    break;
                case 10:
                    System.out.println("SALIENDO DEL PROGRAMA...");
                    break;
                default:
                    System.out.println("OPCION NO VALIDA.");
            }
        } while (opcion != 10);

        sc.close();
    }
}
