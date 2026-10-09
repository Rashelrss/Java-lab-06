public class Main {
    public static void main(String[] args) {
        TiendaModel modelo = new TiendaModel();
        TiendaView vista = new TiendaView();
        TiendaController controlador = new TiendaController(modelo, vista);
        controlador.iniciar();
    }
}
