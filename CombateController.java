import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class CombateController {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private CombateView vista;
    private Random random;
    public CombateController(Jugador jugador, CombateView vista) {
        this.jugador = jugador;
        this.vista = vista;
        this.enemigos = new ArrayList<>();
        this.random = new Random();
        enemigos.add(new Enemigo("Goblin", 40, 1, "Terrestre"));
        enemigos.add(new Enemigo("Dragon", 80, 3, "Volador"));
    }
    public void mostrarEstado() {
        vista.mostrarJugador(jugador);
        vista.mostrarEnemigos(enemigos);
    }
    public void atacarEnemigo(int indice) {
        if (indice < 0 || indice >= enemigos.size()) {
            vista.mostrarMensaje("Enemigo no valido.");
            return;
        }
        Enemigo enemigo = enemigos.get(indice);
        if (!enemigo.estaVivo()) {
            vista.mostrarMensaje("Ese enemigo ya fue derrotado.");
            return;
        }
        jugador.atacar(enemigo);
        if (!enemigo.estaVivo()) {
            vista.mostrarMensaje(
                    enemigo.getNombre() + " ha sido derrotado.");
        }
        if (hayEnemigosVivos()) {
            turnoEnemigo();
        }
    }
    public void usarObjeto() {
        jugador.usarObjeto();
        if (hayEnemigosVivos()) {
            turnoEnemigo();
        }
    }
    public void turnoEnemigo() {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                vivos.add(enemigo);
            }
        }
        if (vivos.isEmpty()) {
            return;
        }
        Enemigo enemigo = vivos.get(random.nextInt(vivos.size()));

        int accion = random.nextInt(2);

        if (accion == 0) {
            enemigo.atacar(jugador);
        } else {
            vista.mostrarMensaje(enemigo.getNombre()
                    + " se prepara para atacar.");
        }
    }

    public boolean hayEnemigosVivos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                return true;
            }
        }

        return false;
    }

    public boolean combateTerminado() {
        return jugador.getSalud() <= 0 || !hayEnemigosVivos();
    }

    public void equiparArma(String arma) {
        jugador.getInventario().equipar(arma);
        vista.mostrarMensaje("Arma equipada: "
                + jugador.getInventario().getEquipado());
    }
    
public List<Enemigo> getEnemigos() {
    return enemigos;
}
}
