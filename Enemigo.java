public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;
    public Enemigo(String nombre, int salud,
                   int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
    }
    public String getNombre() {
        return nombre;
    }
    public int getSalud() {
        return salud;
    }
    public int getNivel() {
        return nivel;
    }
    public String getTipo() {
        return tipo;
    }
    public boolean estaVivo() {
        return salud > 0;
    }
    public void atacar(Jugador jugador) {
        int danio = 5 + nivel * 2;

        jugador.recibirDanio(danio);

        System.out.println(nombre + " ataca al jugador y causa "
                + danio + " de daño.");
    }
    public void recibirDanio(int danio) {
        salud -= danio;

        if (salud < 0) {
            salud = 0;
        }
        System.out.println(nombre + " recibe "
                + danio + " de daño.");
    }
    public String toString() {
        return nombre + " | Tipo: " + tipo
                + " | Nivel: " + nivel
                + " | Salud: " + salud;
    }
}