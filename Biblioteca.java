import java.util.*;

public class Biblioteca {
    // =================== Clase Libro ===================

    class Libro {
    private String titulo;
    private String autor;
    private String codigo;
    private boolean disponible;

        // constructor del libro 

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true; // por defecto está disponible
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void marcarPrestado() {
        this.disponible = false;
    }

    public void marcarDisponible() {
        this.disponible = true;
    }

    public void mostrarDatos() {
        System.out.println("Título: " + titulo + " | Autor: " + autor + " | Código: " + codigo + " | Disponible: " + (disponible ? "Sí" : "No"));
    }
}

// ===================  Usuario ===================
    class Usuario {
        private String nombre;
        private String idUsuario;
        private List<Libro> librosPrestados;

        public Usuario(String nombre, String idUsuario) {
            this.nombre = nombre;
            this.idUsuario = idUsuario;
            this.librosPrestados = new ArrayList<>();
        }

        public String getIdUsuario() {
            return idUsuario;
        }

        public List<Libro> getLibrosPrestados() {
            return librosPrestados;
        }

        public boolean puedePrestar() {
            return librosPrestados.size() < 3;
        }

        public void agregarPrestamo(Libro libro) {
            if (puedePrestar()) {
                librosPrestados.add(libro);
            }
        }

        public void devolverLibro(Libro libro) {
            librosPrestados.remove(libro);
        }

        public void mostrarDatos() {
            System.out.println("Usuario: " + nombre + " | ID: " + idUsuario + " | Libros prestados: " + librosPrestados.size());
        }
    }

    // ====================== Prestamo de libbros ======================
    class Prestamo {
        private Usuario usuario;
        private Libro libro;
        private Date fechaInicio;
        private Date fechaLimite;

        public Prestamo(Usuario usuario, Libro libro, Date fechaInicio, Date fechaLimite) {
            this.usuario = usuario;
            this.libro = libro;
            this.fechaInicio = fechaInicio;
            this.fechaLimite = fechaLimite;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public Libro getLibro() {
            return libro;
        }

        public Date getFechaLimite() {
            return fechaLimite;
        }

        public void mostrarPrestamo() {
            System.out.println("===============prestamo de libros===================");
            System.out.println("Usuario: " + usuario.getIdUsuario() + " | Libro: " + libro.getCodigo() +" | Fecha inicio: " + fechaInicio + " | Fecha límite: " + fechaLimite);
        }
    }

    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    private static final int MULTA_POR_DIA = 500;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public void registrarLibro(Scanner sc) {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Código: ");
        String codigo = sc.nextLine();

        libros.add(new Libro(titulo, autor, codigo));
        System.out.println("Libro registrado correctamente ✅");
    }

    public void registrarUsuario(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("ID Usuario: ");
        String id = sc.nextLine();

        usuarios.add(new Usuario(nombre, id));
        System.out.println("Usuario registrado correctamente ✅");
    }

    public void prestarLibro(Scanner sc) {
        System.out.print("Ingrese ID de usuario: ");
        String id = sc.nextLine();
        Usuario usuario = buscarUsuario(id);

        if (usuario == null) {
            System.out.println("Usuario no encontrado ❌");
            return;
        }

        if (!usuario.puedePrestar()) {
            System.out.println("El usuario ya tiene 3 libros prestados ❌");
            return;
        }

        System.out.print("Ingrese código del libro: ");
        String codigo = sc.nextLine();
        Libro libro = buscarLibro(codigo);

        if (libro == null || !libro.isDisponible()) {
            System.out.println("Libro no disponible ❌");
            return;
        }

        libro.marcarPrestado();
        usuario.agregarPrestamo(libro);

        Date hoy = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoy);
        cal.add(Calendar.DAY_OF_MONTH, 7); // plazo de 7 días

        Prestamo prestamo = new Prestamo(usuario, libro, hoy, cal.getTime());
        prestamos.add(prestamo);

        System.out.println("Préstamo realizado correctamente ✅");
    }

    public void devolverLibro(Scanner sc) {
        System.out.print("Ingrese ID de usuario: ");
        String id = sc.nextLine();
        Usuario usuario = buscarUsuario(id);

        if (usuario == null) {
            System.out.println("Usuario no encontrado ❌");
            return;
        }

        System.out.print("Ingrese código del libro: ");
        String codigo = sc.nextLine();
        Libro libro = buscarLibro(codigo);

        if (libro == null || !usuario.getLibrosPrestados().contains(libro)) {
            System.out.println("El usuario no tiene este libro ❌");
            return;
        }

        Prestamo prestamo = buscarPrestamo(usuario, libro);
        if (prestamo != null) {
            Date hoy = new Date();
            long retraso = (hoy.getTime() - prestamo.getFechaLimite().getTime()) / (1000 * 60 * 60 * 24);
            if (retraso > 0) {
                long multa = retraso * MULTA_POR_DIA;
                System.out.println("Devolución con retraso de " + retraso + " días. Multa: $" + multa);
            } else {
                System.out.println("Devolución sin retraso ✅");
            }

            libro.marcarDisponible();
            usuario.devolverLibro(libro);
            prestamos.remove(prestamo);
        }
    }

    public void mostrarLibrosDisponibles() {
        System.out.println("---- Libros disponibles ----");
        for (Libro l : libros) {
            if (l.isDisponible()) l.mostrarDatos();
        }
    }

    public void mostrarUsuarios() {
        System.out.println("---- Usuarios ----");
        for (Usuario u : usuarios) {
            u.mostrarDatos();
        }
    }

    public void mostrarHistorialPrestamos() {
        System.out.println("---- Historial de préstamos ----");
        for (Prestamo p : prestamos) {
            p.mostrarPrestamo();
        }
    }

    private Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario().equals(id)) return u;
        }
        return null;
    }

    private Libro buscarLibro(String codigo) {
        for (Libro l : libros) {
            if (l.getCodigo().equals(codigo)) return l;
        }
        return null;
    }

    private Prestamo buscarPrestamo(Usuario usuario, Libro libro) {
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario) && p.getLibro().equals(libro)) return p;
        }
        return null;
    }

    // =================== Clase main ================= {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcion;

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n===== MENÚ BIBLIOTECA =====");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. mostrar Historial de prestamos ");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) 
            {
                case 1:
                    biblioteca.registrarLibro(sc);
                break;
                case 2:
                    biblioteca.registrarUsuario(sc);
                break;
                case 3:
                    biblioteca.prestarLibro(sc);
                break;
                case 4:
                    biblioteca.devolverLibro(sc);
                break;
                case 5:
                    biblioteca.mostrarLibrosDisponibles();
                break;
                case 6: 
                    biblioteca.mostrarUsuarios();
                break;
                case 7 : 
                    biblioteca.mostrarHistorialPrestamos();
                break;
                case 0:
                    System.out.println("Saliendo... 👋");
                break;
                default:
                    System.out.println("Opción no válida ❌");
            }
        } 
        while (opcion != 0);
        scanner.close();
    }
}
