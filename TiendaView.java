import java.util.ArrayList;
import java.util.Scanner;

public class TiendaView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenuPrincipal() {
        mostrarMensaje("");
        mostrarMensaje("===== TIENDA EN LINEA =====");
        mostrarMensaje("1. REGISTRARSE");
        mostrarMensaje("2. INICIAR SESION");
        mostrarMensaje("3. SALIR");
        return leerEntero("SELECCIONE UNA OPCION");
    }

    public int mostrarMenuSesion(String nombreUsuario) {
        mostrarMensaje("");
        mostrarMensaje("===== BIENVENIDO " + nombreUsuario + " =====");
        mostrarMensaje("1. VER PRODUCTOS");
        mostrarMensaje("2. AGREGAR PRODUCTO AL CARRITO");
        mostrarMensaje("3. QUITAR PRODUCTO DEL CARRITO");
        mostrarMensaje("4. VER CARRITO");
        mostrarMensaje("5. REALIZAR COMPRA");
        mostrarMensaje("6. VER HISTORIAL DE COMPRAS");
        mostrarMensaje("7. RESENAR UN PRODUCTO COMPRADO");
        mostrarMensaje("8. VER RESENAS DE UN PRODUCTO");
        mostrarMensaje("9. CERRAR SESION");
        return leerEntero("SELECCIONE UNA OPCION");
    }

    public void mostrarProductos(ArrayList<Producto> productos) {
        mostrarMensaje("---------- CATALOGO ----------");
        for (Producto p : productos) {
            mostrarMensaje(p.getId() + ". " + p.getNombre() + " | PRECIO: $" + formatear(p.getPrecio())
                    + " | STOCK: " + p.getStock());
        }
    }

    public void mostrarCarrito(ArrayList<LineaCarrito> lineas, double total) {
        if (lineas.isEmpty()) {
            mostrarMensaje("EL CARRITO ESTA VACIO");
            return;
        }
        mostrarMensaje("---------- CARRITO ----------");
        for (LineaCarrito l : lineas) {
            mostrarMensaje(l.getProducto().getId() + ". " + l.getProducto().getNombre()
                    + " | CANTIDAD: " + l.getCantidad() + " | SUBTOTAL: $" + formatear(l.subtotal()));
        }
        mostrarMensaje("TOTAL: $" + formatear(total));
    }

    public void mostrarHistorial(ArrayList<Compra> compras) {
        if (compras.isEmpty()) {
            mostrarMensaje("AUN NO HA REALIZADO COMPRAS");
            return;
        }
        mostrarMensaje("---------- HISTORIAL DE COMPRAS ----------");
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            mostrarMensaje("COMPRA " + (i + 1) + " | TOTAL: $" + formatear(c.getTotal()));
            for (LineaCarrito l : c.getLineas()) {
                mostrarMensaje("   - " + l.getProducto().getNombre() + " X" + l.getCantidad());
            }
        }
    }

    public void mostrarResenas(String nombreProducto, ArrayList<Resena> resenas, double promedio) {
        mostrarMensaje("---------- RESENAS DE " + nombreProducto + " ----------");
        if (resenas.isEmpty()) {
            mostrarMensaje("ESTE PRODUCTO AUN NO TIENE RESENAS");
            return;
        }
        for (Resena r : resenas) {
            mostrarMensaje(r.getNombreUsuario() + " | CALIFICACION: " + r.getCalificacion() + "/5");
            mostrarMensaje("   " + r.getComentario());
        }
        mostrarMensaje("PROMEDIO: " + formatear(promedio) + "/5 (" + resenas.size() + " RESENAS)");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje.toUpperCase());
    }

    public String leerTexto(String solicitud) {
        while (true) {
            System.out.print(solicitud.toUpperCase() + ": ");
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            mostrarMensaje("EL CAMPO NO PUEDE ESTAR VACIO");
        }
    }

    public int leerEntero(String solicitud) {
        while (true) {
            String texto = leerTexto(solicitud);
            boolean valido = texto.length() <= 9;
            for (int i = 0; i < texto.length(); i++) {
                if (!Character.isDigit(texto.charAt(i))) {
                    valido = false;
                }
            }
            if (valido) {
                return Integer.parseInt(texto);
            }
            mostrarMensaje("DEBE INGRESAR UN NUMERO ENTERO POSITIVO VALIDO");
        }
    }

    private String formatear(double valor) {
        return String.format("%.2f", valor);
    }
}
