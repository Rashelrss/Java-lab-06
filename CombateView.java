import java.util.List;
public class CombateView {
    public void mostrarJugador(Jugador jugador) {
        System.out.println("\n--- JUGADOR ---");
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("Salud: " + jugador.getSalud());
        System.out.println("Nivel: " + jugador.getNivel());
        jugador.getInventario().mostrarInventario();
    }
    public void mostrarEnemigos(List<Enemigo> enemigos) {
        System.out.println("\n--- ENEMIGOS ---");
        for (int i = 0; i < enemigos.size(); i++) {
            System.out.println((i + 1) + ". "
                    + enemigos.get(i));
        }
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}