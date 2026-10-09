public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;
    public PedidoControlador(PedidoModelo modelo,
                             PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    public void agregarPedido(String nombrePlato) {
        Pedido pedido = new Pedido(nombrePlato);
        modelo.agregarPedido(pedido);
        vista.mostrarMensaje(
            "Pedido agregado: " + nombrePlato
        );
    }
    public void mostrarPedidos() {
        vista.mostrarPedidos(modelo.getPedidos());
    }
    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1":
                    String nombrePlato =
                        vista.solicitarNombrePlato();
                    agregarPedido(nombrePlato);
                    break;
                case "2":
                    mostrarPedidos();
                    break;
                case "3":
                    vista.mostrarMensaje("Saliendo");
                    break;
                default:
                    vista.mostrarMensaje(
                        "Opción no válida. Inténtalo de nuevo."
                    );
            }
        } while (!opcion.equals("3"));
        vista.cerrarScanner();
    }
}