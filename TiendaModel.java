import java.util.ArrayList;

class Usuario {
    private String nombre;
    private String correo;
    private String clave;
    private ArrayList<LineaCarrito> carrito = new ArrayList<>();
    private ArrayList<Compra> compras = new ArrayList<>();

    public Usuario(String nombre, String correo, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public ArrayList<LineaCarrito> getCarrito() {
        return carrito;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }
}

class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void descontarStock(int cantidad) {
        stock = stock - cantidad;
    }
}

class LineaCarrito {
    private Producto producto;
    private int cantidad;

    public LineaCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double subtotal() {
        return producto.getPrecio() * cantidad;
    }
}

class Compra {
    private ArrayList<LineaCarrito> lineas;
    private double total;

    public Compra(ArrayList<LineaCarrito> lineas, double total) {
        this.lineas = lineas;
        this.total = total;
    }

    public ArrayList<LineaCarrito> getLineas() {
        return lineas;
    }

    public double getTotal() {
        return total;
    }
}

class Resena {
    private String correo;
    private String nombreUsuario;
    private int productoId;
    private int calificacion;
    private String comentario;

    public Resena(String correo, String nombreUsuario, int productoId, int calificacion, String comentario) {
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.productoId = productoId;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }
}

public class TiendaModel {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Resena> resenas = new ArrayList<>();

    public TiendaModel() {
        productos.add(new Producto(1, "LAPTOP GAMER", 1450.00, 5));
        productos.add(new Producto(2, "MOUSE INALAMBRICO", 25.50, 20));
        productos.add(new Producto(3, "TECLADO MECANICO", 79.90, 12));
        productos.add(new Producto(4, "AUDIFONOS BLUETOOTH", 59.99, 15));
        productos.add(new Producto(5, "MONITOR 24 PULGADAS", 189.00, 8));
    }

    public String registrar(String nombre, String correo, String clave) {
        if (nombre.length() < 3) {
            return "EL NOMBRE DEBE TENER AL MENOS 3 CARACTERES";
        }
        if (!correo.contains("@") || !correo.contains(".") || correo.contains(" ")) {
            return "EL CORREO ELECTRONICO NO ES VALIDO";
        }
        if (clave.length() < 6) {
            return "LA CLAVE DEBE TENER AL MENOS 6 CARACTERES";
        }
        if (buscarUsuario(correo) != null) {
            return "YA EXISTE UN USUARIO CON ESE CORREO";
        }
        usuarios.add(new Usuario(nombre.toUpperCase(), correo.toLowerCase(), clave));
        return null;
    }

    public Usuario iniciarSesion(String correo, String clave) {
        Usuario usuario = buscarUsuario(correo);
        if (usuario != null && usuario.getClave().equals(clave)) {
            return usuario;
        }
        return null;
    }

    private Usuario buscarUsuario(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarProducto(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private LineaCarrito buscarLinea(Usuario usuario, int productoId) {
        for (LineaCarrito l : usuario.getCarrito()) {
            if (l.getProducto().getId() == productoId) {
                return l;
            }
        }
        return null;
    }

    public String agregarAlCarrito(Usuario usuario, int productoId, int cantidad) {
        Producto producto = buscarProducto(productoId);
        if (producto == null) {
            return "NO EXISTE UN PRODUCTO CON ESE IDENTIFICADOR";
        }
        if (cantidad <= 0) {
            return "LA CANTIDAD DEBE SER MAYOR A CERO";
        }
        LineaCarrito linea = buscarLinea(usuario, productoId);
        int total = cantidad;
        if (linea != null) {
            total = total + linea.getCantidad();
        }
        if (total > producto.getStock()) {
            return "STOCK INSUFICIENTE. UNIDADES DISPONIBLES: " + producto.getStock();
        }
        if (linea == null) {
            usuario.getCarrito().add(new LineaCarrito(producto, cantidad));
        } else {
            linea.setCantidad(total);
        }
        return null;
    }

    public String quitarDelCarrito(Usuario usuario, int productoId) {
        LineaCarrito linea = buscarLinea(usuario, productoId);
        if (linea == null) {
            return "EL PRODUCTO INDICADO NO ESTA EN EL CARRITO";
        }
        usuario.getCarrito().remove(linea);
        return null;
    }

    public double totalCarrito(Usuario usuario) {
        double total = 0;
        for (LineaCarrito l : usuario.getCarrito()) {
            total = total + l.subtotal();
        }
        return total;
    }

    public String realizarCompra(Usuario usuario) {
        if (usuario.getCarrito().isEmpty()) {
            return "EL CARRITO ESTA VACIO, AGREGUE PRODUCTOS ANTES DE COMPRAR";
        }
        for (LineaCarrito l : usuario.getCarrito()) {
            if (l.getCantidad() > l.getProducto().getStock()) {
                return "STOCK INSUFICIENTE PARA " + l.getProducto().getNombre();
            }
        }
        double total = totalCarrito(usuario);
        for (LineaCarrito l : usuario.getCarrito()) {
            l.getProducto().descontarStock(l.getCantidad());
        }
        usuario.getCompras().add(new Compra(new ArrayList<>(usuario.getCarrito()), total));
        usuario.getCarrito().clear();
        return null;
    }

    private boolean haComprado(Usuario usuario, int productoId) {
        for (Compra c : usuario.getCompras()) {
            for (LineaCarrito l : c.getLineas()) {
                if (l.getProducto().getId() == productoId) {
                    return true;
                }
            }
        }
        return false;
    }

    public String agregarResena(Usuario usuario, int productoId, int calificacion, String comentario) {
        if (buscarProducto(productoId) == null) {
            return "NO EXISTE UN PRODUCTO CON ESE IDENTIFICADOR";
        }
        if (!haComprado(usuario, productoId)) {
            return "SOLO PUEDE RESENAR PRODUCTOS QUE YA HAYA COMPRADO";
        }
        for (Resena r : resenas) {
            if (r.getCorreo().equals(usuario.getCorreo()) && r.getProductoId() == productoId) {
                return "YA REALIZO UNA RESENA PARA ESTE PRODUCTO";
            }
        }
        if (calificacion < 1 || calificacion > 5) {
            return "LA CALIFICACION DEBE ESTAR ENTRE 1 Y 5";
        }
        if (comentario.length() > 300) {
            return "EL COMENTARIO NO PUEDE SUPERAR 300 CARACTERES";
        }
        resenas.add(new Resena(usuario.getCorreo(), usuario.getNombre(), productoId, calificacion,
                comentario.toUpperCase()));
        return null;
    }

    public ArrayList<Resena> listarResenas(int productoId) {
        ArrayList<Resena> lista = new ArrayList<>();
        for (Resena r : resenas) {
            if (r.getProductoId() == productoId) {
                lista.add(r);
            }
        }
        return lista;
    }

    public double promedioResenas(int productoId) {
        ArrayList<Resena> lista = listarResenas(productoId);
        if (lista.isEmpty()) {
            return 0;
        }
        int suma = 0;
        for (Resena r : lista) {
            suma = suma + r.getCalificacion();
        }
        return (double) suma / lista.size();
    }
}
