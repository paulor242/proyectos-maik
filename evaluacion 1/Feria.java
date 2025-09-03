import java.util.Random;
import java.util.ArrayList;

public class Feria {
    public String nombre;
    public Integer edad;
    private Double creatividad;
    public String[] invento = {"máquinas mecánicas", "programas de software", "experimentos químicos"};
    public Integer puntos;

    // Constructor
    public Feria(String nombre, Integer edad, Double creatividad, String[] invento, Integer puntos) {
        this.nombre = nombre;
        this.edad = edad;
        this.creatividad = creatividad;
        this.invento = invento;
        this.puntos = puntos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public Double getCreatividad() {
        return creatividad;
    }

    public String[] getInvento() {
        return invento;
    }

    // Setter
    public void setCreatividad(Double creatividad) {
        this.creatividad = creatividad;
    }

    // Método abstracto/genérico
    public void Presentar() {
        // Vacío, cada subclase lo define
    }

    // Obtener un invento al azar
    public String getInventoAleatorio() {
        if (invento == null || invento.length == 0) {
            return "No hay inventos disponibles";
        }
        Random random = new Random();
        int indiceAleatorio = random.nextInt(invento.length);
        return invento[indiceAleatorio];
    }

    // Clase interna para los miembros que presentan el invento
    public static class Miembros extends Feria {
        public Miembros(String nombre, Integer edad, Double creatividad, String[] invento, Integer puntos) {
            super(nombre, edad, creatividad, invento, puntos);
        }

        @Override
        public void Presentar() {
            System.out.println("Mi nombre es " + nombre + ", tengo " + edad + " años");
            System.out.println("Mi invento es: " + getInventoAleatorio());
            System.out.println("--------------------------");
        }
    }

    // Mostrar invento
    public void mostrarInvento() {
        System.out.println("El invento de " + nombre + " es un proyecto de: " + getInventoAleatorio());
    }

    // Aumento de la creatividad
    public void aumentarCreatividad(Double incremento) {
        if (puntos >= 0) {
            this.creatividad += puntos;
            System.out.println(nombre + " ha ganado " + puntos + " puntos de creatividad. Nueva creatividad: " +creatividad);
            System.out.println("--------------------------");
        } else {
            System.out.println("Los puntos deben ser positivos. La cantidad de puntos es " + puntos + ", su creatividad es " +creatividad);
            System.out.println("--------------------------");
        }
    }

    // Main
    public static void main(String[] args) {
        // Crear instancias de Miembros
        Feria miembro1 = new Miembros("Juan", 20, 10.0, new String[]{"máquinas mecánicas", "programas de software", "experimentos químicos"}, 10);
        Feria miembro2 = new Miembros("Pedro", 20, 20.0, new String[]{"máquinas mecánicas", "experimentos químicos"}, 20);
        Feria miembro3 = new Miembros("Carlos", 20, 15.0, new String[]{"máquinas mecánicas", "experimentos químicos"}, 10);

        // Crear lista
        ArrayList<Feria> listaMiembros = new ArrayList<>();
        listaMiembros.add(miembro1);
        listaMiembros.add(miembro2);
        listaMiembros.add(miembro3);

        // Recorrer lista
        for (Feria f : listaMiembros) {
            f.Presentar();
            f.mostrarInvento();
            f.aumentarCreatividad(5.0); // Pasar un valor válido en lugar de null
            System.out.println("-------------------");
        }
    }
}