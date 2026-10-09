import java.util.ArrayList;
import java.util.List;
public class Inventario {
    private List<String> objetos;
    private String equipado;
    public Inventario() {
        objetos = new ArrayList<>();
        objetos.add("Espada");
        objetos.add("Pocion");
        equipado = "Espada";
    }
    public String getEquipado() {
        return equipado;
    }
    public void equipar(String objeto) {
        for (String item : objetos) {
            if (item.equalsIgnoreCase(objeto)
                    && !item.equalsIgnoreCase("Pocion")) {
                equipado = item;
                return;
            }
        }
    }
    public boolean usarPocion() {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).equalsIgnoreCase("Pocion")) {
                objetos.remove(i);
                return true;
            }
        }
        return false;
    }
    public void mostrarInventario() {
        System.out.println("INVENTARIO: " + objetos);
        System.out.println("ARMA EQUIPADA: " + equipado);
    }
}