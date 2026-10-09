public class TiendaController {
    private TiendaModel modelo;
    private TiendaView vista;

    public TiendaController(TiendaModel modelo, TiendaView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            int opcion = vista.mostrarMenuPrincipal();
            if (opcion == 1) {
                registrar();
            } else if (opcion == 2) {
                Usuario usuario = iniciarSesion();
                if (usuario != null) {
                    ejecutarSesion(usuario);
                }
            } else if (opcion == 3) {
                vista.mostrarMensaje("GRACIAS POR VISITARNOS, HASTA PRONTO");
                salir = true;
            } else {
                vista.mostrarMensaje("OPCION NO VALIDA, INTENTE NUEVAMENTE");
            }
        }
    }

    private void ejecutarSesion(Usuario usuario) {
        boolean cerrar = false;
        while (!cerrar) {
            int opcion = vista.mostrarMenuSesion(usuario.getNombre());
            if (opcion == 1) {
                vista.mostrarProductos(modelo.listarProductos());
            } else if (opcion == 2) {
                agregarAlCarrito(usuario);
            } else if (opcion == 3) {
                quitarDelCarrito(usuario);
            } else if (opcion == 4) {
                vista.mostrarCarrito(usuario.getCarrito(), modelo.totalCarrito(usuario));
            } else if (opcion == 5) {
                realizarCompra(usuario);
            } else if (opcion == 6) {
                vista.mostrarHistorial(usuario.getCompras());
            } else if (opcion == 7) {
                crearResena(usuario);
            } else if (opcion == 8) {
                verResenas();
            } else if (opcion == 9) {
                vista.mostrarMensaje("SESION CERRADA CORRECTAMENTE");
                cerrar = true;
            } else {
                vista.mostrarMensaje("OPCION NO VALIDA, INTENTE NUEVAMENTE");
            }
        }
    }

    public void registrar() {
        String nombre = vista.leerTexto("NOMBRE COMPLETO");
        String correo = vista.leerTexto("CORREO ELECTRONICO");
        String clave = vista.leerTexto("CLAVE");
        String confirmacion = vista.leerTexto("CONFIRME LA CLAVE");
        if (!clave.equals(confirmacion)) {
            vista.mostrarMensaje("ERROR: LAS CLAVES NO COINCIDEN");
            return;
        }
        String error = modelo.registrar(nombre, correo, clave);
        if (error == null) {
            vista.mostrarMensaje("USUARIO REGISTRADO CORRECTAMENTE, YA PUEDE INICIAR SESION");
        } else {
            vista.mostrarMensaje("ERROR: " + error);
        }
    }

    public Usuario iniciarSesion() {
        String correo = vista.leerTexto("CORREO ELECTRONICO");
        String clave = vista.leerTexto("CLAVE");
        Usuario usuario = modelo.iniciarSesion(correo, clave);
        if (usuario == null) {
            vista.mostrarMensaje("ERROR: CORREO O CLAVE INCORRECTOS");
        } else {
            vista.mostrarMensaje("INICIO DE SESION EXITOSO");
        }
        return usuario;
    }

    public void agregarAlCarrito(Usuario usuario) {
        int id = vista.leerEntero("IDENTIFICADOR DEL PRODUCTO");
        int cantidad = vista.leerEntero("CANTIDAD");
        String error = modelo.agregarAlCarrito(usuario, id, cantidad);
        if (error == null) {
            vista.mostrarMensaje("PRODUCTO AGREGADO AL CARRITO");
        } else {
            vista.mostrarMensaje("ERROR: " + error);
        }
    }

    public void quitarDelCarrito(Usuario usuario) {
        int id = vista.leerEntero("IDENTIFICADOR DEL PRODUCTO");
        String error = modelo.quitarDelCarrito(usuario, id);
        if (error == null) {
            vista.mostrarMensaje("PRODUCTO RETIRADO DEL CARRITO");
        } else {
            vista.mostrarMensaje("ERROR: " + error);
        }
    }

    public void realizarCompra(Usuario usuario) {
        String error = modelo.realizarCompra(usuario);
        if (error == null) {
            vista.mostrarMensaje("COMPRA REALIZADA CON EXITO. YA PUEDE RESENAR LOS PRODUCTOS ADQUIRIDOS");
        } else {
            vista.mostrarMensaje("ERROR: " + error);
        }
    }

    public void crearResena(Usuario usuario) {
        int id = vista.leerEntero("IDENTIFICADOR DEL PRODUCTO");
        int calificacion = vista.leerEntero("CALIFICACION (1 A 5)");
        String comentario = vista.leerTexto("COMENTARIO");
        String error = modelo.agregarResena(usuario, id, calificacion, comentario);
        if (error == null) {
            vista.mostrarMensaje("RESENA REGISTRADA CORRECTAMENTE, GRACIAS POR SU OPINION");
        } else {
            vista.mostrarMensaje("ERROR: " + error);
        }
    }

    public void verResenas() {
        int id = vista.leerEntero("IDENTIFICADOR DEL PRODUCTO");
        Producto producto = modelo.buscarProducto(id);
        if (producto == null) {
            vista.mostrarMensaje("ERROR: NO EXISTE UN PRODUCTO CON ESE IDENTIFICADOR");
        } else {
            vista.mostrarResenas(producto.getNombre(), modelo.listarResenas(id), modelo.promedioResenas(id));
        }
    }
}
