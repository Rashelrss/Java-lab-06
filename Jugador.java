public class Jugador {
    private String nombre;
    private int salud;
    private int nivel;
    private Inventario inventario;
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
        this.nivel = 1;
        this.inventario = new Inventario();
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
    public Inventario getInventario() {
        return inventario;
    }
    public void atacar(Enemigo enemigo) {
        int danio = 10;
        if (inventario.getEquipado().equalsIgnoreCase("Espada")) {
            danio = 20;
        }
        enemigo.recibirDanio(danio);
        System.out.println(nombre + " ataca con "
                + inventario.getEquipado()
                + " y causa " + danio + " de daño.");
    }
    public void usarObjeto() {
        if (inventario.usarPocion()) {
            salud += 30;
            if (salud > 100) {
                salud = 100;
            }
            System.out.println(nombre
                    + " usa una pocion. Salud: " + salud);
        } else {
            System.out.println("No quedan pociones.");
        }
    }
    public void recibirDanio(int danio) {
        salud -= danio;

        if (salud < 0) {
            salud = 0;
        }
    }
}